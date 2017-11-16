package testEntidades;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import ar.org.utn.ddstpanual.model.Cuenta;
import fixture.FixtureEmpresa;


public class EmpresaTest {
  FixtureEmpresa fixture;

  @Before
  public void init() {
    fixture = new FixtureEmpresa();
  }

  @Test
  public void testEmpresaConNombreEmpresaX() {
    assertEquals("EmpresaX", fixture.empresaX.getNombre());
  }

  @Test
  public void testEmpresaXSetNombreAEmpresaZ() {
    fixture.empresaX.setNombre("EmpresaZ");
    assertEquals("EmpresaZ", fixture.empresaX.getNombre());
  }

  @Test
  public void testEmpresaXCon4Cuentas() {
    assertEquals(4, fixture.empresaX.getCuentas().size());
  }

  @Test
  public void testEmpresaXTieneCuentaPasivoCorriente() {
    Cuenta pasivoCorriente = new Cuenta("PasivoCorriente");
    assertTrue(fixture.empresaX.getCuentas().contains(pasivoCorriente));
  }

  @Test
  public void testEmpresaXNoTieneCuentaVentas() {
    Cuenta ventas = new Cuenta("Ventas");
    assertFalse(fixture.empresaX.getCuentas().contains(ventas));
  }

  @Test
  public void testEmpresaYTieneCuentaActivoNoCorriente() {
    Cuenta activoNoCorriente = new Cuenta("ActivoNoCorriente");
    assertTrue(fixture.empresaY.getCuentas().contains(activoNoCorriente));
  }

  @Test
  public void testEmpresaYNoTieneCuentaPasivoCorriente() {
    Cuenta pasivoCorriente = new Cuenta("PasivoCorriente");
    assertFalse(fixture.empresaY.getCuentas().contains(pasivoCorriente));
  }

  @Test
  public void testSeLeAgregaCuentaVentasAEmpresaX() {
    Cuenta ventas = new Cuenta("Ventas");
    fixture.empresaX.getCuentas().add(ventas);
    assertTrue(fixture.empresaX.getCuentas().contains(ventas));
  }

  @Test
  public void testEmpresaXActivoCorrientePeriodo2015Valor1000() {
    assertEquals(1000.0, fixture.empresaX.obtenerValor("ActivoCorriente", "2015"), 0.0);
  }

  @Test
  public void testEmpresaXActivoNoCorrientePeriodo2016Valor5000() {
    assertEquals(5000.0, fixture.empresaX.obtenerValor("ActivoNoCorriente", "2016"), 0.0);
  }

  @Test
  public void testEmpresaXPasivoCorrienteDePeriodo2017NoTieneValor3000() {
    assertNotEquals(3000.0, fixture.empresaX.obtenerValor("PasivoCorriente", "2017"), 0.0);
  }

  @Test
  public void testEmpresaYActivoCorrientePeriodo2016Valor2000() {
    assertEquals(2000.0, fixture.empresaY.obtenerValor("ActivoCorriente", "2016"), 0.0);
  }

  @Test
  public void testEmpresaYVentasPeriodo2016Valor10000() {
    assertEquals(10000.0, fixture.empresaY.obtenerValor("Ventas", "2016"), 0.0);
  }

  @Test
  public void testEmpresaYBeneficioDePeriodo2017NoTieneValor3000() {
    assertNotEquals(3000.0, fixture.empresaY.obtenerValor("Beneficio", "2017"), 0.0);
  }

  @Test
  public void testEmpresaXActivoCorrientePeriodo2014Valor0() {
    assertEquals(0.0, fixture.empresaX.obtenerValor("ActivoCorriente", "2014"), 0.0);
  }

  @Test
  public void testEmpresaYVentasPeriodo2000Valor0() {
    assertEquals(0.0, fixture.empresaY.obtenerValor("Ventas", "2000"), 0.0);
  }

  @Test
  public void testEmpresaZNoTieneCuentas() {
    assertEquals(0, fixture.empresaZ.getCuentas().size());
  }

  @Test
  public void testEmpresaZVentasPeriodo2017Valor0() {
    assertEquals(-1.0, fixture.empresaZ.obtenerValor("Ventas", "2017"), 0.0);
  }

  @Test
  public void testEmpresaZNoTieneCuentaVentas() {
    Cuenta ventas = new Cuenta("Ventas");
    assertFalse(fixture.empresaZ.getCuentas().contains(ventas));
  }
}
