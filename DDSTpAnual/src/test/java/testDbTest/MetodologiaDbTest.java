package testDbTest;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

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
import db.FixtureDB;

public class MetodologiaDbTest {
  MetodologiaDb metodologiaDb;
  Metodologia metodologia;
  FixtureDB fixture;


  @Before
  public void init() {
    metodologiaDb = new MetodologiaDbImpl();
    fixture = new FixtureDB();

    String nombre = "MetodologiaOrden";
    Indicador indicadorTest = new Indicador("IndicadorMetodologiaOrden", "{IndicadorA}+100");
    Filtro filtroTest = new FiltroMayor();
    Condicion condicion1 = new Condicion(indicadorTest, filtroTest, 100);
    ArrayList<Condicion> condiciones = new ArrayList<Condicion>();
    condiciones.add(condicion1);

    TipoOrden tipoOrdenTest = new TipoOrden();
    tipoOrdenTest.setIdTipoOrden(TipoOrden.ASCENDENTE);
    Orden ordenTest = new Orden(indicadorTest, "Ascendente");

    List<Orden> ordenes = new ArrayList<>();
    ordenes.add(ordenTest);

    metodologia = new Metodologia(nombre, condiciones, ordenes);

  }

  // Test guardar empresa
  @Test
  public void testGuardarMetodologia() throws ArchivoException {
    metodologiaDb.guardarMetodologia(metodologia);
  }

  @Test
  public void testMostrarMetodologiaGuardada() throws ServiceException, ArchivoException {
    System.out.println(metodologiaDb.obtenerMetodologia("MetodologiaOrden").toJson());
  }

  @Test
  public void testObtenerMetodologiasGuardadas() throws ArchivoException {
    for (Metodologia m : metodologiaDb.obtenerMetodologias()) {
      System.out.println(m.toJson());
    } ;
  }
}
