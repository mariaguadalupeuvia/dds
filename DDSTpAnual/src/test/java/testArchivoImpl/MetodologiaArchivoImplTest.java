package testArchivoImpl;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import ar.org.utn.ddstpanual.db.MetodologiaDb;
import ar.org.utn.ddstpanual.db.impl.MetodologiaDbImpl;
import ar.org.utn.ddstpanual.exception.ArchivoException;
import ar.org.utn.ddstpanual.exception.ServiceException;
import ar.org.utn.ddstpanual.model.Indicador;
import ar.org.utn.ddstpanual.model.metodologia.Condicion;
import ar.org.utn.ddstpanual.model.metodologia.Filtro;
import ar.org.utn.ddstpanual.model.metodologia.FiltroMayor;
import ar.org.utn.ddstpanual.model.metodologia.Metodologia;
import ar.org.utn.ddstpanual.model.metodologia.Orden;
import ar.org.utn.ddstpanual.model.metodologia.TipoOrden;

public class MetodologiaArchivoImplTest {
  MetodologiaDb metodologiaDb;
  Metodologia metodologia;


  @Before
  public void init() {
    metodologiaDb = new MetodologiaDbImpl();

    String nombre = "MetodologiaArchivo";
    Indicador indicadorTest = new Indicador("IndicadorMetodologia", "{IndicadorA}/50");
    Filtro filtroTest = new FiltroMayor();
    Condicion condicion1 = new Condicion(indicadorTest, filtroTest, 100);
    ArrayList<Condicion> condiciones = new ArrayList<Condicion>();
    condiciones.add(condicion1);
    TipoOrden tipoOrdenTest = new TipoOrden();
    tipoOrdenTest.setIdTipoOrden(TipoOrden.ASCENDENTE);
    Orden ordenTest = new Orden(indicadorTest, tipoOrdenTest);

    metodologia = new Metodologia(nombre, condiciones, ordenTest);

  }

  // Test guardar empresa
  @Test
  public void testGuardarMetodologia() throws ArchivoException {
    metodologiaDb.guardarMetodologia(metodologia);
  }

  @Test
  public void testMostrarMetodologiaExistente() throws ServiceException, ArchivoException {
    System.out.println(metodologiaDb.obtenerMetodologia("MetodologiaArchivo").toJson());
  }

  @Test(expected = ArchivoException.class)
  public void testMostrarMetodologiaInexistente() throws ServiceException, ArchivoException {
    System.out.println(metodologiaDb.obtenerMetodologia("MetodologiaArchivoInexistente").toJson());
  }

  @Test
  public void testObtenerMetodologiasGuardadas() throws ArchivoException {
    for (Metodologia m : metodologiaDb.obtenerMetodologias()) {
      System.out.println(m.toJson());
    } ;
  }
}
