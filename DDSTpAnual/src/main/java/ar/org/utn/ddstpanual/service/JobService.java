package ar.org.utn.ddstpanual.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.TimerTask;

import ar.org.utn.ddstpanual.exception.ServiceException;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class JobService extends TimerTask {

  private static EmpresaService empresaService = new EmpresaService();

  @Override
  public void run() {
    log.info("Job comienza:" + new Date());
    completeTask();
    log.info("Job termino:" + new Date());
  }

  private void completeTask() {
    try {
      // 5 minutos
      Thread.sleep(300000);
      File directory = new File("src/main/resources/uploads/");
      directory.mkdirs();
      List<String> nombresArchivos = Arrays.asList(directory.list());
      for (String nombreArchivo : nombresArchivos) {
        try {
          File archivo = new File(directory, nombreArchivo);
          InputStream inputStream = new FileInputStream(archivo);
          empresaService.subirArchivo(inputStream);
          log.info("Se subio el archivo {}", nombreArchivo);
        } catch (ServiceException e) {
          log.error("Error al subir el archivo {}", nombreArchivo);
        } catch (FileNotFoundException e) {
          log.error("No se encontro el archivo {}", nombreArchivo);
        }
      }
    } catch (InterruptedException e) {
      log.error("Se produjo un error inesperado.");
      e.printStackTrace();
    }
  }

}
