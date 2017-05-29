package ar.org.utn.ddstpanual.archivo.impl;

import org.apache.commons.lang3.StringUtils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import ar.org.utn.ddstpanual.archivo.EmpresaArchivo;
import ar.org.utn.ddstpanual.exception.ServiceException;
import ar.org.utn.ddstpanual.model.EmpresaExcel;

public class EmpresaArchivoImpl implements EmpresaArchivo {

  @Override
  public void guardarEmpresa(EmpresaExcel empresa) throws ServiceException {
    String path = System.getProperty("user.dir");
    FileWriter filewriter = null;
    PrintWriter printwriten = null;
    try {
      filewriter = new FileWriter(
          path + "\\src\\main\\resources\\empresas\\" + empresa.getNombreEmpresa(), true);
      printwriten = new PrintWriter(filewriter);
      String linea =
          empresa.getNombreCuenta() + "|" + empresa.getFecha() + "|" + empresa.getValor();
      printwriten.println(linea);
    } catch (IOException e) {
      throw new ServiceException("Error al abrir el archivo");
    } finally {
      try {
        if (null != filewriter) {
          filewriter.close();
        }
      } catch (Exception ex) {
        throw new ServiceException("Error al intentar cerrar el archivo.");
      }
    }
  }

  @Override
  public boolean exists(EmpresaExcel empresa) throws ServiceException {
    String path = System.getProperty("user.dir");
    File file = new File(path + "\\src\\main\\resources\\empresas\\" + empresa.getNombreEmpresa());
    FileReader filereader = null;
    BufferedReader buffer = null;
    String linea = "";
    String lineaEmpresa = "";
    boolean existe = false;
    try {
      filereader = new FileReader(file);
      buffer = new BufferedReader(filereader);
      while ((linea = buffer.readLine()) != null) {
        lineaEmpresa = empresa.getNombreCuenta() + "|" + empresa.getFecha() + "|";
        if (StringUtils.contains(linea, lineaEmpresa)) {
          existe = true;
        }
      }
    } catch (IOException e) {
      throw new ServiceException("Error al abrir el archivo");
    } finally {
      try {
        if (null != filereader) {
          filereader.close();
        }
      } catch (Exception ex) {
        throw new ServiceException("Error al intentar cerrar el archivo.");
      }
    }
    return existe;
  }

}
