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

import ar.org.utn.ddstpanual.archivo.EmpresaArchivo;
import ar.org.utn.ddstpanual.archivo.impl.EmpresaArchivoImpl;
import ar.org.utn.ddstpanual.exception.ArchivoException;
import ar.org.utn.ddstpanual.exception.ServiceException;
import ar.org.utn.ddstpanual.log.LogData;
import ar.org.utn.ddstpanual.model.Cuenta;
import ar.org.utn.ddstpanual.model.Empresa;
import ar.org.utn.ddstpanual.model.EmpresaExcel;
import ar.org.utn.ddstpanual.model.Periodo;
import ar.org.utn.ddstpanual.service.EmpresaService;

public class EmpresaServiceImpl implements EmpresaService {

  EmpresaArchivo empresaArchivo;

  @Override
  public void subirExcel(final String rutaArchivo) throws ServiceException {
    try {
      final File file = new File(rutaArchivo);
      final FileInputStream fileStream = new FileInputStream(file);
      EmpresaExcel empresaExcel = new EmpresaExcel();
      final HSSFWorkbook workbook = new HSSFWorkbook(fileStream);
      final HSSFSheet sheet = workbook.getSheetAt(0);
      final Iterator<Row> rowIterator = sheet.iterator();
      Row row;
      rowIterator.next();
      while (rowIterator.hasNext()) {
        row = rowIterator.next();
        empresaExcel = new EmpresaExcel();
        empresaExcel.setNombreEmpresa(row.getCell(0).getStringCellValue());
        empresaExcel.setNombreCuenta(row.getCell(1).getStringCellValue());
        empresaExcel.setFecha(String.valueOf((float) row.getCell(2).getNumericCellValue()));
        empresaExcel.setValor((float) row.getCell(3).getNumericCellValue());
        if (!getEmpresaArchivo().exists(empresaExcel)) {
          getEmpresaArchivo().guardarEmpresa(empresaExcel);
        } else {
          LogData.EscribirLogText(row.getRowNum() + ": Ya se ingreso un valor para este periodo en el archivo.");
        }
      }
      workbook.close();
    } catch (final IOException ex) {
      throw new ServiceException("Error al abrir el archivo.");
    } catch (final ArchivoException e) {
      throw new ServiceException(e.getMessage());
    }
  }

  @Override
  public List<Empresa> obtenerEmpresas() throws ServiceException {
    try {
      final List<Empresa> empresas = getEmpresaArchivo().obtenerEmpresas();
      return empresas;
    } catch (final ArchivoException e) {
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

  public EmpresaArchivo getEmpresaArchivo() {
    if (empresaArchivo != null) {
      return empresaArchivo;
    }
    empresaArchivo = new EmpresaArchivoImpl();
    return empresaArchivo;
  }

  @Override
  public List<Periodo> obtenerPeriodos() throws ServiceException {
    try {
      final List<Empresa> empresas = this.obtenerEmpresas();
      final List<Periodo> periodos = new ArrayList<Periodo>();
      for (final Empresa e : empresas) {
        for (final Cuenta c : e.getCuentas()) {
          for (final Periodo per : c.getPeriodos()) {
            if (periodos.stream().allMatch(p -> !p.getFecha().equals(per.getFecha()))) {
              periodos.add(new Periodo(per.getFecha()));
            }
          }
        }
      }
      return periodos;
    } catch (final ServiceException e) {
      throw new ServiceException(e.getMessage());
    }
  }

  @Override
  public Empresa obtenerEmpresa(String nombre) throws ServiceException {
    try {
      return getEmpresaArchivo().obtenerEmpresa(nombre);
    } catch (ArchivoException e) {
      throw new ServiceException(e.getMessage());
    }
  }

}
