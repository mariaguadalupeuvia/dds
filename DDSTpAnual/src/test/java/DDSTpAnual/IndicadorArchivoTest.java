package DDSTpAnual;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import ar.org.utn.ddstpanual.archivo.impl.IndicadorArchivoImpl;
import ar.org.utn.ddstpanual.exception.ArchivoException;

public class IndicadorArchivoTest {

  IndicadorArchivoImpl service = null;
  // Indicador indicador = null;

  @Before
  public void init() {
    service = new IndicadorArchivoImpl();
  }

  @Test
  public void testObtenerFormulaIndicadorA() throws ArchivoException {
    assertEquals("[cuenta]+12", service.obtenerFormula("IndicadorA"));
  }
}
