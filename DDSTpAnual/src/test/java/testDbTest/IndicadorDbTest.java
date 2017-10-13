package testDbTest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import ar.org.utn.ddstpanual.exception.ArchivoException;
import ar.org.utn.ddstpanual.model.Indicador;

public class IndicadorDbTest {


  Indicador indicador = null;
  Indicador indicadorBusqueda1;
  Indicador indicadorBusqueda2;
  Indicador indicadorPrueba;

  @Before
  public void init() {

    indicadorBusqueda1 = new Indicador("IndicadorBusqueda1", "[CuentaA]*2");
    indicadorBusqueda2 = new Indicador("IndicadorBusqueda2", "[CuentaA]*{IndicadorBusqueda1}");
    indicadorPrueba = new Indicador("roa","[cuenta1]*45");
  }

//  // Test guardar indicador en el archivo
//  @Test
//  public void testGuardarIndicadorEnArchivo() throws ArchivoException {
//    indicador = new Indicador("IndicadorArchivo", "(2*[CuentaA])");
//    indicadorDb.guardarIndicador(indicador);
//  }
//
//  // Test sobre la existencia de datos en un archivo
//  @Test
//  public void testExisteIndicadorEnArchivo() throws ArchivoException {
//    indicador = new Indicador("ROE", "([beneficioNeto]/[patrimonioNeto])*100");
//    assertTrue(indicadorDb.exists(indicador));
//  }
//
//  @Test
//  public void testNoExisteIndicadorEnArchivo() throws ArchivoException {
//    indicador = new Indicador("IndicadorArchivoNoExistente", "[CuentaB]+200");
//    assertFalse(indicadorDb.exists(indicador));
//  }
//
//  // Test sobre los datos guardados
//  @Test
//  public void testObtenerIndicadorGuardado() throws ArchivoException {
//    if (!indicadorDb.exists(indicadorBusqueda1)) {
//      indicadorDb.guardarIndicador(indicadorBusqueda1);
//    }
//
//    for (Indicador indicador : indicadorDb.obtenerIndicadores()) {
//      if (indicador.getNombre().equals("IndicadorBusqueda1")) {
//        System.out.println(indicador.toString());
//      }
//    } ;
//  }
//
//  @Test
//  public void testObtenerFormulaNoExistente() throws ArchivoException {
//    assertEquals("", indicadorDb.obtenerFormula("IndicadorInexistente"));
//  }
//  @Test
//  public void testObtenerNombreIndicador() throws ArchivoException {
//    assertEquals("roe",indicadorDb.obtenerNombre("[activoCorriente]/[pasivoTotal]"));
//  }
//  
//  @Test
//  public void testExisteIndicador() {
//    
//  }
  
}
