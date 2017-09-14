package testDbTest;

import org.junit.Before;
import org.junit.Test;
import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;

import java.util.ArrayList;
import java.util.List;

import ar.org.utn.ddstpanual.db.MetodologiaDb;
import ar.org.utn.ddstpanual.db.impl.MetodologiaDbImpl;
import ar.org.utn.ddstpanual.exception.ArchivoException;
import ar.org.utn.ddstpanual.exception.ServiceException;
import ar.org.utn.ddstpanual.model.Indicador;
import ar.org.utn.ddstpanual.model.metodologia.Condicion;
import ar.org.utn.ddstpanual.model.metodologia.Filtro;
import ar.org.utn.ddstpanual.model.metodologia.Metodologia;
import ar.org.utn.ddstpanual.model.metodologia.Orden;

import db.FixtureDB;

public class MetodologiaDbTest implements WithGlobalEntityManager{
  MetodologiaDb metodologiaDb;

  FixtureDB fixture;

  @Before
  public void init() {
    metodologiaDb = new MetodologiaDbImpl();
    fixture = new FixtureDB();
  }
  
  // Test guardar metodologia
  @Test
  public void testGuardarMetodologia() throws ArchivoException {

	    Indicador indicadorTest = new Indicador("IndicadorMetodologiaOrden", "{IndicadorA}+100");
	    Condicion condicion1 = new Condicion(indicadorTest, Filtro.MAYOR, 100.0);
	    ArrayList<Condicion> condiciones = new ArrayList<Condicion>();
	    condiciones.add(condicion1);

	    Orden ordenTest = new Orden(indicadorTest, "Ascendente");
	    List<Orden> ordenes = new ArrayList<>();
	    ordenes.add(ordenTest);
	    metodologiaDb.guardarMetodologia(new Metodologia("MET1", condiciones, ordenes));
  }

  @Test
  public void testMostrarMetodologiaGuardada() throws ServiceException, ArchivoException {
    System.out.println(metodologiaDb.obtenerMetodologia("BUFFET").toJson());
  }

  @Test
  public void testObtenerMetodologiasGuardadas() throws ArchivoException {
    for (Metodologia m : metodologiaDb.obtenerMetodologias()) {
      System.out.println(m.toJson());
    } ;
  }
}
