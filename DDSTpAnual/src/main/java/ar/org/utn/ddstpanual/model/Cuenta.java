package ar.org.utn.ddstpanual.model;

import org.uqbar.commons.utils.Observable;

import java.util.List;

@Observable
public class Cuenta {

  String nombre;
  List<Periodo> periodos;

  public Cuenta() {}

  public Cuenta(final String nombre, final List<Periodo> periodos) {
    this.nombre = nombre;
    this.periodos = periodos;
  }


  public double obtenerValor(final String fecha) {
    double valor = 0;
    for (final Periodo p : periodos) {
      if (p.getFecha().equals(fecha))
        valor = p.getValor();
    }
    return valor;
  }

  public String getNombre() {
    return nombre;
  }

  public void setNombre(final String nombre) {
    this.nombre = nombre;
  }

  public List<Periodo> getPeriodos() {
    return periodos;
  }

  public void setPeriodos(final List<Periodo> periodos) {
    this.periodos = periodos;
  }

  @Override
  public boolean equals(final Object o) {

    if (o == this)
      return true;
    if (!(o instanceof Cuenta)) {
      return false;
    }

    final Cuenta cuenta = (Cuenta) o;

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
    final StringBuilder builder = new StringBuilder();
    builder.append("{\n nombre : " + nombre + ", \n");
    builder.append("valores : " + periodos.toString() + "\n}");
    return builder.toString();
  }

}
