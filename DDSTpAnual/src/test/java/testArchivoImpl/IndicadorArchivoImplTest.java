package testArchivoImpl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import ar.org.utn.ddstpanual.archivo.impl.IndicadorArchivoImpl;
import ar.org.utn.ddstpanual.exception.ArchivoException;
import ar.org.utn.ddstpanual.model.Indicador;

public class IndicadorArchivoImplTest {

  IndicadorArchivoImpl indicadorArchivo = null;
  Indicador indicador = null;
  Indicador indicadorBusqueda1;
  Indicador indicadorBusqueda2;

  @Before
  public void init() {
    indicadorArchivo = new IndicadorArchivoImpl();

    indicadorBusqueda1 = new Indicador("IndicadorBusqueda1", "[CuentaA]*2");
    indicadorBusqueda2 = new Indicador("IndicadorBusqueda2", "[CuentaA]*{IndicadorBusqueda1}");
  }

  // Test guardar indicador en el archivo
  @Test
  public void testGuardarIndicadorEnArchivo() throws ArchivoException {
    indicador = new Indicador("IndicadorArchivo", "(2*[CuentaA])");
    indicadorArchivo.guardarIndicador(indicador);
  }

  // Test sobre la existencia de datos en un archivo
  @Test
  public void testExisteIndicadorEnArchivo() throws ArchivoException {
    indicador = new Indicador("ROE", "([beneficioNeto]/[patrimonioNeto])*100");
    assertTrue(indicadorArchivo.exists(indicador));
  }

  @Test
  public void testNoExisteIndicadorEnArchivo() throws ArchivoException {
    indicador = new Indicador("IndicadorArchivoNoExistente", "[CuentaB]+200");
    assertFalse(indicadorArchivo.exists(indicador));
  }

  // Test sobre los datos guardados
  @Test
  public void testObtenerIndicadorGuardado() throws ArchivoException {
    if (!indicadorArchivo.exists(indicadorBusqueda1)) {
      indicadorArchivo.guardarIndicador(indicadorBusqueda1);
    }

    for (Indicador indicador : indicadorArchivo.obtenerIndicadores()) {
      if (indicador.getNombre().equals("IndicadorBusqueda1")) {
        System.out.println(indicador.toString());
      }
    } ;
  }

  @Test
  public void testObtenerFormulaROA() throws ArchivoException {
    assertEquals("({activoTotal}/[patrimonioNeto])*100", indicadorArchivo.obtenerFormula("ROA"));
  }

  @Test
  public void testObtenerFormulaNoExistente() throws ArchivoException {
    assertEquals("", indicadorArchivo.obtenerFormula("IndicadorInexistente"));
  }
}
