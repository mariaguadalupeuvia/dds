package testDbTest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.Before;
import org.junit.Test;
import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;

import ar.org.utn.ddstpanual.exception.DbException;
import fixture.FixtureIndicador;

public class IndicadorDbTest implements WithGlobalEntityManager {
	private FixtureIndicador fixture;

	@Before
	public void iniciarValores() throws DbException {
		fixture = new FixtureIndicador();
		fixture.cargarIndicadores();
	}

	// Test guardar indicador
	@Test
	public void testGuardar() {
		entityManager().getTransaction().begin();
		try {
			entityManager().persist(fixture.indTest1);
		} finally {
			entityManager().getTransaction().rollback();
		}

	}

	// Test sobre la existencia de datos
	@Test
	public void testExisteIndicador() {
		entityManager().getTransaction().begin();
		try {
			entityManager().persist(fixture.indTest2);
			assertTrue(fixture.indicadorDb.exists(fixture.indTest2, fixture.usuario));
		} catch (DbException e) {
			fail("Error al ejecutar el test");
		} finally {
			entityManager().getTransaction().rollback();
		}
	}

	@Test
	public void testNoExisteIndicador() {
		entityManager().getTransaction().begin();
		try {
			assertFalse(fixture.indicadorDb.exists(fixture.indTest3, fixture.usuario));
		} catch (DbException e) {
			fail("Error al ejecutar el test");
		} finally {
			entityManager().getTransaction().rollback();
		}
	}

	/*
	 * /// Test sobre los datos guardados
	 * 
	 * @Test public void testObtenerIndicadorDeUsuario() {
	 * entityManager().getTransaction().begin(); Indicador deuda = new
	 * Indicador("deuda", "[patrimonioNeto]-[pasivoTotal]"); Indicador roe = new
	 * Indicador("roe", "[activoCorriente]/[pasivoTotal]"); try {
	 * entityManager().persist(fixture.indTest1);
	 * entityManager().persist(fixture.indTest2);
	 * entityManager().persist(fixture.indTest3);
	 * entityManager().persist(fixture.indTest4); entityManager().persist(deuda);
	 * entityManager().persist(roe);
	 * 
	 * ArrayList<Indicador> lista = new ArrayList<Indicador>();
	 * lista.add(fixture.indTest1); lista.add(fixture.indTest2);
	 * lista.add(fixture.indTest3); lista.add(fixture.indTest4); lista.add(deuda);
	 * lista.add(roe);
	 * 
	 * assertEquals(lista,
	 * fixture.indicadorDb.obtenerIndicadoresPorUsuario(fixture.usuario.getId())); }
	 * catch (DbException e) { fail("Error al ejecutar el test"); } finally {
	 * entityManager().getTransaction().rollback(); } }
	 */
	@Test(expected = DbException.class)
	public void testObtenerFormulaNoExistente() throws DbException {
		entityManager().getTransaction().begin();
		try {
			assertEquals("([CuentaPrb1]+[CuentaPrb2])*[CuentaPrb]",
					fixture.indicadorDb.obtenerIndicador(fixture.indTest4.getNombre()).getFormula());
		} finally {
			entityManager().getTransaction().rollback();
		}
	}
}
