package testDbTest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;
import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;

import ar.org.utn.ddstpanual.exception.DbException;
import ar.org.utn.ddstpanual.model.Indicador;
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
		try {
			entityManager().getTransaction().begin();
			fixture.indicadorDb.guardarIndicador(fixture.indTest1);
		} catch (DbException e) {
			fail("Error al ejecutar el test");
		} finally {
			entityManager().getTransaction().rollback();
		}

	}

	// Test sobre la existencia de datos
	@Test
	public void testExisteIndicador() {
		try {
			fixture.indicadorDb.guardarIndicador(fixture.indTest2);
			assertTrue(fixture.indicadorDb.exists(fixture.indTest2));
		} catch (DbException e) {
			fail("Error al ejecutar el test");
		} finally {
			entityManager().getTransaction().rollback();
		}
	}

	@Test
	public void testNoExisteIndicador() {
		try {
			assertFalse(fixture.indicadorDb.exists(fixture.indTest3));
		} catch (DbException e) {
			fail("Error al ejecutar el test");
		} finally {
			entityManager().getTransaction().rollback();
		}
	}

	// Test sobre los datos guardados
	@Test
	public void testObtenerIndicadorDeUsuario() {
		try {
			if (!fixture.indicadorDb.exists(fixture.indTest1)) {
				fixture.indicadorDb.guardarIndicador(fixture.indTest1);
			}

			if (!fixture.indicadorDb.exists(fixture.indTest2)) {
				fixture.indicadorDb.guardarIndicador(fixture.indTest2);
			}

			ArrayList<Indicador> lista = new ArrayList<Indicador>();
			lista.add(fixture.indTest1);
			lista.add(fixture.indTest2);

			assertTrue(fixture.indicadorDb.obtenerIndicadoresPorUsuario(fixture.usuario.getId()).containsAll(lista));
		} catch (DbException e) {
			fail("Error al ejecutar el test");
		} finally {
			entityManager().getTransaction().rollback();
		}
	}

	@Test
	public void testObtenerFormulaNoExistente() {
		try {
			assertEquals("([CuentaPrb1]+[CuentaPrb2])*[CuentaPrb]",
					fixture.indicadorDb.obtenerIndicador(fixture.indTest4.getNombre()).getFormula());
		} catch (DbException e) {
			fail("Error al ejecutar el test");
		} finally {
			entityManager().getTransaction().rollback();
		}
	}
}
