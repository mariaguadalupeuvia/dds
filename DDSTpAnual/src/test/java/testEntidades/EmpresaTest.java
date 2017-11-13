package testEntidades;

import static org.junit.Assert.*;

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
    fixture.empresaX.setNombre("CuentaZ");
    assertEquals("CuentaZ", fixture.empresaX.getNombre());
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
  public void testEmpresaYTieneCuentaActivoNoCorriente() {
    Cuenta activoNoCorriente = new Cuenta("ActivoNoCorriente");
    assertTrue(fixture.empresaY.getCuentas().contains(activoNoCorriente));
  }
}
