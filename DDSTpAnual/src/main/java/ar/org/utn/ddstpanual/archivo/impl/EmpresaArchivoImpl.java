package ar.org.utn.ddstpanual.archivo.impl;

import org.apache.commons.lang3.StringUtils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import ar.org.utn.ddstpanual.archivo.EmpresaArchivo;
import ar.org.utn.ddstpanual.exception.DbException;
import ar.org.utn.ddstpanual.model.Cuenta;
import ar.org.utn.ddstpanual.model.Empresa;
import ar.org.utn.ddstpanual.model.EmpresaExcel;
import ar.org.utn.ddstpanual.model.Periodo;

public class EmpresaArchivoImpl implements EmpresaArchivo {

  public void guardarEmpresa(final EmpresaExcel empresa) throws DbException {
    final String path = System.getProperty("user.dir");
    FileWriter filewriter = null;
    PrintWriter printwriten = null;
    try {
      filewriter = new FileWriter(path + "\\src\\main\\resources\\empresas\\" + empresa.getNombreEmpresa(), true);
      printwriten = new PrintWriter(filewriter);
      final String linea = empresa.getNombreCuenta() + "|" + empresa.getFecha() + "|" + empresa.getValor();
      printwriten.println(linea);
    } catch (final IOException e) {
      throw new DbException("Error al abrir el archivo");
    } finally {
      try {
        if (null != filewriter) {
          filewriter.close();
        }
      } catch (final Exception ex) {
        throw new DbException("Error al intentar cerrar el archivo.");
      }
    }
  }

  @Override
  public boolean exists(final EmpresaExcel empresa) throws DbException {
    final String path = System.getProperty("user.dir");
    final File file = new File(path + "\\src\\main\\resources\\empresas\\" + empresa.getNombreEmpresa());
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
    } catch (final FileNotFoundException fnfe) {
      existe = false;
    } catch (final IOException e) {
      throw new DbException("Error al abrir el archivo");
    } finally {
      try {
        if (null != filereader) {
          filereader.close();
        }
      } catch (final Exception ex) {
        throw new DbException("Error al intentar cerrar el archivo.");
      }
    }
    return existe;
  }

  @Override
  public Empresa obtenerEmpresa(final String nombreEmpresa) throws DbException {
    Empresa empresa = new Empresa();
    final String path = System.getProperty("user.dir");
    final File file = new File(path + "\\src\\main\\resources\\empresas\\" + nombreEmpresa);
    FileReader filereader = null;
    BufferedReader buffer = null;
    String linea = "";
    try {
      filereader = new FileReader(file);
      buffer = new BufferedReader(filereader);
      empresa.setNombre(nombreEmpresa);
      empresa.setCuentas(new ArrayList<Cuenta>());
      while ((linea = buffer.readLine()) != null) {
        Cuenta cuenta = new Cuenta();
        final String[] registro = StringUtils.split(linea, "|");
        cuenta.setNombre(registro[0]);
        if (empresa.getCuentas().contains(cuenta)) {
          for (final Cuenta cuentaIter : empresa.getCuentas()) {
            if (cuentaIter.getNombre().equals(registro[0])) {
              cuenta = cuentaIter;
              break;
            }
          }
          final Periodo periodo = new Periodo();
          periodo.setFecha(registro[1]);
          periodo.setValor(Float.valueOf(registro[2]));
          cuenta.getPeriodos().add(periodo);
          empresa.getCuentas().remove(cuenta);
        } else {
          cuenta.setPeriodos(new ArrayList<Periodo>());
          final Periodo periodo = new Periodo();
          periodo.setFecha(registro[1]);
          periodo.setValor(Float.valueOf(registro[2]));
          cuenta.getPeriodos().add(periodo);
        }
        empresa.getCuentas().add(cuenta);
      }
    } catch (final FileNotFoundException fnfe) {
      throw new DbException("No existe la empresa " + nombreEmpresa);
    } catch (final IOException e) {
      throw new DbException("Error al abrir el archivo");
    } finally {
      try {
        if (null != filereader) {
          filereader.close();
        }
      } catch (final Exception ex) {
        throw new DbException("Error al intentar cerrar el archivo.");
      }
    }
    return empresa;
  }

  @Override
  public List<Empresa> obtenerEmpresas() throws DbException {
    final String path = System.getProperty("user.dir");
    final File dir = new File(path + "\\src\\main\\resources\\empresas\\");
    final String[] ficheros = dir.list();
    final List<Empresa> empresas = new ArrayList<Empresa>();
    FileReader filereader = null;
    BufferedReader buffer = null;
    if (ficheros != null) {
      for (final String fichero : ficheros) {
        final Empresa empresa = new Empresa();
        empresa.setNombre(fichero);
        empresa.setCuentas(new ArrayList<Cuenta>());
        final File file = new File(path + "\\src\\main\\resources\\empresas\\" + fichero);
        String linea = "";
        try {
          filereader = new FileReader(file);
          buffer = new BufferedReader(filereader);
          while ((linea = buffer.readLine()) != null) {
            Cuenta cuenta = new Cuenta();
            final String[] registro = StringUtils.split(linea, "|");
            cuenta.setNombre(registro[0]);
            if (empresa.getCuentas().contains(cuenta)) {
              // TODO: Pasarlo a lambda expression
              for (final Cuenta cuentaIter : empresa.getCuentas()) {
                if (cuentaIter.getNombre().equals(registro[0])) {
                  cuenta = cuentaIter;
                  break;
                }
              }
              final Periodo periodo = new Periodo();
              periodo.setFecha(registro[1]);
              periodo.setValor(Float.valueOf(registro[2]));
              cuenta.getPeriodos().add(periodo);
              empresa.getCuentas().remove(cuenta);
            } else {
              cuenta.setPeriodos(new ArrayList<Periodo>());
              final Periodo periodo = new Periodo();
              periodo.setFecha(registro[1]);
              periodo.setValor(Float.valueOf(registro[2]));
              cuenta.getPeriodos().add(periodo);
            }
            empresa.getCuentas().add(cuenta);
          }
        } catch (final IOException e) {
          throw new DbException("Error al abrir el archivo");
        } finally {
          try {
            if (null != filereader) {
              filereader.close();
            }
          } catch (final Exception ex) {
            throw new DbException("Error al intentar cerrar el archivo.");
          }
        }
        empresas.add(empresa);
      }
    }
    return empresas;
  }

  @Override
  public List<Periodo> obtenerPeriodos(final String nombreEmpresa) throws DbException {
    final String path = System.getProperty("user.dir");
    final File dir = new File(path + "\\src\\main\\resources\\empresas\\" + nombreEmpresa);
    FileReader filereader = null;
    BufferedReader buffer = null;
    String linea = "";
    final List<Periodo> periodos = new ArrayList<Periodo>();
    try {
      filereader = new FileReader(dir);
      buffer = new BufferedReader(filereader);
      while ((linea = buffer.readLine()) != null) {
        final String[] registro = StringUtils.split(linea, "|");
        if (periodos.stream().allMatch(p -> !p.getFecha().equals(registro[1]))) {
          periodos.add(new Periodo(registro[1]));
        }
      }
    } catch (final FileNotFoundException fnfe) {
      throw new DbException("No existe una empresa con ese nombre");
    } catch (final IOException e) {
      throw new DbException("Error al abrir el archivo");
    } finally {
      try {
        if (null != filereader) {
          filereader.close();
        }
      } catch (final Exception ex) {
        throw new DbException("Error al intentar cerrar el archivo.");
      }
    }
    /*
     * int i = 0; while(!periodos.isEmpty()){ System.out.println(periodos.get(i).getFecha() + "  " +
     * periodos.get(i).getValor() + "\n"); i++; }
     */
    return periodos;
  }

}
