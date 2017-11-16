package testService;

import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

import ar.org.utn.ddstpanual.exception.ServiceException;
import ar.org.utn.ddstpanual.service.EmpresaService;

public class EmpresaServiceTest {
  EmpresaService empresaService = null;

  @Before
  public void init() {
    empresaService = new EmpresaService();
  }

  public InputStream iniciarArchivo(final String nombreArchivo) throws ServiceException {
    FileInputStream archivoInput;
    final String path = System.getProperty("user.dir");
    final String rutaArchivo = path + "\\src\\main\\resources\\test\\" + nombreArchivo;
    File archivo = new File(rutaArchivo);
    try {
      archivoInput = new FileInputStream(archivo);
    } catch (FileNotFoundException e) {
      throw new ServiceException(e.getMessage());
    }

    return archivoInput;
  }

  // Test sobre la carga del archivo
  @Test
  public void testSubirExcel() throws ServiceException {
    empresaService.subirArchivo(iniciarArchivo("CargarDatosEmpresas.xls"));
  }

  @Test(expected = ServiceException.class)
  public void testSubirArchivoInexistente() throws ServiceException {
    empresaService.subirArchivo(iniciarArchivo("Caso1"));
  }

  @Test(expected = ServiceException.class)
  public void testSubirArchivoNoExcel() throws ServiceException {
    empresaService.subirArchivo(iniciarArchivo("CargaCSV.csv"));
  }
}
