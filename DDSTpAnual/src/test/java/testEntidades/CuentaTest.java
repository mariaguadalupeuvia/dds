package testEntidades;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import ar.org.utn.ddstpanual.model.Cuenta;
import ar.org.utn.ddstpanual.model.Periodo;
import fixture.FixtureCuenta;

public class CuentaTest {

  FixtureCuenta fixture;

  @Before
  public void init() {
    fixture = new FixtureCuenta();
  }

  @Test
  public void testCuentaConNombreCuentaA() {
    assertEquals("CuentaA", fixture.cuentaA.getNombre());
  }
  @Test
  public void testCuentaASetNombreACuentaC() {
    fixture.cuentaA.setNombre("CuentaC");
    assertEquals("CuentaC", fixture.cuentaA.getNombre());
  }
  @Test
  public void testCuentaACon5Periodos() {
    assertEquals(5, fixture.cuentaA.getPeriodos().size());
  }
   @Test
   public void testCuentaAConPeriodo2013() {
     Periodo periodo2013 = new Periodo("2013");
     assertTrue(fixture.cuentaA.getPeriodos().contains(periodo2013));
   }
   @Test
   public void testCuentaBCon6Periodos() {
     assertEquals(6, fixture.cuentaB.getPeriodos().size());
   }
    @Test
    public void testCuentaBConPeriodo2012() {
      Periodo periodo2012 = new Periodo("2012");
      assertTrue(fixture.cuentaB.getPeriodos().contains(periodo2012));
    }
    @Test
    public void testCuentaANoTienePeriodo2012() {
      Periodo periodo2012 = new Periodo("2012");
      assertFalse(fixture.cuentaA.getPeriodos().contains(periodo2012));
    }
   @Test
   public void testCuentaASeLeAgregaElPeriodo2010() {
     Periodo periodo2010 = new Periodo("2010");
     fixture.cuentaA.getPeriodos().add(periodo2010);
     assertTrue(fixture.cuentaA.getPeriodos().contains(periodo2010));
   }
   @Test
   public void testCuentaSinPeriodos() {
     assertTrue(fixture.cuentaSinPeriodos.getPeriodos().isEmpty());
   }
   @Test
   public void testCuentasAIguales() {
     Cuenta cuentaAClon = new Cuenta("CuentaA");
     assertTrue(fixture.cuentaA.equals(cuentaAClon));
   }
   @Test
   public void testCuentaANoEsIgualACuentaB() {
     assertFalse(fixture.cuentaA.equals(fixture.cuentaB));
   }
   @Test
   public void testCuentaAObtenerValor1000(){
     assertEquals(1000.0, fixture.cuentaA.obtenerValor("2013"), 0.0);
   }
   @Test
   public void testCuentaANoTieneValor4500En2017(){
     assertNotEquals(4500.0, fixture.cuentaA.obtenerValor("2017"), 0.0);
   }
   @Test
   public void testCuentaBObtenerValor3500(){
     assertEquals(3500.0, fixture.cuentaB.obtenerValor("2015"), 0.0);
   } 
   @Test
   public void testCuentaBNoTieneValor7000En2016(){
     assertNotEquals(7000.0, fixture.cuentaA.obtenerValor("2016"), 0.0);
   }
   @Test
   public void testObtenerValorCeroDePeriodoFaltante(){
     assertEquals(0.0, fixture.cuentaA.obtenerValor("2012"), 0.0);
   } 
}
