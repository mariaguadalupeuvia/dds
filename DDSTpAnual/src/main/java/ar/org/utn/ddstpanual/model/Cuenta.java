package ar.org.utn.ddstpanual.model;

import java.util.Map;

public class Cuenta {

  String nombre;
  Map<Integer, Long> valores;

  public String getNombre() {
    return nombre;
  }

  public void setNombre(String nombre) {
    this.nombre = nombre;
  }

  public Map<Integer, Long> getValores() {
    return valores;
  }

  public void setValores(Map<Integer, Long> valores) {
    this.valores = valores;
  }

}
