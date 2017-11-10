package ar.org.utn.ddstpanual.service;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Optional;

import ar.org.utn.ddstpanual.db.EmpresaDb;
import ar.org.utn.ddstpanual.exception.DbException;
import ar.org.utn.ddstpanual.exception.ServiceException;
import ar.org.utn.ddstpanual.model.Cuenta;
import ar.org.utn.ddstpanual.model.Empresa;
import ar.org.utn.ddstpanual.model.Periodo;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class EmpresaService {

  EmpresaDb empresaDb;

  public void subirArchivo(final InputStream inputStream) throws ServiceException {
    try {
      Row row;
      final HSSFWorkbook workbook = new HSSFWorkbook(inputStream);
      final HSSFSheet sheet = workbook.getSheetAt(0);
      final Iterator<Row> rowIterator = sheet.iterator();
      rowIterator.next();
      while (rowIterator.hasNext()) {
        row = rowIterator.next();
        final String nombreEmpresa = row.getCell(0).getStringCellValue();
        final String nombreCuenta = row.getCell(1).getStringCellValue();
        final String fecha = String.valueOf((int) row.getCell(2).getNumericCellValue());
        final float valor = (float) row.getCell(3).getNumericCellValue();
        agregarEmpresa(nombreEmpresa, nombreCuenta, fecha, valor);
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

  public void agregarEmpresa(final String nombreEmpresa, final String nombreCuenta, final String fecha, float valor) throws DbException {
    Empresa empresa = null;
    Cuenta cuenta = null;
    Periodo periodo = null;
    empresa = getEmpresaDb().obtenerEmpresa(nombreEmpresa);
    if (empresa != null) {
      Optional<Cuenta> optionalCuenta = empresa.getCuentas().stream().filter(c -> c.getNombre().equals(nombreCuenta)).findFirst();
      if (optionalCuenta.isPresent()) {
        cuenta = optionalCuenta.get();
        Optional<Periodo> optionalPeriodo = cuenta.getPeriodos().stream().filter(p -> p.getFecha().equals(fecha)).findFirst();
        if (optionalPeriodo.isPresent()) {
          periodo = optionalPeriodo.get();
          periodo.setValor(valor);
        } else {
          periodo = new Periodo();
          periodo.setFecha(fecha);
          periodo.setValor(valor);
        }
      } else {
        cuenta = new Cuenta();
        cuenta.setPeriodos(new ArrayList<>());
        cuenta.setNombre(nombreCuenta);
        periodo = new Periodo();
        periodo.setFecha(fecha);
        periodo.setValor(valor);
      }
    } else {
      empresa = new Empresa();
      empresa.setNombre(nombreEmpresa);
      empresa.setCuentas(new ArrayList<>());
      cuenta = new Cuenta();
      cuenta.setPeriodos(new ArrayList<>());
      cuenta.setNombre(nombreCuenta);
      periodo = new Periodo();
      periodo.setFecha(fecha);
      periodo.setValor(valor);
    }
    cuenta.getPeriodos().remove(periodo);
    empresa.getCuentas().remove(cuenta);
    cuenta.getPeriodos().add(periodo);
    empresa.getCuentas().add(cuenta);
    getEmpresaDb().guardarEmpresa(empresa);
  }

  public EmpresaDb getEmpresaDb() {
    if (empresaDb != null) {
      return empresaDb;
    }
    empresaDb = new EmpresaDb();
    return empresaDb;
  }

}
