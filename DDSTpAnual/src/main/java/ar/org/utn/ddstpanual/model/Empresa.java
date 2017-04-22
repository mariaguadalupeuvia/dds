package ar.org.utn.ddstpanual.model;

import java.util.List;

public class Empresa {

  List<Cuenta> cuentas;

  public List<Cuenta> getCuentas() {
    return cuentas;
  }

  public void setCuentas(List<Cuenta> cuentas) {
    this.cuentas = cuentas;
  }

}
