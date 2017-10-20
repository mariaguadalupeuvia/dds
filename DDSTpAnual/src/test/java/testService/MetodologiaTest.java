package testService;

import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;
import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;

import java.util.List;

import ar.org.utn.ddstpanual.db.MetodologiaDb;
import ar.org.utn.ddstpanual.exception.DbException;
import ar.org.utn.ddstpanual.exception.ServiceException;
import ar.org.utn.ddstpanual.model.Empresa;
import ar.org.utn.ddstpanual.model.Periodo;
import ar.org.utn.ddstpanual.model.metodologia.Metodologia;
import db.FixtureMetodologiaDB;

public class MetodologiaTest implements WithGlobalEntityManager {
  Metodologia metodologia = null;
  List<Empresa> empresas = null;
  FixtureMetodologiaDB fixture = new FixtureMetodologiaDB();
  private MetodologiaDb metododologiaDb = new MetodologiaDb();

  @Before
  public void init() throws ServiceException, DbException {
    metodologia = metododologiaDb.obtenerMetodologia("BUFFET");
    empresas = fixture.getEmpresas();
  }

  // Test sobre la obtencion de metodologias
  @Test
  public void testObtenerMetodologiaBD() throws ServiceException {
    assertTrue(metodologia.getNombre().equals("BUFFET"));
  }

  // Test ejecutar metodologia
  @Test
  public void testEjecutarMetodologiaPara2017() throws DbException, ServiceException {
    try {
      List<Empresa> empresasOrdenadas = metodologia.ejecutarMetodologia(empresas, new Periodo("2017"));
      assertTrue(empresasOrdenadas.stream().findFirst().orElse(null).getNombre().equals("twitter"));
      empresasOrdenadas.stream().forEach(e -> System.out.println("2017: Invertir en " + e.getNombre()));
      assertTrue(empresasOrdenadas.size() == 3);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  @Test
  public void testEjecutarMetodologiaPara2016() throws DbException, ServiceException {
    try {
      List<Empresa> empresasOrdenadas = metodologia.ejecutarMetodologia(empresas, new Periodo("2016"));
      assertTrue(empresasOrdenadas.stream().findFirst().orElse(null).getNombre().equals("Facebook"));
      empresasOrdenadas.stream().forEach(e -> System.out.println("2016: Invertir en " + e.getNombre()));
      assertTrue(empresasOrdenadas.size() == 3);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  @Test
  public void testEjecutarMetodologiaPara2015() throws DbException, ServiceException {
    try {
      List<Empresa> empresasOrdenadas = metodologia.ejecutarMetodologia(empresas, new Periodo("2015"));
      assertTrue(empresasOrdenadas.stream().findFirst().orElse(null).getNombre().equals("ebay"));
      empresasOrdenadas.stream().forEach(e -> System.out.println("2015: Invertir en " + e.getNombre()));
      assertTrue(empresasOrdenadas.size() == 2);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
