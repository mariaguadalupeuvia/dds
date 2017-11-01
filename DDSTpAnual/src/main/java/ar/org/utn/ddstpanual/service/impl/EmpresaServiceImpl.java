package ar.org.utn.ddstpanual.service.impl;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import ar.org.utn.ddstpanual.db.EmpresaDb;
import ar.org.utn.ddstpanual.exception.DbException;
import ar.org.utn.ddstpanual.exception.ServiceException;
import ar.org.utn.ddstpanual.model.Cuenta;
import ar.org.utn.ddstpanual.model.Empresa;
import ar.org.utn.ddstpanual.model.EmpresaExcel;
import ar.org.utn.ddstpanual.model.Periodo;
import ar.org.utn.ddstpanual.service.EmpresaService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class EmpresaServiceImpl implements EmpresaService {

  EmpresaDb empresaDb;

  @Override
  public void subirExcel(final String rutaArchivo) throws ServiceException {
    try {
      Row row;
      final File file = new File(rutaArchivo);
      final FileInputStream fileStream = new FileInputStream(file);
      final HSSFWorkbook workbook = new HSSFWorkbook(fileStream);
      final HSSFSheet sheet = workbook.getSheetAt(0);
      final Iterator<Row> rowIterator = sheet.iterator();
      rowIterator.next();
      String nombreEmpresa = "";
      String nombreCuenta = "";
      String fecha = "";
      Empresa empresa = null;
      Cuenta cuenta = null;
      Periodo periodo = null;
      while (rowIterator.hasNext()) {
        row = rowIterator.next();
        nombreEmpresa = row.getCell(0).getStringCellValue();
        nombreCuenta = row.getCell(1).getStringCellValue();
        fecha = String.valueOf((float) row.getCell(2).getNumericCellValue());
        empresa = getEmpresaDb().obtenerEmpresa(nombreEmpresa);
        if (empresa != null) {
          cuenta = getEmpresaDb().obtenerCuenta(empresa.getId(), nombreCuenta);
          if (cuenta != null) {
            periodo = getEmpresaDb().obtenerPeriodo(cuenta.getId(), fecha);
            if (periodo != null) {
              periodo.setValor((float) row.getCell(3).getNumericCellValue());
            } else {
              periodo = new Periodo();
              periodo.setFecha(fecha);
              periodo.setValor((float) row.getCell(3).getNumericCellValue());
              cuenta.getPeriodos().add(periodo);
            }
          } else {
            cuenta = new Cuenta();
            cuenta.setPeriodos(new ArrayList<>());
            cuenta.setNombre(nombreCuenta);
            periodo = new Periodo();
            periodo.setFecha(fecha);
            periodo.setValor((float) row.getCell(3).getNumericCellValue());
            cuenta.getPeriodos().add(periodo);
            empresa.getCuentas().add(cuenta);
          }
        } else {
          empresa = new Empresa();
          empresa.setNombre(nombreEmpresa);
          empresa.setCuentas(new ArrayList<>());
          cuenta = new Cuenta();
          cuenta.setPeriodos(new ArrayList<>());
          cuenta.setNombre(nombreCuenta);
          periodo = new Periodo();
          periodo.setFecha(String.valueOf((float) row.getCell(2).getNumericCellValue()));
          periodo.setValor((float) row.getCell(3).getNumericCellValue());
          cuenta.getPeriodos().add(periodo);
          empresa.getCuentas().add(cuenta);
        }
        getEmpresaDb().guardarEmpresa(empresa);
      }
      workbook.close();
    } catch (final IOException e) {
      log.error(e.getMessage());
      throw new ServiceException("Error al abrir el archivo.");
    } catch (final DbException e) {
      log.error(e.getMessage());
      throw new ServiceException(e.getMessage());
    }
  }

  @Override
  public List<Empresa> obtenerEmpresas() throws ServiceException {
    try {
      final List<Empresa> empresas = getEmpresaDb().obtenerEmpresas();
      return empresas;
    } catch (final DbException e) {
      log.error(e.getMessage());
      throw new ServiceException(e.getMessage());
    }
  }

  @Override
  public List<EmpresaExcel> buscar(final Empresa empresa, final Cuenta cuenta, final Periodo periodo) throws ServiceException {
    List<EmpresaExcel> empresas = new ArrayList<EmpresaExcel>();
    if (cuenta != null) {
      final List<Cuenta> cuentas =
          empresa.getCuentas().stream().filter(c -> c.getNombre().equals(cuenta.getNombre())).collect(Collectors.toList());
      empresa.setCuentas(cuentas);
    }
    if (periodo != null) {
      final List<Periodo> periodos =
          cuenta.getPeriodos().stream().filter(p -> p.getFecha().equals(periodo.getFecha())).collect(Collectors.toList());
      empresa.getCuentas().get(0).setPeriodos(periodos);
    }
    empresas = convertToEmpresaExcel(empresa);
    return empresas;
  }

  public List<EmpresaExcel> convertToEmpresaExcel(final Empresa empresa) {
    final List<EmpresaExcel> empresas = new ArrayList<EmpresaExcel>();
    for (final Cuenta cuenta : empresa.getCuentas()) {
      for (final Periodo periodo : cuenta.getPeriodos()) {
        final EmpresaExcel empresaExcel = new EmpresaExcel();
        empresaExcel.setFecha(periodo.getFecha());
        empresaExcel.setValor(periodo.getValor());
        empresaExcel.setNombreCuenta(cuenta.getNombre());
        empresaExcel.setNombreEmpresa(empresa.getNombre());
        empresas.add(empresaExcel);
      }
    }
    return empresas;
  }

  @Override
  public List<Periodo> obtenerPeriodos() throws ServiceException {
    try {
      return getEmpresaDb().obtenerPeriodos();
    } catch (DbException e) {
      log.error(e.getMessage());
      throw new ServiceException(e.getMessage());
    }
  }

  @Override
  public Empresa obtenerEmpresa(String nombre) throws ServiceException {
    Empresa empresa = new Empresa();
    try {
      empresa = getEmpresaDb().obtenerEmpresa(nombre);
      return empresa;
    } catch (DbException e) {
      log.error(e.getMessage());
      throw new ServiceException(e.getMessage());
    }
  }

  public EmpresaDb getEmpresaDb() {
    if (empresaDb != null) {
      return empresaDb;
    }
    empresaDb = new EmpresaDb();
    return empresaDb;
  }

}
