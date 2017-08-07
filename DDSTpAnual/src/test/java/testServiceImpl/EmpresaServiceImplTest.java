package testServiceImpl;

import org.apache.poi.openxml4j.exceptions.NotOfficeXmlFileException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import ar.org.utn.ddstpanual.exception.ServiceException;
import ar.org.utn.ddstpanual.service.impl.EmpresaServiceImpl;

public class EmpresaServiceImplTest {
  EmpresaServiceImpl service = null;

  @Before
  public void init() {
    service = new EmpresaServiceImpl();
  }

  public String iniciarArchivo(final String nombreArchivo) {
    final String path = System.getProperty("user.dir");
    final String rutaArchivo = path + "\\src\\main\\resources\\test\\" + nombreArchivo;
    return rutaArchivo;
  }

  // Test sobre la carga del archivo
  @Test
  public void testSubirExcel() throws ServiceException {
    service.subirExcel(iniciarArchivo("Carga1.xlsx"));
  }

  @Test(expected = ServiceException.class)
  public void testSubirArchivoInexistente() throws ServiceException {
    service.subirExcel(iniciarArchivo("Caso1"));
  }

  @Test(expected = NotOfficeXmlFileException.class)
  public void testSubirArchivoNoExcel() throws ServiceException {
    service.subirExcel(iniciarArchivo("CargaCSV.csv"));
  }

  // Test sobre la obtencion de datos del archivo
  // buscar ()
  // obtenerEmpresas ()

  @Test
  public void testObtenerIndicadores() throws ServiceException {
    service.obtenerEmpresas();
  }

  @Test
  public void testObtenerPeriodos() throws ServiceException {
    Assert.assertNotNull(service.obtenerPeriodos());
  }

}
