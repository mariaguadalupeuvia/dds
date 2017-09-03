package testServiceImpl;

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
    entityManager().persist(empresa);
    entityManager().getTransaction().commit();
    entityManager().close();
  }

}
