package testService;

import org.junit.Test;
import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;
import org.uqbarproject.jpa.java8.extras.test.AbstractPersistenceTest;

import java.util.ArrayList;
import java.util.List;

import ar.org.utn.ddstpanual.model.Cuenta;
import ar.org.utn.ddstpanual.model.Empresa;
import ar.org.utn.ddstpanual.model.Periodo;

public class ConnectionDbTest extends AbstractPersistenceTest implements WithGlobalEntityManager {

  @Test
  public void persistenciaPeriodo() {
    List<Cuenta> cuentas = new ArrayList<>();
    List<Periodo> periodos = new ArrayList<>();
    Cuenta cuenta = new Cuenta();
    cuenta.setNombre("cuenta");
    cuenta.setPeriodos(periodos);
    Periodo periodo = new Periodo();
    periodo.setFecha("2017");
    periodo.setValor(123123);
    periodos.add(periodo);
    cuentas.add(cuenta);
    Empresa empresa = new Empresa();
    empresa.setCuentas(cuentas);
    empresa.setNombre("Empresa");
    withTransaction(() -> {
      entityManager().persist(empresa);
    });
  }

  // @Test
  // public void persistirMetodologia() {
  //
  // Indicador indicador = new Indicador("activoTotal", "[activoCorriente]+[activoNoCorriente]");
  //
  // List<Condicion> condiciones = new ArrayList<>();
  // condiciones.add(new Condicion(indicador, entityManager().find( Filtro.class, 1), 500));
  // condiciones.add(new Condicion(indicador,entityManager().find( Filtro.class, 2), 1000));
  // Metodologia metodologia = new Metodologia("MET2", condiciones, Arrays.asList(new
  // Orden(indicador, "Ascendente")));
  //
  // withTransaction(() -> {
  // entityManager().persist(metodologia);
  // });
  //
  // }
  //
  // @Test
  // public void obtenerMetodologia() {
  // withTransaction(() -> {
  // List<Metodologia> metodologias = entityManager().createQuery("from Metodologia",
  // Metodologia.class).getResultList();
  // assertTrue(metodologias.size() > 0);
  // // for (Metodologia metod : metodologias)
  // // {
  // // System.out.println("Metodologia: " + metod.getNombre());
  // //
  // // for (Condicion condicion : metod.getCondiciones())
  // // {
  // // System.out.println("Condicion: " + condicion.getIndicador() + " " +
  // // condicion.getFiltro().getNombre() + " " + condicion.getValor() );
  // // }
  // // }
  // });
  // }
}
