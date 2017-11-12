package testEntidades;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import ar.org.utn.ddstpanual.model.Periodo;
import fixture.FixturePeriodo;

public class PeriodoTest {

FixturePeriodo fixture;
  
  @Before
  public void init() {
    fixture = new FixturePeriodo();
  }
  
  @Test
  public void testPeriodoConFecha2013() {
    assertEquals("2013", fixture.periodo2013.getFecha());
  }
  @Test
  public void testPeriodoConFecha2014() {
    assertEquals("2014", fixture.periodo2014.getFecha());
  }
  @Test
  public void testPeriodoConValor3000() {
    assertEquals(3000.0, fixture.periodo2013.getValor(), 0.0);
  }
  @Test
  public void testPeriodoConValor4000() {
    assertEquals(4000.0, fixture.periodo2014.getValor(), 0.0);
  }
  @Test
  public void testPeriodo2012SetFecha2015() {
    fixture.periodoSetFecha.setFecha("2015");
    assertEquals("2015", fixture.periodoSetFecha.getFecha());
  }
  @Test
  public void testPeriodoSetValor8000() {
    fixture.periodoSetFecha.setValor(8000);
    assertEquals(8000.0, fixture.periodoSetFecha.getValor(), 0.0);
  }
  @Test
  public void testPeriodoValorNulo() {
    assertEquals(0.0, fixture.periodoValorNulo.getValor(), 0.0);
  }
  @Test
  public void testPeriodoFechaNula() {
    assertNull(fixture.periodoValorNulo.getFecha());
  }
  @Test
  public void testPeriodo2013Igual() {
    Periodo periodo2013Equals = new Periodo("2013", 3000);
    assertTrue(fixture.periodo2013.equals(periodo2013Equals));
  }
  @Test
  public void testPeriodo2013NoesIguala2014() {
    Periodo periodo2014Equals = new Periodo("2014", 3000);
    assertFalse(fixture.periodo2013.equals(periodo2014Equals));
  }

}
