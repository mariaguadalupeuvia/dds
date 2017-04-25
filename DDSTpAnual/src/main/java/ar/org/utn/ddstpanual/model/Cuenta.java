package ar.org.utn.ddstpanual.model;

import java.util.Map;

public class Cuenta {

  String nombre;
  Map<String, Float> valores;

  public String getNombre() {
    return nombre;
  }

  public void setNombre(String nombre) {
    this.nombre = nombre;
  }

  public Map<String, Float> getValores() {
    return valores;
  }

  public void setValores(Map<String, Float> valores) {
    this.valores = valores;
  }
  
  @Override
  public String toString(){
    StringBuilder builder = new StringBuilder();
    builder.append("{\n nombre : " + nombre + ", \n");
    builder.append("valores : " + valores.toString() + "\n}");
    return builder.toString();
  }

}
