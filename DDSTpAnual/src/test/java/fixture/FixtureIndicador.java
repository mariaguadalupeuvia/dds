package fixture;

import java.util.ArrayList;
import java.util.List;

import ar.org.utn.ddstpanual.db.IndicadorDb;
import ar.org.utn.ddstpanual.db.UsuarioDb;
import ar.org.utn.ddstpanual.exception.DbException;
import ar.org.utn.ddstpanual.model.Cuenta;
import ar.org.utn.ddstpanual.model.Empresa;
import ar.org.utn.ddstpanual.model.Indicador;
import ar.org.utn.ddstpanual.model.Periodo;
import ar.org.utn.ddstpanual.model.Usuario;

public class FixtureIndicador {
  public List<Periodo> periodosTest1;
  public List<Periodo> periodosTest2;
  public List<Periodo> periodosTest3;
  public Cuenta cuentaTest1;
  public Cuenta cuentaTest2;
  public Cuenta cuentaTest3;
  public List<Cuenta> cuentasTest;
  public Empresa empTest;
  public Indicador indTest1;
  public Indicador indTest2;
  public Indicador indTest3;
  public Indicador indTest4;
  public Indicador indErr;
  public UsuarioDb usuarioDb;
  public Usuario usuario;
  public IndicadorDb indicadorDb;


  public void iniciarCuentas() {
    periodosTest1 = new ArrayList<Periodo>();
    periodosTest2 = new ArrayList<Periodo>();
    periodosTest3 = new ArrayList<Periodo>();

    periodosTest1.add(new Periodo("2011", 500000));
    periodosTest1.add(new Periodo("2012", 40000));
    periodosTest1.add(new Periodo("2013", 100000));
    periodosTest1.add(new Periodo("2014", 70000));

    periodosTest2.add(new Periodo("2011", 400000));
    periodosTest2.add(new Periodo("2012", 80000));
    periodosTest2.add(new Periodo("2013", 900000));
    periodosTest2.add(new Periodo("2014", 16000));

    periodosTest3.add(new Periodo("2011", 100000));
    periodosTest3.add(new Periodo("2012", -70000));
    periodosTest3.add(new Periodo("2013", 20000));
    periodosTest3.add(new Periodo("2014", 0));

    cuentaTest1 = new Cuenta("CuentaPrb1", periodosTest1);
    cuentaTest2 = new Cuenta("CuentaPrb2", periodosTest2);
    cuentaTest3 = new Cuenta("CuentaPrb3", periodosTest3);

    cuentasTest = new ArrayList<Cuenta>();
    cuentasTest.add(cuentaTest1);
    cuentasTest.add(cuentaTest2);
    cuentasTest.add(cuentaTest3);

    empTest = new Empresa("empTest", cuentasTest);

    indTest1 = new Indicador("indTest1", "([CuentaPrb1]+[CuentaPrb2])/[CuentaPrb3]");
    indTest2 = new Indicador("indTest2", "([CuentaPrb1]-[CuentaPrb2])/[CuentaPrb3]");
    indTest3 = new Indicador("indTest3", "([CuentaPrb1]+[CuentaPrb2])*[CuentaPrb3]");
    indTest4 = new Indicador("indTest4", "([CuentaPrb1]+[CuentaPrb2])*[CuentaPrb]");
    indErr = new Indicador("indErr", "[CuentaPrb1]/{indErr}");
  }
  
  public void cargarIndicadores() throws DbException {    
    indicadorDb = new IndicadorDb();
    
    usuarioDb = new UsuarioDb();
    usuario = usuarioDb.obtenerUsuario("nico");
    
    indTest1 = new Indicador("indTest1", "([CuentaPrb1]+[CuentaPrb2])/[CuentaPrb3]",usuario.getId());
    indTest2 = new Indicador("indTest2", "([CuentaPrb1]-[CuentaPrb2])/[CuentaPrb3]",usuario.getId());
    indTest3 = new Indicador("indTest3", "([CuentaPrb1]+[CuentaPrb2])*[CuentaPrb3]",usuario.getId());
    indTest4 = new Indicador("indTest4", "([CuentaPrb1]+[CuentaPrb2])*[CuentaPrb]",usuario.getId());
  }

}
