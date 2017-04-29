package DDSTpAnual;

import org.apache.poi.openxml4j.exceptions.NotOfficeXmlFileException;
import org.junit.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;

import ar.org.utn.ddstpanual.Main;
import ar.org.utn.ddstpanual.exception.ServiceException;
import ar.org.utn.ddstpanual.service.impl.EmpresaServiceImpl;

public class EmpresaServiceImplTest {
  File file = null;
  FileInputStream fileStream = null;
  EmpresaServiceImpl service = null;

  @Before
  public void init() {
    service = new EmpresaServiceImpl();
  }

  public void iniciarArchivo(String nombreArchivo) {
    URL resource = Main.class.getClassLoader().getResource(nombreArchivo);
    file = new File(resource.getPath());
    try {
      fileStream = new FileInputStream(file);
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    }
  }

  // Test sobre la carga del archivo
  @Test
  public void testSubirExcel() throws ServiceException {
    iniciarArchivo("Carga1.xlsx");
    service.subirExcel(fileStream);
  }

  @Test(expected = NullPointerException.class)
  public void testSubirArchivoInexistente() throws ServiceException {
    iniciarArchivo("Caso1");
    service.subirExcel(fileStream);
  }

  @Test(expected = NotOfficeXmlFileException.class)
  public void testSubirArchivoNoExcel() throws ServiceException {
    iniciarArchivo("CargaCSV.csv");
    service.subirExcel(fileStream);
  }

  // Test sobre los datos del archivo



  @After
  public void finish() {

    try {
      if (fileStream != null)
        fileStream.close();
    } catch (IOException ex) {
      ex.printStackTrace();
    }

  }

}
