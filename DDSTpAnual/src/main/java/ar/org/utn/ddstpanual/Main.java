package ar.org.utn.ddstpanual;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import ar.org.utn.ddstpanual.exception.ServiceException;
import ar.org.utn.ddstpanual.service.EmpresaService;
import ar.org.utn.ddstpanual.service.impl.EmpresaServiceImpl;

public class Main {

  public static void main(String[] args){
    EmpresaService empresaService = new EmpresaServiceImpl();
    

    File archivo = new File("C:\\Users\\Compumar\\git\\2017-vn-group-24\\DDSTpAnual\\src\\main\\resources\\Carga1.xlsx");
    try {
      FileInputStream file = new FileInputStream(archivo);
      empresaService.subirExcel(file);
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    } catch (ServiceException ex) {
      ex.printStackTrace();
    }
  }
  
}
