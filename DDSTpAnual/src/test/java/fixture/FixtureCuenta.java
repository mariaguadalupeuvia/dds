package fixture;

import ar.org.utn.ddstpanual.model.Cuenta;

public class FixtureCuenta {
  public Cuenta cuentaValorNulo;
  public Cuenta cuentaA;
  public Cuenta cuentaB;
  public Cuenta cuentaSinPeriodos;
  
  public FixtureCuenta(){
    FixturePeriodo fixtureP = new FixturePeriodo();
    cuentaValorNulo = new Cuenta();
    cuentaA = new Cuenta("CuentaA", fixtureP.periodosA);
    cuentaB = new Cuenta("CuentaB", fixtureP.periodosB);  
    cuentaSinPeriodos = new Cuenta("CuentaSinPeriodos");
    
  }
}
