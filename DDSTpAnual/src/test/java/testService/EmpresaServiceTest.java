package testService;

import org.junit.Before;
import org.junit.Test;

import ar.org.utn.ddstpanual.exception.ServiceException;
import ar.org.utn.ddstpanual.model.Empresa;
import ar.org.utn.ddstpanual.model.EmpresaExcel;
import ar.org.utn.ddstpanual.service.EmpresaService;

public class EmpresaServiceTest {
  EmpresaService empresaService = null;

  @Before
  public void init() {
    empresaService = new EmpresaService();
  }

  public String iniciarArchivo(final String nombreArchivo) {
    final String path = System.getProperty("user.dir");
    final String rutaArchivo = path + "\\src\\main\\resources\\test\\" + nombreArchivo;
    return rutaArchivo;
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

  // Test sobre la obtencion de datos del archivo
  @Test
  public void testObtenerDatosEmpresa() throws ServiceException {
    Empresa empresaTest = new Empresa();

    for (Empresa e : empresaService.obtenerEmpresas()) {
      if (e.getNombre().equals("Tenaris")) {
        empresaTest = e;
      }
    }

    for (EmpresaExcel empresa : empresaService.buscar(empresaTest, null, null)) {
      System.out.println(empresa.toString());
    } ;
  }

}
