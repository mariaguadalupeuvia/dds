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
import ar.org.utn.ddstpanual.dto.EmpresaDto;
import ar.org.utn.ddstpanual.exception.DaoException;
import ar.org.utn.ddstpanual.exception.ServiceException;
import ar.org.utn.ddstpanual.service.EmpresaService;

public class EmpresaServiceImpl implements EmpresaService {

  EmpresaDao empresaDao;

  public void subirExcel(FileInputStream file) throws ServiceException {

    Map<String, EmpresaDto> empresas = new HashMap<String, EmpresaDto>();
    EmpresaDto empresaDto = new EmpresaDto();
    String key;

    try {

      XSSFWorkbook workbook = new XSSFWorkbook(file);
      XSSFSheet sheet = workbook.getSheetAt(0);
      Iterator<Row> rowIterator = sheet.iterator();
      Row row;
      rowIterator.next();
      while (rowIterator.hasNext()) {
        row = rowIterator.next();

        System.out.println("RowNum: " + row.getRowNum());
        empresaDto = new EmpresaDto();
        empresaDto.setNombreEmpresa(row.getCell(0).getStringCellValue());
        empresaDto.setNombreCuenta(row.getCell(1).getStringCellValue());
        empresaDto.setFecha(String.valueOf((float) row.getCell(2).getNumericCellValue()));
        empresaDto.setValor((float) row.getCell(3).getNumericCellValue());
        key = empresaDto.getNombreEmpresa() + "-" + empresaDto.getNombreCuenta() + "-"
            + empresaDto.getFecha();

        if (!empresas.containsKey(key)) {
          empresas.put(key, empresaDto);
        } else {
          System.out.println(
              row.getRowNum() + ": Ya se ingreso un valor para este periodo en el archivo.");
        }

      }
      getEmpresaDao().saveEmpresas(empresas);
      workbook.close();
    } catch (IOException ex) {
      ex.printStackTrace();
    } catch (DaoException e) {
      throw new ServiceException(e.getMessage());
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
