package ar.org.utn.ddstpanual.model;

import java.util.Map;

public class Empresa {

  Map<String, Cuenta> cuentas;
  String nombre;

  public Map<String, Cuenta> getCuentas() {
    return cuentas;
  }

  public void setCuentas(Map<String, Cuenta> cuentas) {
    this.cuentas = cuentas;
  }

  public String getNombre() {
    return nombre;
  }

  public void setNombre(String nombre) {
    this.nombre = nombre;
  }

  @Override
  public String toString() {
    StringBuilder builder = new StringBuilder();
    builder.append("{\nnombre : " + nombre + ",\n");
    builder.append("cuentas : " + cuentas.toString() + "\n}");
    return builder.toString();
  }

}
