package DDSTpAnual;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import ar.org.utn.ddstpanual.exception.ServiceException;
import ar.org.utn.ddstpanual.model.Indicador;
import ar.org.utn.ddstpanual.service.impl.IndicadorServiceImpl;

public class IndicadorServiceImplTest {

  IndicadorServiceImpl service = null;
  Indicador indicador = null;
  String indicadorSimpleCorrecto = null;
  String indicadorSimpleIncorrecto = null;

  @Before
  public void init() {
      service = new IndicadorServiceImpl();
      indicadorSimpleCorrecto = "[Cuenta]+12";
      indicadorSimpleIncorrecto = "Cuenta+ 3";
  }
  
  // Test sobre el guardado de indicadores
  @Test
  public void testGuardarIndicadorCorrectamente() throws ServiceException {
    indicador = new Indicador("IndicadorCorrecto", indicadorSimpleCorrecto);
    service.guardarIndicador(indicador);
  }
  
  @Test(expected = ServiceException.class)
  public void testGuardarIndicadorError() throws ServiceException {
    indicador = new Indicador("IndicadorIncorrecto", indicadorSimpleIncorrecto);
    service.guardarIndicador(indicador);
  }
  
  // Test sobre la validacion de las formulas
  @Test
  public void testValidarFormulaCorrecta() throws ServiceException {
    assertTrue(service.validarFormula(indicadorSimpleCorrecto));
  }
  
  @Test
  public void testValidarFormulaIncorrectaCorrecta() throws ServiceException {
    assertFalse(service.validarFormula(indicadorSimpleIncorrecto));
  }
  
  // Test sobre la obtencion de Indicadores 
  // obtenerIndicadores
  @Test
  public void testObtenerIndicadores() throws ServiceException {
    service.obtenerIndicadores();
  }
  
  // Test sobre la ejecucion de los indicadores 
  // ejecutarIndicador
  @Test
  public void testEjecutarIndicador() throws ServiceException {
    service.ejecutarIndicador();
  }
 
  
}
