package testService;

import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;
import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;

import ar.org.utn.ddstpanual.exception.ArchivoException;
import ar.org.utn.ddstpanual.exception.ServiceException;
import ar.org.utn.ddstpanual.model.Empresa;
import ar.org.utn.ddstpanual.model.Periodo;
import ar.org.utn.ddstpanual.model.metodologia.Metodologia;
import ar.org.utn.ddstpanual.service.impl.MetodologiaServiceImpl;
import db.FixtureDB;

import java.util.List;

public class MetodologiaServiceTest implements WithGlobalEntityManager{
  MetodologiaServiceImpl service = null;
  Metodologia metodologia = null;
  List<Empresa> empresas = null;
  FixtureDB fixture = new FixtureDB();
  
  @Before
  public void init() throws ServiceException 
  {
    service = new MetodologiaServiceImpl();
    metodologia = service.obtenerMetodologia("BUFFET");
    empresas = fixture.getEmpresas();//entityManager().createQuery("from Empresa", Empresa.class).getResultList();
  }
  
  // Test sobre la obtencion de metodologias
  @Test
  public void testObtenerMetodologiaBD() throws ServiceException 
  {
    assertTrue(metodologia.getNombre().equals("BUFFET"));
  }

  // Test ejecutar metodologia
  @Test
  public void testEjecutarMetodologiaPara2017() throws ArchivoException, ServiceException 
  {
	  try 
	  {
		  List<Empresa> empresasOrdenadas = service.ejecutarMetodologia(empresas, metodologia, new Periodo("2017"));
		  assertTrue(empresasOrdenadas.stream().findFirst().orElse(null).getNombre().equals("twitter"));
		  empresasOrdenadas.stream().forEach(e -> System.out.println("2017: Invertir en " + e.getNombre()));
		  assertTrue(empresasOrdenadas.size() == 3); 
	  } 
	  catch (ServiceException e) 
	  {
		e.printStackTrace();
	  } 
  }

  @Test
  public void testEjecutarMetodologiaPara2016() throws ArchivoException, ServiceException 
  {
	  try 
	  {
		  List<Empresa> empresasOrdenadas = service.ejecutarMetodologia(empresas, metodologia, new Periodo("2016"));
		  assertTrue(empresasOrdenadas.stream().findFirst().orElse(null).getNombre().equals("Facebook"));
		  empresasOrdenadas.stream().forEach(e -> System.out.println("2016: Invertir en " + e.getNombre()));
		  assertTrue(empresasOrdenadas.size() == 3); 
	  } 
	  catch (ServiceException e) 
	  {
		e.printStackTrace();
	  } 
  }
  
  @Test
  public void testEjecutarMetodologiaPara2015() throws ArchivoException, ServiceException 
  {
	  try 
	  {
		  List<Empresa> empresasOrdenadas = service.ejecutarMetodologia(empresas, metodologia, new Periodo("2015"));
		  assertTrue(empresasOrdenadas.stream().findFirst().orElse(null).getNombre().equals("ebay"));
		  empresasOrdenadas.stream().forEach(e -> System.out.println("2015: Invertir en " + e.getNombre()));
		  assertTrue(empresasOrdenadas.size() == 2); 
	  } 
	  catch (ServiceException e) 
	  {
		e.printStackTrace();
	  } 
  }
}
