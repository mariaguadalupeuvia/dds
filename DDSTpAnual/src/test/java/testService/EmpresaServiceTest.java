package testService;

import org.apache.poi.openxml4j.exceptions.NotOfficeXmlFileException;
import org.junit.Before;
import org.junit.Test;

import ar.org.utn.ddstpanual.exception.ServiceException;
import ar.org.utn.ddstpanual.model.Empresa;
import ar.org.utn.ddstpanual.model.EmpresaExcel;
import ar.org.utn.ddstpanual.service.impl.EmpresaServiceImpl;

public class EmpresaServiceTest {
  EmpresaServiceImpl empresaService = null;

  @Before
  public void init() {
    empresaService = new EmpresaServiceImpl();
  }

  public String iniciarArchivo(final String nombreArchivo) {
    final String path = System.getProperty("user.dir");
    final String rutaArchivo = path + "\\src\\main\\resources\\test\\" + nombreArchivo;
    return rutaArchivo;
  }

  // Test sobre la carga del archivo
  @Test
  public void testSubirExcel() throws ServiceException {
    empresaService.subirExcel(iniciarArchivo("Carga1.xlsx"));
  }

  @Test(expected = ServiceException.class)
  public void testSubirArchivoInexistente() throws ServiceException {
    empresaService.subirExcel(iniciarArchivo("Caso1"));
  }

  @Test(expected = NotOfficeXmlFileException.class)
  public void testSubirArchivoNoExcel() throws ServiceException {
    empresaService.subirExcel(iniciarArchivo("CargaCSV.csv"));
  }

  // Test sobre la obtencion de datos del archivo
  @Test
  public void testObtenerDatosEmpresa() throws ServiceException {
    Empresa empresaTest = new Empresa();

    for (Empresa e : empresaService.obtenerEmpresas()) {
      if (e.getNombre().equals("Facebook")) {
        empresaTest = e;
      }
    }

    for (EmpresaExcel empresa : empresaService.buscar(empresaTest, null, null)) {
      System.out.println(empresa.toString());
    } ;
  }

}
