package testDbTest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.Before;
import org.junit.Test;
import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;

import ar.org.utn.ddstpanual.db.EmpresaDb;
import ar.org.utn.ddstpanual.exception.DbException;
import ar.org.utn.ddstpanual.model.Cuenta;
import ar.org.utn.ddstpanual.model.Empresa;
import fixture.FixtureCuenta;
import fixture.FixtureEmpresa;

public class EmpresaDbTest implements WithGlobalEntityManager {
  FixtureEmpresa fixture;
  FixtureCuenta fixtureC;
  EmpresaDb empresaDb;

  @Before
  public void init() {
    empresaDb = new EmpresaDb();
    fixture = new FixtureEmpresa();
    fixtureC = new FixtureCuenta();
  }

  @Test
  public void testGuardarEmpresaX() {
    entityManager().getTransaction().begin();
    entityManager().persist(fixture.empresaX);
    try {
      assertTrue(empresaDb.exists(fixture.empresaX));
    } catch (DbException e) {
      fail("Ha ocurrido un error al consultar la base de datos en la ejecución del test");
    } finally {
      entityManager().getTransaction().rollback();
    }
  }

  @Test
  public void testGuardarEmpresaY() {
    entityManager().getTransaction().begin();
    entityManager().persist(fixture.empresaY);
    try {
      assertTrue(empresaDb.exists(fixture.empresaY));
    } catch (DbException e) {
      fail("Ha ocurrido un error al consultar la base de datos en la ejecución del test");
    } finally {
      entityManager().getTransaction().rollback();
    }
  }

  @Test
  public void testGuardarEmpresaZ() {
    entityManager().getTransaction().begin();
    entityManager().persist(fixture.empresaZ);
    try {
      assertTrue(empresaDb.exists(fixture.empresaZ));
    } catch (DbException e) {
      fail("Ha ocurrido un error al consultar la base de datos en la ejecución del test");
    } finally {
      entityManager().getTransaction().rollback();
    }
  }

  @Test
  public void testObtenerEmpresaX() {
    entityManager().getTransaction().begin();
    entityManager().persist(fixture.empresaX);
    try {
      assertEquals(fixture.empresaX, empresaDb.obtenerEmpresa("EmpresaX"));
    } catch (DbException e) {
      fail("Ha ocurrido un error al consultar la base de datos en la ejecución del test");
    } finally {
      entityManager().getTransaction().rollback();
    }
  }

  @Test
  public void testObtenerEmpresaY() {
    entityManager().getTransaction().begin();
    entityManager().persist(fixture.empresaY);
    try {
      assertEquals(fixture.empresaY, empresaDb.obtenerEmpresa("EmpresaY"));
    } catch (DbException e) {
      fail("Ha ocurrido un error al consultar la base de datos en la ejecución del test");
    } finally {
      entityManager().getTransaction().rollback();
    }
  }

  @Test
  public void testObtenerEmpresaZIgualPorNombre() {
    Empresa empresaZEquals = new Empresa("EmpresaZ");
    entityManager().getTransaction().begin();
    entityManager().persist(fixture.empresaZ);
    try {
      assertEquals(empresaZEquals, empresaDb.obtenerEmpresa("EmpresaZ"));
    } catch (DbException e) {
      fail("Ha ocurrido un error al consultar la base de datos en la ejecución del test");
    } finally {
      entityManager().getTransaction().rollback();
    }
  }

  @Test
  public void testEmpresaXSetNombreAEmpresaXZ() {
    entityManager().getTransaction().begin();
    entityManager().persist(fixture.empresaX);
    try {
      Empresa empresaXDB = empresaDb.obtenerEmpresa("EmpresaX");
      empresaXDB.setNombre("EmpresaXZ");
      entityManager().persist(empresaXDB);
      assertEquals("EmpresaXZ", empresaDb.obtenerEmpresa("EmpresaXZ").getNombre());
    } catch (DbException e) {
      fail("Ha ocurrido un error al consultar la base de datos en la ejecución del test");
    } finally {
      entityManager().getTransaction().rollback();
    }
  }

  @Test
  public void testEmpresaXCon4Cuentas() {
    entityManager().getTransaction().begin();
    entityManager().persist(fixture.empresaX);
    try {
      Empresa empresaXDB = empresaDb.obtenerEmpresa("EmpresaX");
      assertEquals(4, empresaXDB.getCuentas().size());
    } catch (DbException e) {
      fail("Ha ocurrido un error al consultar la base de datos en la ejecución del test");
    } finally {
      entityManager().getTransaction().rollback();
    }
  }

  @Test
  public void testEmpresaXTieneCuentaPasivoCorriente() {
    Cuenta pasivoCorriente = new Cuenta("PasivoCorriente");
    entityManager().getTransaction().begin();
    entityManager().persist(fixture.empresaX);
    try {
      Empresa empresaXDB = empresaDb.obtenerEmpresa("EmpresaX");
      assertTrue(empresaXDB.getCuentas().contains(pasivoCorriente));
    } catch (DbException e) {
      fail("Ha ocurrido un error al consultar la base de datos en la ejecución del test");
    } finally {
      entityManager().getTransaction().rollback();
    }
  }

  @Test
  public void testEmpresaYTieneCuentaActivoNoCorriente() {
    Cuenta activoNoCorriente = new Cuenta("ActivoNoCorriente");
    entityManager().getTransaction().begin();
    entityManager().persist(fixture.empresaY);
    try {
      Empresa empresaYDB = empresaDb.obtenerEmpresa("EmpresaY");
      assertTrue(empresaYDB.getCuentas().contains(activoNoCorriente));
    } catch (DbException e) {
      fail("Ha ocurrido un error al consultar la base de datos en la ejecución del test");
    } finally {
      entityManager().getTransaction().rollback();
    }
  }

  @Test
  public void testEmpresaYNoTieneCuentaPasivoCorriente() {
    Cuenta pasivoCorriente = new Cuenta("PasivoCorriente");
    entityManager().getTransaction().begin();
    entityManager().persist(fixture.empresaY);
    try {
      Empresa empresaYDB = empresaDb.obtenerEmpresa("EmpresaY");
      assertFalse(empresaYDB.getCuentas().contains(pasivoCorriente));
    } catch (DbException e) {
      fail("Ha ocurrido un error al consultar la base de datos en la ejecución del test");
    } finally {
      entityManager().getTransaction().rollback();
    }
  }

  @Test
  public void testEmpresaXActivoCorrientePeriodo2017Valor3000() {
    entityManager().getTransaction().begin();
    entityManager().persist(fixture.empresaX);
    try {
      Empresa empresaXDB = empresaDb.obtenerEmpresa("EmpresaX");
      assertEquals(3000.0, empresaXDB.obtenerValor("ActivoCorriente", "2017"), 0.0);
    } catch (DbException e) {
      fail("Ha ocurrido un error al consultar la base de datos en la ejecución del test");
    } finally {
      entityManager().getTransaction().rollback();
    }
  }

  @Test
  public void testEmpresaXActivoNoCorrientePeriodo2015Valor3000() {
    entityManager().getTransaction().begin();
    entityManager().persist(fixture.empresaX);
    try {
      Empresa empresaXDB = empresaDb.obtenerEmpresa("EmpresaX");
      assertEquals(3000.0, empresaXDB.obtenerValor("ActivoNoCorriente", "2015"), 0.0);
    } catch (DbException e) {
      fail("Ha ocurrido un error al consultar la base de datos en la ejecución del test");
    } finally {
      entityManager().getTransaction().rollback();
    }
  }

  @Test
  public void testEmpresaXPasivoCorrienteDePeriodo2017NoTieneValor3000() {
    entityManager().getTransaction().begin();
    entityManager().persist(fixture.empresaX);
    try {
      Empresa empresaXDB = empresaDb.obtenerEmpresa("EmpresaX");
      assertNotEquals(3000.0, empresaXDB.obtenerValor("PasivoCorriente", "2017"), 0.0);
    } catch (DbException e) {
      fail("Ha ocurrido un error al consultar la base de datos en la ejecución del test");
    } finally {
      entityManager().getTransaction().rollback();
    }
  }

  @Test
  public void testEmpresaYActivoCorrientePeriodo2016Valor2000() {
    entityManager().getTransaction().begin();
    entityManager().persist(fixture.empresaY);
    try {
      Empresa empresaYDB = empresaDb.obtenerEmpresa("EmpresaY");
      assertEquals(2000.0, empresaYDB.obtenerValor("ActivoCorriente", "2016"), 0.0);
    } catch (DbException e) {
      fail("Ha ocurrido un error al consultar la base de datos en la ejecución del test");
    } finally {
      entityManager().getTransaction().rollback();
    }
  }

  @Test
  public void testEmpresaYVentasPeriodo2016Valor10000() {
    entityManager().getTransaction().begin();
    entityManager().persist(fixture.empresaY);
    try {
      Empresa empresaYDB = empresaDb.obtenerEmpresa("EmpresaY");
      assertEquals(10000.0, empresaYDB.obtenerValor("Ventas", "2016"), 0.0);
    } catch (DbException e) {
      fail("Ha ocurrido un error al consultar la base de datos en la ejecución del test");
    } finally {
      entityManager().getTransaction().rollback();
    }
  }

  @Test
  public void testEmpresaYBeneficioDePeriodo2015NoTieneValor3000() {
    entityManager().getTransaction().begin();
    entityManager().persist(fixture.empresaY);
    try {
      Empresa empresaYDB = empresaDb.obtenerEmpresa("EmpresaY");
      assertNotEquals(3000.0, empresaYDB.obtenerValor("Beneficio", "2015"), 0.0);
    } catch (DbException e) {
      fail("Ha ocurrido un error al consultar la base de datos en la ejecución del test");
    } finally {
      entityManager().getTransaction().rollback();
    }
  }

  @Test
  public void testEmpresaXActivoCorrientePeriodo2014Valor0() {
    entityManager().getTransaction().begin();
    entityManager().persist(fixture.empresaX);
    try {
      Empresa empresaXDB = empresaDb.obtenerEmpresa("EmpresaX");
      assertEquals(0.0, empresaXDB.obtenerValor("ActivoCorriente", "2014"), 0.0);
    } catch (DbException e) {
      fail("Ha ocurrido un error al consultar la base de datos en la ejecución del test");
    } finally {
      entityManager().getTransaction().rollback();
    }
  }

  @Test
  public void testEmpresaYVentasPeriodo2000Valor0() {
    entityManager().getTransaction().begin();
    entityManager().persist(fixture.empresaY);
    try {
      Empresa empresaYDB = empresaDb.obtenerEmpresa("EmpresaY");
      assertEquals(0.0, empresaYDB.obtenerValor("Ventas", "2000"), 0.0);
    } catch (DbException e) {
      fail("Ha ocurrido un error al consultar la base de datos en la ejecución del test");
    } finally {
      entityManager().getTransaction().rollback();
    }
  }

  @Test
  public void testEmpresaZNoTieneCuentas() {
    entityManager().getTransaction().begin();
    entityManager().persist(fixture.empresaZ);
    try {
      Empresa empresaZDB = empresaDb.obtenerEmpresa("EmpresaZ");
      assertEquals(0, empresaZDB.getCuentas().size());
    } catch (DbException e) {
      fail("Ha ocurrido un error al consultar la base de datos en la ejecución del test");
    } finally {
      entityManager().getTransaction().rollback();
    }
  }

  @Test
  public void testEmpresaZVentasPeriodo2017Valor0() {
    entityManager().getTransaction().begin();
    entityManager().persist(fixture.empresaZ);
    try {
      Empresa empresaZDB = empresaDb.obtenerEmpresa("EmpresaZ");
      assertEquals(-1.0, empresaZDB.obtenerValor("Ventas", "2017"), 0.0);
    } catch (DbException e) {
      fail("Ha ocurrido un error al consultar la base de datos en la ejecución del test");
    } finally {
      entityManager().getTransaction().rollback();
    }
  }

  @Test
  public void testEmpresaZNoTieneCuentaVentas() {
    Cuenta ventas = new Cuenta("Ventas");
    entityManager().getTransaction().begin();
    entityManager().persist(fixture.empresaZ);
    try {
      Empresa empresaZDB = empresaDb.obtenerEmpresa("EmpresaZ");
      assertFalse(empresaZDB.getCuentas().contains(ventas));
    } catch (DbException e) {
      fail("Ha ocurrido un error al consultar la base de datos en la ejecución del test");
    } finally {
      entityManager().getTransaction().rollback();
    }
  }
}
