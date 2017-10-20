package testService;

// import static org.junit.Assert.assertFalse;
// import static org.junit.Assert.assertTrue;
//
// import org.junit.Before;
// import org.junit.Test;
//
// import java.util.ArrayList;
//
// import ar.org.utn.ddstpanual.exception.ArchivoException;
// import ar.org.utn.ddstpanual.exception.ServiceException;
// import ar.org.utn.ddstpanual.model.Empresa;
// import ar.org.utn.ddstpanual.model.Indicador;
// import ar.org.utn.ddstpanual.model.Periodo;
// import ar.org.utn.ddstpanual.model.metodologia.Condicion;
// import ar.org.utn.ddstpanual.model.metodologia.Filtro;
// import ar.org.utn.ddstpanual.model.metodologia.FiltroIgual;
// import ar.org.utn.ddstpanual.model.metodologia.FiltroMayor;
// import ar.org.utn.ddstpanual.model.metodologia.FiltroMayorIgual;
// import ar.org.utn.ddstpanual.model.metodologia.FiltroMenor;
// import ar.org.utn.ddstpanual.model.metodologia.FiltroMenorIgual;
// import ar.org.utn.ddstpanual.model.metodologia.Metodologia;
// import ar.org.utn.ddstpanual.service.impl.EmpresaServiceImpl;

public class CondicionServiceTest {
  // CondicionServiceImpl condicionService;
  // EmpresaServiceImpl empresaService;
  // Metodologia metodologia;
  // Periodo periodoTest;
  // Empresa empresaTest;
  // Condicion condicion1;
  // ArrayList<Condicion> condiciones;
  // String nombreEmpresa;
  // Indicador indicadorTest;
  //
  // @Before
  // public void init() throws ServiceException {
  // condicionService = new CondicionServiceImpl();
  // empresaService = new EmpresaServiceImpl();
  //
  // nombreEmpresa = "MetodologiaService";
  // indicadorTest = new Indicador("IndicadorB", "{IndicadorA}/50");
  //
  // periodoTest = new Periodo("2016");
  // empresaTest = empresaService.obtenerEmpresa("FaceCopia");
  //
  //
  // }
  //
  // // boolean cumpleCondiciones(Metodologia metodologia, Empresa empresa, Periodo periodo)
  // @Test
  // public void testCumpleCondicionCondicionMayorA100() throws ArchivoException, ServiceException {
  // Filtro filtroTest = new FiltroMayor();
  // condicion1 = new Condicion(indicadorTest, filtroTest, 100);
  // condiciones = new ArrayList<Condicion>();
  // condiciones.add(condicion1);
  // metodologia = new Metodologia(nombreEmpresa, condiciones, null);
  // assertTrue(condicionService.cumpleCondiciones(metodologia, empresaTest, periodoTest));
  // }
  //
  // @Test
  // public void testCumpleCondicionCondicionMayorIgualA1000() throws ArchivoException,
  // ServiceException {
  // Filtro filtroTest = new FiltroMayorIgual();
  // condicion1 = new Condicion(indicadorTest, filtroTest, 1000);
  // condiciones = new ArrayList<Condicion>();
  // condiciones.add(condicion1);
  // metodologia = new Metodologia(nombreEmpresa, condiciones, null);
  // assertTrue(condicionService.cumpleCondiciones(metodologia, empresaTest, periodoTest));
  // }
  //
  // @Test
  // public void testNoCumpleCondicionCondicionIgualA2000() throws ArchivoException,
  // ServiceException {
  // Filtro filtroTest = new FiltroIgual();
  // condicion1 = new Condicion(indicadorTest, filtroTest, 2000);
  // condiciones = new ArrayList<Condicion>();
  // condiciones.add(condicion1);
  // metodologia = new Metodologia(nombreEmpresa, condiciones, null);
  // assertFalse(condicionService.cumpleCondiciones(metodologia, empresaTest, periodoTest));
  // }
  //
  // @Test
  // public void testCumpleCondicionCondicionMenorA2000() throws ArchivoException, ServiceException
  // {
  // Filtro filtroTest = new FiltroMenor();
  // condicion1 = new Condicion(indicadorTest, filtroTest, 2000);
  // condiciones = new ArrayList<Condicion>();
  // condiciones.add(condicion1);
  // metodologia = new Metodologia(nombreEmpresa, condiciones, null);
  // assertTrue(condicionService.cumpleCondiciones(metodologia, empresaTest, periodoTest));
  // }
  //
  // @Test
  // public void testCumpleCondicionCondicionMenorIgualA2000() throws ArchivoException,
  // ServiceException {
  // Filtro filtroTest = new FiltroMenorIgual();
  // condicion1 = new Condicion(indicadorTest, filtroTest, 1000);
  // condiciones = new ArrayList<Condicion>();
  // condiciones.add(condicion1);
  // metodologia = new Metodologia(nombreEmpresa, condiciones, null);
  // assertTrue(condicionService.cumpleCondiciones(metodologia, empresaTest, periodoTest));
  // }

}
