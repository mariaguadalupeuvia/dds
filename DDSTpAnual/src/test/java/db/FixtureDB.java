package db;

import org.junit.Test;
import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;
import org.uqbarproject.jpa.java8.extras.test.AbstractPersistenceTest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import ar.org.utn.ddstpanual.model.Cuenta;
import ar.org.utn.ddstpanual.model.Empresa;
import ar.org.utn.ddstpanual.model.Indicador;
import ar.org.utn.ddstpanual.model.Periodo;
import ar.org.utn.ddstpanual.model.metodologia.Filtro;
import ar.org.utn.ddstpanual.model.metodologia.FiltroCreciente;
import ar.org.utn.ddstpanual.model.metodologia.FiltroDecreciente;
import ar.org.utn.ddstpanual.model.metodologia.FiltroIgual;
import ar.org.utn.ddstpanual.model.metodologia.FiltroMayor;
import ar.org.utn.ddstpanual.model.metodologia.FiltroMayorIgual;
import ar.org.utn.ddstpanual.model.metodologia.FiltroMenor;
import ar.org.utn.ddstpanual.model.metodologia.FiltroMenorIgual;

public class FixtureDB extends AbstractPersistenceTest implements WithGlobalEntityManager {
  public FixtureDB() {

    try {

      // // Creación de los tipos de Orden
      // TipoOrden tipoOrden1 = new TipoOrden();
      // tipoOrden1.setIdTipoOrden(TipoOrden.ASCENDENTE);
      // tipoOrden1.setNombreOrden("ASCENDENTE");
      //
      // TipoOrden tipoOrden2 = new TipoOrden();
      // tipoOrden2.setIdTipoOrden(TipoOrden.DESCENDENTE);
      // tipoOrden2.setNombreOrden("DESCENDENTE");

      withTransaction(() -> {
        // entityManager().persist(tipoOrden1);
        // entityManager().persist(tipoOrden2);

      });
    } catch (Exception e) {
      // e.printStackTrace(); Esto permite que se cree una sola vez el tipo de Orden en la base
    }
  }

  @Test
  public void crearFixture() {
    List<Filtro> filtros = new ArrayList<>();
    filtros.add(new FiltroCreciente());
    filtros.add(new FiltroDecreciente());
    filtros.add(new FiltroIgual());
    filtros.add(new FiltroMayor());
    filtros.add(new FiltroMenor());
    filtros.add(new FiltroMayorIgual());
    filtros.add(new FiltroMenorIgual());

    List<Indicador> indicadores = new ArrayList<>();
    indicadores.add(new Indicador("activoTotal", "[activoCorriente]+[activoNoCorriente]"));
    indicadores.add(new Indicador("deuda", "[activoTotal]+[pasivoTotal]"));
    indicadores.add(new Indicador("margenesCrecientes", "[activoCorriente]*[activoNoCorriente]"));
    indicadores.add(new Indicador("roe", "[activoCorriente]/[pasivoTotal]"));
    indicadores.add(new Indicador("roa", "[activoCorriente]/[deuda]"));
    indicadores.add(new Indicador("pasivoTotal", "[pasivoCorriente]+[pasivoNoCorriente]"));


    List<Periodo> periodos1 =
        Arrays.asList(new Periodo("2014", 1000), new Periodo("2015", 2000), new Periodo("2016", 3000), new Periodo("2017", 4000));
    List<Periodo> periodos2 =
        Arrays.asList(new Periodo("2014", 1000), new Periodo("2015", 2000), new Periodo("2016", 3000), new Periodo("2017", 4000));
    List<Periodo> periodos3 =
        Arrays.asList(new Periodo("2014", 1000), new Periodo("2015", 2000), new Periodo("2016", 3000), new Periodo("2017", 4000));
    List<Periodo> periodos4 =
        Arrays.asList(new Periodo("2014", 1000), new Periodo("2015", 2000), new Periodo("2016", 3000), new Periodo("2017", 4000));
    List<Cuenta> cuentas = Arrays.asList(new Cuenta("activoCorriente", periodos1), new Cuenta("activoNoCorriente", periodos2),
        new Cuenta("pasivoCorriente", periodos3), new Cuenta("pasivoNoCorriente", periodos4));
    Empresa facebook = new Empresa("Facebook", cuentas);

    List<Periodo> periodos5 =
        Arrays.asList(new Periodo("2014", 500), new Periodo("2015", 750), new Periodo("2016", 1000), new Periodo("2017", 1200));
    List<Periodo> periodos6 =
        Arrays.asList(new Periodo("2014", 500), new Periodo("2015", 750), new Periodo("2016", 1000), new Periodo("2017", 1200));
    List<Periodo> periodos7 =
        Arrays.asList(new Periodo("2014", 500), new Periodo("2015", 750), new Periodo("2016", 1000), new Periodo("2017", 1200));
    List<Periodo> periodos8 =
        Arrays.asList(new Periodo("2014", 500), new Periodo("2015", 750), new Periodo("2016", 1000), new Periodo("2017", 1200));
    List<Cuenta> cuentas2 = Arrays.asList(new Cuenta("activoCorriente", periodos5), new Cuenta("activoNoCorriente", periodos6),
        new Cuenta("pasivoCorriente", periodos7), new Cuenta("pasivoNoCorriente", periodos8));
    Empresa twitter = new Empresa("Twitter", cuentas2);


    withTransaction(() -> {
      filtros.stream().forEach((f -> entityManager().persist(f)));
      indicadores.stream().forEach((i -> entityManager().persist(i)));
      entityManager().persist(facebook);
      entityManager().persist(twitter);
    });
  }
}
