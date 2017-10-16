package testDbTest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import ar.org.utn.ddstpanual.db.IndicadorDb;
import ar.org.utn.ddstpanual.db.impl.IndicadorDbImpl;
import ar.org.utn.ddstpanual.exception.DbException;
import ar.org.utn.ddstpanual.model.Indicador;

public class IndicadorDbTest {

  IndicadorDb indicadorDb = null;
  Indicador indicador = null;
  Indicador indicadorBusqueda1;
  Indicador indicadorBusqueda2;
  Indicador indicadorPrueba;

  @Before
  public void init() {
    indicadorDb = new IndicadorDbImpl();

    indicadorBusqueda1 = new Indicador("IndicadorBusqueda1", "[CuentaA]*2");
    indicadorBusqueda2 = new Indicador("IndicadorBusqueda2", "[CuentaA]*{IndicadorBusqueda1}");
    indicadorPrueba = new Indicador("roa", "[cuenta1]*45");
  }

  // Test guardar indicador en el archivo
  @Test
  public void testGuardarIndicadorEnArchivo() throws DbException {
    indicador = new Indicador("IndicadorArchivo", "(2*[CuentaA])");
    indicadorDb.guardarIndicador(indicador);
  }

  // Test sobre la existencia de datos en un archivo
  @Test
  public void testExisteIndicadorEnArchivo() throws DbException {
    indicador = new Indicador("ROE", "([beneficioNeto]/[patrimonioNeto])*100");
    assertTrue(indicadorDb.exists(indicador));
  }

  @Test
  public void testNoExisteIndicadorEnArchivo() throws DbException {
    indicador = new Indicador("IndicadorArchivoNoExistente", "[CuentaB]+200");
    assertFalse(indicadorDb.exists(indicador));
  }

  // Test sobre los datos guardados
  @Test
  public void testObtenerIndicadorGuardado() throws DbException {
    if (!indicadorDb.exists(indicadorBusqueda1)) {
      indicadorDb.guardarIndicador(indicadorBusqueda1);
    }

    for (Indicador indicador : indicadorDb.obtenerIndicadores()) {
      if (indicador.getNombre().equals("IndicadorBusqueda1")) {
        System.out.println(indicador.toString());
      }
    } ;
  }

  @Test
  public void testObtenerFormulaNoExistente() throws DbException {
    assertEquals("", indicadorDb.obtenerFormula("IndicadorInexistente"));
  }

  @Test
  public void testObtenerNombreIndicador() throws DbException {
    assertEquals("roe", indicadorDb.obtenerNombre("[activoCorriente]/[pasivoTotal]"));
  }

  @Test
  public void testExisteIndicador() {

  }

}
