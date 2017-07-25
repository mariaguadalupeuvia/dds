package ar.org.utn.ddstpanual.archivo.impl;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import ar.org.utn.ddstpanual.archivo.MetodologiaArchivo;
import ar.org.utn.ddstpanual.exception.ArchivoException;
import ar.org.utn.ddstpanual.model.metodologia.Metodologia;
import ar.org.utn.ddstpanual.utils.MetodologiaClassAdapter;


public class MetodologiaArchivoImpl implements MetodologiaArchivo {

  String pathMetodologias = "\\src\\main\\resources\\metodologias\\";

  @Override
  public void guardarMetodologia(Metodologia metodologia) throws ArchivoException {
    final String path = System.getProperty("user.dir");
    FileWriter filewriter = null;
    PrintWriter printwriten = null;
    try {
      filewriter = new FileWriter(path + pathMetodologias + metodologia.getNombre(), true);
      printwriten = new PrintWriter(filewriter);
      final Gson gson = new Gson();
      final String linea = gson.toJson(metodologia, Metodologia.class);// metodologia.toJson();
      printwriten.println(linea);
    } catch (final IOException e) {
      throw new ArchivoException("Error al abrir el archivo");
    } finally {
      try {
        if (null != filewriter) {
          filewriter.close();
        }
      } catch (final Exception ex) {
        throw new ArchivoException("Error al intentar cerrar el archivo.");
      }
    }
  }

  @Override
  public Metodologia obtenerMetodologia(String nombre) throws ArchivoException {
    final String path = System.getProperty("user.dir");
    Metodologia metodologia = new Metodologia();
    FileReader filereader = null;
    BufferedReader buffer = null;
    final Gson gson = new GsonBuilder().registerTypeAdapter(Metodologia.class, new MetodologiaClassAdapter()).create();
    final File file = new File(path + pathMetodologias + nombre);
    String linea = "";
    try {
      filereader = new FileReader(file);
      buffer = new BufferedReader(filereader);
      while ((linea = buffer.readLine()) != null) {
        metodologia = gson.fromJson(linea, Metodologia.class);
      }
    } catch (final IOException e) {
      throw new ArchivoException("Error al abrir el archivo");
    } finally {
      try {
        if (null != filereader) {
          filereader.close();
        }
      } catch (final Exception ex) {
        throw new ArchivoException("Error al intentar cerrar el archivo.");
      }
    }
    return metodologia;
  }

  @Override
  public List<Metodologia> obtenerMetodologias() throws ArchivoException {
    final Gson gson = new GsonBuilder().registerTypeAdapter(Metodologia.class, new MetodologiaClassAdapter()).create();
    final String path = System.getProperty("user.dir");
    final File dir = new File(path + pathMetodologias);
    final String[] ficheros = dir.list();
    final List<Metodologia> metodologias = new ArrayList<Metodologia>();
    FileReader filereader = null;
    BufferedReader buffer = null;
    if (ficheros != null) {
      for (final String fichero : ficheros) {
        Metodologia metodologia = new Metodologia();
        final File file = new File(path + pathMetodologias + fichero);
        String linea = "";
        try {
          filereader = new FileReader(file);
          buffer = new BufferedReader(filereader);
          while ((linea = buffer.readLine()) != null) {
            metodologia = gson.fromJson(linea, Metodologia.class);
            metodologias.add(metodologia);
          }
        } catch (final IOException e) {
          throw new ArchivoException("Error al abrir el archivo");
        } finally {
          try {
            if (null != filereader) {
              filereader.close();
            }
          } catch (final Exception ex) {
            throw new ArchivoException("Error al intentar cerrar el archivo.");
          }
        }
        metodologias.add(metodologia);
      }
    }
    return metodologias;
  }

}
