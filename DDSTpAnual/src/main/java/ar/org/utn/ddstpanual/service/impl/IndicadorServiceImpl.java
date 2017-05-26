package ar.org.utn.ddstpanual.service.impl;

import org.apache.commons.lang3.StringUtils;
import org.uqbar.commons.utils.Observable;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import ar.org.utn.ddstpanual.exception.ServiceException;
import ar.org.utn.ddstpanual.model.Indicador;
import ar.org.utn.ddstpanual.service.IndicadorService;

@Observable
public class IndicadorServiceImpl implements IndicadorService {

  private List<Indicador> indicadores;
  private String nombre;
  private String formula;
  private String error;

  @Override
  public void guardarIndicador() throws ServiceException {
    String path = System.getProperty("user.dir");
    FileWriter filewriter = null;
    PrintWriter printwriten = null;
    try {
      filewriter = new FileWriter(path + "\\src\\main\\resources\\indicadores.txt", true);
      printwriten = new PrintWriter(filewriter);
      String linea = nombre + "=" + formula;
      printwriten.println(linea);

    } catch (IOException e) {
      e.printStackTrace();
    } finally {
      try {
        if (null != filewriter) {
          filewriter.close();
        }
      } catch (Exception ex) {
        ex.printStackTrace();
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
    indicadores = new ArrayList<Indicador>();
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
      e.printStackTrace();
    } finally {
      try {
        if (null != filereader) {
          filereader.close();
        }
      } catch (Exception ex) {
        ex.printStackTrace();
      }
    }
    return indicadores;
  }

  @Override
  public void eliminarIndicador() throws ServiceException {
    // TODO Auto-generated method stub

  }

  public List<Indicador> getIndicadores() {
    return indicadores;
  }

  public void setIndicadores(List<Indicador> indicadores) {
    this.indicadores = indicadores;
  }

  public String getError() {
    return error;
  }

  public void setError(String error) {
    this.error = error;
  }

  public String getNombre() {
    return nombre;
  }

  public void setNombre(String nombre) {
    this.nombre = nombre;
  }

  public String getFormula() {
    return formula;
  }

  public void setFormula(String formula) {
    this.formula = formula;
  }
}
