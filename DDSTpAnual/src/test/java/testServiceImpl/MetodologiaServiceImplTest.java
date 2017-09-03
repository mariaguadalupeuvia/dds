package testServiceImpl;

import org.junit.Before;
import org.junit.Test;

import ar.org.utn.ddstpanual.exception.ServiceException;
import ar.org.utn.ddstpanual.model.metodologia.Metodologia;
import ar.org.utn.ddstpanual.service.impl.MetodologiaServiceImpl;

public class MetodologiaServiceImplTest {
  MetodologiaServiceImpl service = null;
  Metodologia metodologia = null;

  @Before
  public void init() {
    service = new MetodologiaServiceImpl();
  }

  // Test sobre la obtencion de Indicadores
  // obtenerIndicadores
  @Test
  public void testObtenerIndicadores() throws ServiceException {
    metodologia = service.obtenerMetodologia("MET1");
    System.out.println(metodologia.getNombre());
  }

}
