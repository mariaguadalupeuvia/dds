package ar.org.utn.ddstpanual.model;

import org.uqbar.commons.utils.Observable;

import java.util.List;

@Observable
public class Cuenta {

  String nombre;
  List<Periodo> periodos;

  public String getNombre() {
    return nombre;
  }

  public void setNombre(String nombre) {
    this.nombre = nombre;
  }

  public List<Periodo> getPeriodos() {
    return periodos;
  }

  public void setPeriodos(List<Periodo> periodos) {
    this.periodos = periodos;
  }

  @Override
  public boolean equals(Object o) {

    if (o == this)
      return true;
    if (!(o instanceof Cuenta)) {
      return false;
    }

    Cuenta cuenta = (Cuenta) o;

    return cuenta.nombre.equals(nombre);
  }

  @Override
  public int hashCode() {
    int result = 17;
    result = 31 * result + nombre.hashCode();
    return result;
  }

  @Override
  public String toString() {
    StringBuilder builder = new StringBuilder();
    builder.append("{\n nombre : " + nombre + ", \n");
    builder.append("valores : " + periodos.toString() + "\n}");
    return builder.toString();
  }

}
