package fixture;

import java.util.ArrayList;
import java.util.List;

import ar.org.utn.ddstpanual.model.Cuenta;
import ar.org.utn.ddstpanual.model.Periodo;

public class FixtureCuenta {
  public Cuenta cuentaValorNulo;
  public Cuenta cuentaA;
  public Cuenta cuentaB;
  public Cuenta cuentaSinPeriodos;

  public Cuenta pasivoCorriente;
  public Cuenta pasivoNoCorriente;
  public Cuenta activoCorriente;
  public Cuenta activoNoCorriente;
  public Cuenta beneficio;
  public Cuenta ventas;
  public Cuenta pasivoTotal;
  public Cuenta patrimonioNeto;

  public FixtureCuenta() {
    FixturePeriodo fixtureP = new FixturePeriodo();
    cuentaValorNulo = new Cuenta();
    cuentaSinPeriodos = new Cuenta("CuentaSinPeriodos");

    List<Periodo> periodosA = new ArrayList<Periodo>();
    periodosA.add(fixtureP.periodo2013.BuilderPeriodo(1000));
    periodosA.add(fixtureP.periodo2014.BuilderPeriodo(2000));
    periodosA.add(fixtureP.periodo2015.BuilderPeriodo(3000));
    periodosA.add(fixtureP.periodo2016.BuilderPeriodo(4000));
    periodosA.add(fixtureP.periodo2017.BuilderPeriodo(5000));

    cuentaA = new Cuenta("CuentaA", periodosA);

    List<Periodo> periodosB = new ArrayList<Periodo>();
    periodosB.add(fixtureP.periodo2012.BuilderPeriodo(1000));
    periodosB.add(fixtureP.periodo2013.BuilderPeriodo(1500));
    periodosB.add(fixtureP.periodo2014.BuilderPeriodo(2500));
    periodosB.add(fixtureP.periodo2015.BuilderPeriodo(3500));
    periodosB.add(fixtureP.periodo2016.BuilderPeriodo(4000));
    periodosB.add(fixtureP.periodo2017.BuilderPeriodo(5500));

    cuentaB = new Cuenta("CuentaB", periodosB);

    pasivoCorriente = new Cuenta("PasivoCorriente");
    pasivoNoCorriente = new Cuenta("PasivoNoCorriente");
    activoCorriente = new Cuenta("ActivoCorriente");
    activoNoCorriente = new Cuenta("ActivoNoCorriente");
    beneficio = new Cuenta("Benificio");
    ventas = new Cuenta("Ventas");
    pasivoTotal = new Cuenta("PasivoTotal");
    patrimonioNeto = new Cuenta("PatrimonioNeto");
  }
}
