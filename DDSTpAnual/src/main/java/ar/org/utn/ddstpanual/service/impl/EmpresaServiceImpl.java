package ar.org.utn.ddstpanual.service.impl;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import ar.org.utn.ddstpanual.dao.EmpresaDao;
import ar.org.utn.ddstpanual.dao.impl.EmpresaDaoImpl;
import ar.org.utn.ddstpanual.exception.DaoException;
import ar.org.utn.ddstpanual.exception.ServiceException;
import ar.org.utn.ddstpanual.model.Cuenta;
import ar.org.utn.ddstpanual.model.Empresa;
import ar.org.utn.ddstpanual.service.EmpresaService;

public class EmpresaServiceImpl implements EmpresaService {

  EmpresaDao empresaDao;

  public void subirExcel(FileInputStream file) throws ServiceException {

    Map<String, Empresa> empresas = new HashMap<String, Empresa>();
    Map<String, Cuenta> cuentas;
    Map<String, Float> valores;
    Empresa empresa;
    Cuenta cuenta;

    try {

      XSSFWorkbook workbook = new XSSFWorkbook(file);
      XSSFSheet sheet = workbook.getSheetAt(0);
      Iterator<Row> rowIterator = sheet.iterator();
      Row row;

      rowIterator.next();
      while (rowIterator.hasNext()) {
        row = rowIterator.next();
        empresa = new Empresa();
        cuenta = new Cuenta();

        String nombreEmpresa = row.getCell(0).getStringCellValue();
        String nombreCuenta = row.getCell(1).getStringCellValue();
        String periodo = String.valueOf((float) row.getCell(2).getNumericCellValue());
        Float valor = (float) row.getCell(3).getNumericCellValue();

        if (empresas.containsKey(nombreEmpresa)) {
          empresa = empresas.get(nombreEmpresa);

          if (empresa.getCuentas().containsKey(nombreCuenta)) {
            cuenta = empresa.getCuentas().get(nombreCuenta);
            if (cuenta.getValores().containsKey(periodo)) {
              System.out.println("Error, ya se ingreso un valor para este periodo");
            } else {
              empresas.get(nombreEmpresa).getCuentas().get(nombreCuenta).getValores().put(periodo,
                  valor);
            }
          } else {
            cuenta.setNombre(nombreCuenta);
            valores = new HashMap<String, Float>();
            valores.put(periodo, valor);
            cuenta.setValores(valores);
            empresas.get(nombreEmpresa).getCuentas().put(nombreCuenta, cuenta);
          }
        } else {
          valores = new HashMap<String, Float>();
          cuentas = new HashMap<String, Cuenta>();
          empresa.setNombre(nombreEmpresa);
          cuenta.setNombre(nombreCuenta);
          valores.put(periodo, valor);
          cuenta.setValores(valores);
          cuentas.put(nombreCuenta, cuenta);
          empresa.setCuentas(cuentas);
          empresas.put(nombreEmpresa, empresa);
        }

      }
      getEmpresaDao().saveEmpresas(empresas);
      workbook.close();
    } catch (IOException ex) {
      ex.printStackTrace();
    } catch (DaoException e) {
      e.printStackTrace();
    }
  }

  public EmpresaDao getEmpresaDao() {
    if (empresaDao != null) {
      return empresaDao;
    }
    empresaDao = new EmpresaDaoImpl();
    return empresaDao;
  }

}
