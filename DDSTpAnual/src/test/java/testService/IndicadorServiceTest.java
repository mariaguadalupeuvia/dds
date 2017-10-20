package testService;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import ar.org.utn.ddstpanual.exception.ArbolException;
import ar.org.utn.ddstpanual.model.Cuenta;
import ar.org.utn.ddstpanual.model.Empresa;
import ar.org.utn.ddstpanual.model.Indicador;
import ar.org.utn.ddstpanual.model.Periodo;
import ar.org.utn.ddstpanual.utils.tree.ArbolUtil;

public class IndicadorServiceTest {

  Indicador indicador = null;
  String indicadorSimpleCorrecto = null;
  String indicadorSimpleIncorrecto = null;
  List<Periodo> periodos = new ArrayList<Periodo>();
  List<Cuenta> cuentas = new ArrayList<Cuenta>();
  Empresa ejemplo = new Empresa();
  ArbolUtil arbol = new ArbolUtil();

  @Before
  public void init() {
    indicadorSimpleCorrecto = "[Cuenta]+12";
    indicadorSimpleIncorrecto = "Cuenta+ 3";

    periodos.add(new Periodo("2010", 500));
    periodos.add(new Periodo("2011", 1500));
    periodos.add(new Periodo("2012", 2500));
    periodos.add(new Periodo("2013", 3500));
    periodos.add(new Periodo("2014", 4500));

    cuentas.add(new Cuenta("cuenta1", periodos));
    cuentas.add(new Cuenta("cuenta2", periodos));

    ejemplo = new Empresa(1, cuentas, "Ejemplo");
  }



  // // Test sobre el guardado de indicadores
  // @Test(expected = ServiceException.class)
  // public void testGuardarIndicadorCorrectoDuplicado() throws ServiceException {
  // indicador = new Indicador("IndicadorCorrecto", indicadorSimpleCorrecto);
  // service.guardarIndicador(indicador);
  // }
  //
  // @Test(expected = ServiceException.class)
  // public void testGuardarIndicadorError() throws ServiceException {
  // indicador = new Indicador("IndicadorIncorrecto", indicadorSimpleIncorrecto);
  // service.guardarIndicador(indicador);
  // }
  //
  // // Test sobre la validacion de las formulas
  // @Test
  // public void testValidarFormulaCorrecta() throws ServiceException {
  // assertTrue(service.validarFormula(indicadorSimpleCorrecto));
  // }
  //
  // @Test
  // public void testValidarFormulaIncorrectaCorrecta() throws ServiceException {
  // assertFalse(service.validarFormula(indicadorSimpleIncorrecto));
  // }

  // // Test sobre la obtencion de Indicadores
  // // obtenerIndicadores
  // @Test
  // public void testObtenerIndicadores() throws ServiceException {
  // service.obtenerIndicadores();
  // }


  // Test sobre la ejecucion de los indicadores
  @Test
  public void testEjecutarIndicadorConResultado1000() throws ArbolException {
    System.out.println(periodos.get(0).getFecha());
    //Assert.assertEquals(1000.0, arbol.obtenerValor("[cuenta1]+[cuenta2]", periodos.get(0).getFecha(), ejemplo), 0);
  }


}
