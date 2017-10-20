package testDbTest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import ar.org.utn.ddstpanual.db.IndicadorDb;
import ar.org.utn.ddstpanual.db.UsuarioDb;
import ar.org.utn.ddstpanual.exception.DbException;
import ar.org.utn.ddstpanual.model.Indicador;
import ar.org.utn.ddstpanual.model.Usuario;

public class IndicadorDbTest {

  IndicadorDb indicadorDb = null;
  Indicador indicador = null;
  Indicador indicadorBusqueda1;
  Indicador indicadorBusqueda2;
  Indicador indicadorPrueba;
  Usuario usuario;

  @Before
  public void init() throws DbException {
    indicadorDb = new IndicadorDb();
    UsuarioDb usuarioDb = new UsuarioDb();
    usuario = usuarioDb.obtenerUsuario("ivan");

    indicadorBusqueda1 = new Indicador("IndicadorBusqueda1", "[pasivoNoCorriente]*2", usuario.getId());
    indicadorBusqueda2 = new Indicador("IndicadorBusqueda2", "[pasivoNoCorriente]*{IndicadorBusqueda1}", usuario.getId());
  }

  // Test guardar indicador
  @Test
  public void testGuardar() throws DbException {
    indicador = new Indicador("IndicadorDb", "(2*[pasivoCorriente])", usuario.getId());
    indicadorDb.guardarIndicador(indicador);
  }

  // Test sobre la existencia de datos
  @Test
  public void testExisteIndicador() throws DbException {
    indicador = new Indicador("ROE", "([beneficioNeto]/[patrimonioNeto])*100", usuario.getId());
    indicadorDb.guardarIndicador(indicador);
    assertTrue(indicadorDb.exists(indicador));
  }

  @Test
  public void testNoExisteIndicador() throws DbException {
    indicador = new Indicador("IndicadorArchivoNoExistente", "[CuentaB]+200", usuario.getId());
    assertFalse(indicadorDb.exists(indicador));
  }

  // Test sobre los datos guardados
  @Test
  public void testObtenerIndicadorDeUsuario() throws DbException {
    if (!indicadorDb.exists(indicadorBusqueda1)) {
      indicadorDb.guardarIndicador(indicadorBusqueda1);
    }

    if (!indicadorDb.exists(indicadorBusqueda2)) {
      indicadorDb.guardarIndicador(indicadorBusqueda2);
    }

    ArrayList<Indicador> lista = new ArrayList<Indicador>();
    lista.add(indicadorBusqueda1);
    lista.add(indicadorBusqueda2);

    assertTrue(indicadorDb.obtenerIndicadoresPorUsuario(usuario.getId()).containsAll(lista));
  }

  @Test
  public void testObtenerFormulaNoExistente() throws DbException {
    assertEquals("", indicadorDb.obtenerFormula("IndicadorInexistente"));
  }

  @Test
  public void testObtenerNombreIndicador() throws DbException {
    assertEquals("ROE", indicadorDb.obtenerNombre("([beneficioNeto]/[patrimonioNeto])*100"));
  }

}
