package ar.org.utn.ddstpanual.archivo.impl;

import org.apache.commons.lang3.StringUtils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import ar.org.utn.ddstpanual.archivo.IndicadorArchivo;
import ar.org.utn.ddstpanual.exception.ServiceException;
import ar.org.utn.ddstpanual.model.Indicador;

public class IndicadorArchivoImpl implements IndicadorArchivo {

  @Override
  public void guardarIndicador(Indicador indicador) throws ServiceException {
    String path = System.getProperty("user.dir");
    FileWriter filewriter = null;
    PrintWriter printwriten = null;
    try {
      filewriter = new FileWriter(path + "\\src\\main\\resources\\indicadores.txt", true);
      printwriten = new PrintWriter(filewriter);
      String linea = indicador.getNombre() + "=" + indicador.getFormula();
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
  public List<Indicador> obtenerIndicadores() throws ServiceException {
    String path = System.getProperty("user.dir");
    File file = new File(path + "\\src\\main\\resources\\indicadores.txt");
    FileReader filereader = null;
    BufferedReader buffer = null;
    String linea = "";
    List<Indicador> indicadores = new ArrayList<Indicador>();
    try {
      filereader = new FileReader(file);
      buffer = new BufferedReader(filereader);
      while ((linea = buffer.readLine()) != null) {
        Indicador indicador = new Indicador();
        indicador.setNombre(StringUtils.split(linea, "=")[0]);
        indicador.setFormula(StringUtils.split(linea, "=")[1]);
        indicadores.add(indicador);
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
    return indicadores;
  }

  @Override
  public List<String> obtenerNombresIndicadores() throws ServiceException {
    String path = System.getProperty("user.dir");
    File file = new File(path + "\\src\\main\\resources\\indicadores.txt");
    FileReader filereader = null;
    BufferedReader buffer = null;
    String linea = "";
    List<String> indicadores = new ArrayList<String>();
    try {
      filereader = new FileReader(file);
      buffer = new BufferedReader(filereader);
      while ((linea = buffer.readLine()) != null) {
        indicadores.add(StringUtils.split(linea, "=")[0]);
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
    return indicadores;
  }

}
