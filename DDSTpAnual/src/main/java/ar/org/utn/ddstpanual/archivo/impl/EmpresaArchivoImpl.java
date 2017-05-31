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

import ar.org.utn.ddstpanual.archivo.EmpresaArchivo;
import ar.org.utn.ddstpanual.exception.ServiceException;
import ar.org.utn.ddstpanual.model.Cuenta;
import ar.org.utn.ddstpanual.model.Empresa;
import ar.org.utn.ddstpanual.model.EmpresaExcel;
import ar.org.utn.ddstpanual.model.Periodo;

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

  @Override
  public List<Empresa> obtenerEmpresas() throws ServiceException {
    String path = System.getProperty("user.dir");
    File dir = new File(path + "\\src\\main\\resources\\empresas\\");
    String[] ficheros = dir.list();
    List<Empresa> empresas = new ArrayList<Empresa>();
    FileReader filereader = null;
    BufferedReader buffer = null;
    if (ficheros != null) {
      for (String fichero : ficheros) {
        Empresa empresa = new Empresa();
        empresa.setNombre(fichero);
        empresa.setCuentas(new ArrayList<Cuenta>());
        File file = new File(path + "\\src\\main\\resources\\empresas\\" + fichero);
        String linea = "";
        try {
          filereader = new FileReader(file);
          buffer = new BufferedReader(filereader);
          while ((linea = buffer.readLine()) != null) {
            Cuenta cuenta = new Cuenta();
            String[] registro = StringUtils.split(linea, "|");
            cuenta.setNombre(registro[0]);
            if (empresa.getCuentas().contains(cuenta)) {
              // TODO: Pasarlo a lambda expression
              for (Cuenta cuentaIter : empresa.getCuentas()) {
                if (cuentaIter.getNombre().equals(registro[0])) {
                  cuenta = cuentaIter;
                }
              }
              Periodo periodo = new Periodo();
              periodo.setFecha(registro[1]);
              periodo.setValor(Float.valueOf(registro[2]));
              cuenta.getPeriodos().add(periodo);
              empresa.getCuentas().remove(cuenta);
            } else {
              cuenta.setPeriodos(new ArrayList<Periodo>());
              Periodo periodo = new Periodo();
              periodo.setFecha(registro[1]);
              periodo.setValor(Float.valueOf(registro[2]));
              cuenta.getPeriodos().add(periodo);
            }
            empresa.getCuentas().add(cuenta);
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
        empresas.add(empresa);
      }
    }

    return empresas;
  }

}
