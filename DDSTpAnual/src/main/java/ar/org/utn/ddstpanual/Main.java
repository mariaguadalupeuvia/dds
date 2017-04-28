package ar.org.utn.ddstpanual;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.URL;

import ar.org.utn.ddstpanual.exception.ServiceException;
import ar.org.utn.ddstpanual.service.EmpresaService;
import ar.org.utn.ddstpanual.service.impl.EmpresaServiceImpl;

public class Main {

  public static void main(String[] args){
    EmpresaService empresaService = new EmpresaServiceImpl();
    URL resource = Main.class.getClassLoader().getResource("Carga1.xlsx");
    
    File archivo = new File(resource.getPath());
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
