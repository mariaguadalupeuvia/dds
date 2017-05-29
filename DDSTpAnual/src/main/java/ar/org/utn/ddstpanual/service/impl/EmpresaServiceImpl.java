package ar.org.utn.ddstpanual.service.impl;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Iterator;

import ar.org.utn.ddstpanual.archivo.EmpresaArchivo;
import ar.org.utn.ddstpanual.archivo.impl.EmpresaArchivoImpl;
import ar.org.utn.ddstpanual.exception.ServiceException;
import ar.org.utn.ddstpanual.log.LogData;
import ar.org.utn.ddstpanual.model.EmpresaExcel;
import ar.org.utn.ddstpanual.service.EmpresaService;

public class EmpresaServiceImpl implements EmpresaService {

  EmpresaArchivo empresaArchivo;

  public void subirExcel(FileInputStream file) throws ServiceException {

    EmpresaExcel empresaExcel = new EmpresaExcel();
    try {
      XSSFWorkbook workbook = new XSSFWorkbook(file);
      XSSFSheet sheet = workbook.getSheetAt(0);
      Iterator<Row> rowIterator = sheet.iterator();
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
          LogData.EscribirLogText(
              row.getRowNum() + ": Ya se ingreso un valor para este periodo en el archivo.");
        }
      }
      workbook.close();
    } catch (IOException ex) {
      ex.printStackTrace();
    }
  }

  public EmpresaArchivo getEmpresaArchivo() {
    if (empresaArchivo != null) {
      return empresaArchivo;
    }
    empresaArchivo = new EmpresaArchivoImpl();
    return empresaArchivo;
  }
}
