package ar.org.utn.ddstpanual.service.impl;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Iterator;

import ar.org.utn.ddstpanual.exception.ServiceException;
import ar.org.utn.ddstpanual.service.EmpresaService;

public class EmpresaServiceImpl implements EmpresaService {

  public void subirExcel(FileInputStream file) throws ServiceException {

    try {
      XSSFWorkbook workbook = new XSSFWorkbook(file);
      /*
       * Obtenemos la primera pestaña a la que se quiera procesar indicando el indice.
       * 
       * Una vez obtenida la hoja excel con las filas que se quieren leer obtenemos el iterator
       * 
       * que nos permite recorrer cada una de las filas que contiene.
       */
      XSSFSheet sheet = workbook.getSheetAt(0);
      Iterator<Row> rowIterator = sheet.iterator();
      Row row;

      // Recorremos todas las filas para mostrar el contenido de cada celda
      while (rowIterator.hasNext()) {
        row = rowIterator.next();
        // Obtenemos el iterator que permite recorres todas las celdas de una fila
        Iterator<Cell> cellIterator = row.cellIterator();
        Cell celda;

        while (cellIterator.hasNext()) {
          celda = cellIterator.next();
          // Dependiendo del formato de la celda el valor se debe mostrar como String, Fecha,
          // boolean,
          // entero...
          switch (celda.getCellTypeEnum()) {
            case NUMERIC:// CELL_TYPE_NUMERIC:
              if (DateUtil.isCellDateFormatted(celda)) {
                System.out.println(celda.getDateCellValue());
              } else {
                System.out.println(celda.getNumericCellValue());
              }
              break;
            case STRING:
              System.out.println(celda.getStringCellValue());
              break;
            case BOOLEAN:
              System.out.println(celda.getBooleanCellValue());
              break;
            case BLANK:
              break;
            case ERROR:
              break;
            case FORMULA:
              break;
            case _NONE:
              break;
            default:
              break;
          }
        }
      }
      // cerramos el libro excel
      workbook.close();
    } catch (IOException ex) {
      ex.printStackTrace();
    }
  }

}
