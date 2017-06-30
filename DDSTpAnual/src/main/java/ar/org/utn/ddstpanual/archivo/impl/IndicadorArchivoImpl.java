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
import ar.org.utn.ddstpanual.exception.ArchivoException;
import ar.org.utn.ddstpanual.model.Indicador;
import ar.org.utn.ddstpanual.tree.model.Arbol;
import ar.org.utn.ddstpanual.tree.utils.IndicadorUtil;

public class IndicadorArchivoImpl implements IndicadorArchivo {

  @Override
  public void guardarIndicador(Indicador indicador) throws ArchivoException {
    String path = System.getProperty("user.dir");
    FileWriter filewriter = null;
    PrintWriter printwriten = null;
    try {
      filewriter = new FileWriter(path + "\\src\\main\\resources\\indicadores.txt", true);
      printwriten = new PrintWriter(filewriter);
      String linea = indicador.getNombre() + "=" + indicador.getFormula();
      printwriten.println(linea);
    } catch (IOException e) {
      throw new ArchivoException("Error al abrir el archivo");
    } finally {
      try {
        if (null != filewriter) {
          filewriter.close();
        }
      } catch (Exception ex) {
        throw new ArchivoException("Error al intentar cerrar el archivo.");
      }
    }
  }

  @Override
  public List<Indicador> obtenerIndicadores() throws ArchivoException {
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
      throw new ArchivoException("Error al abrir el archivo");
    } finally {
      try {
        if (null != filereader) {
          filereader.close();
        }
      } catch (Exception ex) {
        throw new ArchivoException("Error al intentar cerrar el archivo.");
      }
    }
    return indicadores;
  }

  public boolean exists(Indicador indicador) throws ArchivoException {
    String path = System.getProperty("user.dir");
    File file = new File(path + "\\src\\main\\resources\\indicadores.txt");
    FileReader filereader = null;
    BufferedReader buffer = null;
    String linea = "";
    boolean existe = false;
    try {
      filereader = new FileReader(file);
      buffer = new BufferedReader(filereader);
      while ((linea = buffer.readLine()) != null) {
        String[] indicadorArchivo = StringUtils.split(linea, "=");
        if (indicadorArchivo[0].equals(indicador.getNombre())
            || indicadorArchivo[1].equals(indicador.getFormula())) {
          existe = true;
        }
      }
    } catch (IOException e) {
      throw new ArchivoException("Error al abrir el archivo");
    } finally {
      try {
        if (null != filereader) {
          filereader.close();
        }
      } catch (Exception ex) {
        throw new ArchivoException("Error al intentar cerrar el archivo.");
      }
    }

    return existe;
  }

  @Override
  public void eliminarIndicador(Indicador indicador) throws ArchivoException {
    // Existe el indicador y si esta devolver la posicion
    // Despues borrar la linea
    
  }

  public Arbol obtenerArbolPorIdicador(String nombreIndicador) {
    // Llamar al objeto de la clase estatico
    return IndicadorUtil.obtenerIndicador(nombreIndicador).getArbol();
  }
  
  @Override
  public String obtenerFormula(String nombre) throws ArchivoException {
	    String path = System.getProperty("user.dir");
	    File file = new File(path + "\\src\\main\\resources\\indicadores.txt");
	    FileReader filereader = null;
	    BufferedReader buffer = null;
	    String linea = "";
	    String formula = "";
	    try {
	      filereader = new FileReader(file);
	      buffer = new BufferedReader(filereader);
	      while ((linea = buffer.readLine()) != null) {
	        String[] indicadorArchivo = StringUtils.split(linea, "=");
	        if (indicadorArchivo[0].equals(nombre)){
	            formula = indicadorArchivo[1];	        
	        }
	      }
	    } catch (IOException e) {
	      throw new ArchivoException("Error al abrir el archivo");
	    } finally {
	      try {
	        if (null != filereader) {
	          filereader.close();
	        }
	      } catch (Exception ex) {
	        throw new ArchivoException("Error al intentar cerrar el archivo.");
	      }
	    }
	    return formula;
  }
}
