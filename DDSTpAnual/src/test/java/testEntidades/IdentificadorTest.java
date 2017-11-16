package testEntidades;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

import org.junit.BeforeClass;
import org.junit.Test;
import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;

import ar.org.utn.ddstpanual.exception.ArbolException;
import ar.org.utn.ddstpanual.exception.DbException;
import fixture.FixtureIndicador;

public class IdentificadorTest implements WithGlobalEntityManager {

  private static FixtureIndicador fixture;

  @BeforeClass
  public static void inciarComponentes() {
    fixture = new FixtureIndicador();
    fixture.iniciarCuentas();
  }

  @Test
  public void testEjecutarIndicadorSumaYDivisionCorrectamente() throws DbException {
    try {
      assertEquals((Double) 9.0, fixture.indTest1.ejecutarIndicador("2011", fixture.empTest));
    } catch (ArbolException e) {
      fail("Ha ocurrido un error al ejecutar el test");
    }
  }

  @Test
  public void testEjecutarIndicadorSumaYDivisionConDenomindorCero() throws DbException {
    try {
      assertEquals((Double) (-1.0), fixture.indTest1.ejecutarIndicador("2014", fixture.empTest));
    } catch (ArbolException e) {
      fail("Ha ocurrido un error al ejecutar el test");
    }
  }

  @Test(expected = DbException.class)
  public void testEjecutarIndicadorConParametrosInexistentesEnDb() throws DbException {
    try {
      fixture.indErr.ejecutarIndicador("2011", fixture.empTest);
    } catch (ArbolException e) {
      fail("Ha ocurrido un error al ejecutar el test");
    }
  }

  @Test
  public void testEjecutarIndicadorObteniendoResultadoNegativo() throws ArbolException, DbException {
    assertEquals((Double) (-1.0), fixture.indTest2.ejecutarIndicador("2013", fixture.empTest));
  }

  @Test
  public void testEjecutarIndicadorConCuentasInexistentes() throws ArbolException, DbException {
    assertEquals(-1.00, fixture.indTest4.ejecutarIndicador("2011", fixture.empTest), 0.0);
  }

  @Test(expected = ArbolException.class)
  public void testEjecutarIndicadorRecursivo() throws ArbolException {
    entityManager().getTransaction().begin();
    entityManager().persist(fixture.indErr);
    try {
      fixture.indErr.ejecutarIndicador("2011", fixture.empTest);
    } catch (DbException e) {
      fail("Ha ocurrido un error al consultar la base de datos en la ejecución del test");
    } finally {
      entityManager().getTransaction().rollback();
    }

  }

  @Test
  public void testGuardarArbolPostEjecucionIndicador() throws ArbolException, DbException {
    fixture.indTest3.ejecutarIndicador("2011", fixture.empTest);
    assertNotNull(fixture.indTest3.getArbol().getRoot());
  }
}
