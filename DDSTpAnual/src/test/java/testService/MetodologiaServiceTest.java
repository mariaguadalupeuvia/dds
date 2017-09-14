package testService;

import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;
import org.uqbarproject.jpa.java8.extras.PerThreadEntityManagers;

import javax.persistence.EntityManager;

import ar.org.utn.ddstpanual.exception.ServiceException;
import ar.org.utn.ddstpanual.model.metodologia.Metodologia;
import ar.org.utn.ddstpanual.service.impl.MetodologiaServiceImpl;

public class MetodologiaServiceTest {
  MetodologiaServiceImpl service = null;
  Metodologia metodologia = null;

  @Before
  public void init() {
    service = new MetodologiaServiceImpl();

  }

  // Test sobre la obtencion de metodologias
  @Test
  public void testObtenerMetodologiaDeArchivo() throws ServiceException {
    metodologia = service.obtenerMetodologia("MET2");
    assertTrue(metodologia.getNombre().equals("MET2"));
  }

  @Test
  public void testObtenerMetodologiaBD() throws ServiceException {
    metodologia = obtener();
    assertTrue(metodologia.getNombre().equals("MET2"));
  }

  // Test de Orden
  @Test
  public void testOrdenarAscendenteEmpresas() {
    // service.compareEmpresasByMetodologia(e1, e2, ordenes, per)
  }

  private Metodologia obtener() {
    try {
      EntityManager entity = PerThreadEntityManagers.getEntityManager();
      return entity.find(Metodologia.class, 1);
    } catch (Exception e) {
      System.out.println(e.getMessage());
    }
    return null;
  }
}