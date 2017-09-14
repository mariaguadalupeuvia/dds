package testArchivo;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import ar.org.utn.ddstpanual.archivo.impl.EmpresaArchivoImpl;
import ar.org.utn.ddstpanual.exception.ArchivoException;
import ar.org.utn.ddstpanual.model.Empresa;
import ar.org.utn.ddstpanual.model.EmpresaExcel;
import ar.org.utn.ddstpanual.model.Periodo;

public class EmpresaArchivoTest {
  EmpresaArchivoImpl empresaArchivo;
  List<Periodo> periodos = new ArrayList<Periodo>();
  EmpresaExcel empresa;

  EmpresaExcel empresaBusqueda1;
  EmpresaExcel empresaBusqueda2;

  @Before
  public void init() {
    empresaArchivo = new EmpresaArchivoImpl();

    empresaBusqueda1 = new EmpresaExcel("EmpresaBusqueda", "CuentaA", "2016", 200);
    empresaBusqueda2 = new EmpresaExcel("EmpresaBusqueda", "CuentaA", "2017", 300);

  }

  // Test guardar empresa
  @Test
  public void testGuardarEmpresaEnExcel() throws ArchivoException {
    empresa = new EmpresaExcel("EmpresaArchivo", "Cuenta1", "2016", 500);
    empresaArchivo.guardarEmpresa(empresa);
  }

  // Test sobre la existencia de datos en un archivo
  @Test
  public void testExisteEmpresaEnArchivo() throws ArchivoException {
    empresa = new EmpresaExcel("EmpresaArchivo", "Cuenta1", "2016", 500);
    assertTrue(empresaArchivo.exists(empresa));
  }

  @Test
  public void testNoExisteEmpresaEnArchivo() throws ArchivoException {
    empresa = new EmpresaExcel("EmpresaArchivo", "Cuenta2", "2016", 500);
    assertFalse(empresaArchivo.exists(empresa));
  }

  // Test sobre los datos guardados
  @Test
  public void testObtenerEmpresaGuardada() throws ArchivoException {
    if (!empresaArchivo.exists(empresaBusqueda1)) {
      empresaArchivo.guardarEmpresa(empresaBusqueda1);
    }

    if (!empresaArchivo.exists(empresaBusqueda2)) {
      empresaArchivo.guardarEmpresa(empresaBusqueda2);
    }

    for (Empresa empresa : empresaArchivo.obtenerEmpresas()) {
      if (empresa.getNombre().equals("EmpresaBusqueda")) {
        System.out.println(empresa.toString());
      }
    } ;
  }

  @Test
  public void testEncontrarPeriodosDeEmpresaGuardadas() throws ArchivoException {
    if (!empresaArchivo.exists(empresaBusqueda1)) {
      empresaArchivo.guardarEmpresa(empresaBusqueda1);
    }

    if (!empresaArchivo.exists(empresaBusqueda2)) {
      empresaArchivo.guardarEmpresa(empresaBusqueda2);
    }
    for (Periodo periodo : empresaArchivo.obtenerPeriodos("EmpresaBusqueda")) {
      System.out.println(periodo);
    }
  }

  @Test(expected = ArchivoException.class)
  public void testBuscarPeriodosDeUnaEmpresaQueNoExiste() throws ArchivoException {
    empresaArchivo.obtenerPeriodos("EmpresaInexistente");
  }
}
