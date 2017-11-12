package ar.org.utn.ddstpanual.service;

import org.apache.commons.lang3.StringUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.TimerTask;
import java.util.stream.Collectors;

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
      // Thread.sleep(300000);
      Thread.sleep(20000);
      File directory = new File("src/main/resources/uploads/");
      directory.mkdirs();
      List<String> nombresArchivos = Arrays.asList(directory.list());
      nombresArchivos = nombresArchivos.stream()
          .filter(nombre -> !StringUtils.contains(nombre, "_done") && !StringUtils.contains(nombre, "_error")).collect(Collectors.toList());
      for (String nombreArchivo : nombresArchivos) {
        File archivo = new File(directory, nombreArchivo);
        try {
          InputStream inputStream = new FileInputStream(archivo);
          empresaService.subirArchivo(inputStream);
          archivo.renameTo(new File(archivo.getParentFile(), nombreArchivo + "_done"));
          log.info("Se subio el archivo {}", nombreArchivo);
        } catch (ServiceException e) {
          archivo.renameTo(new File(archivo.getParentFile(), nombreArchivo + "_error"));
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
