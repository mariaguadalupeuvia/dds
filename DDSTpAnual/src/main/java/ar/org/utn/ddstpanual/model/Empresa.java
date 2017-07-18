package ar.org.utn.ddstpanual.model;

import org.uqbar.commons.utils.Observable;

import java.util.ArrayList;
import java.util.List;

@Observable
public class Empresa {

  List<Cuenta> cuentas;
  String nombre;

  public Empresa() {}

  public Empresa(final List<Cuenta> cuentas, final String nombre) {
    this.cuentas = cuentas;
    this.nombre = nombre;
  }

  public double obtenerValor(final String nombreCuenta, final String periodo) {
    double valor = 0;
    for (final Cuenta cue : cuentas) {
      if (cue.getNombre().equals(nombreCuenta))
        valor = cue.obtenerValor(periodo);
    }
    return valor;

  }

  public List<Periodo> obtenerPeriodos() {
    final List<Periodo> periodos = new ArrayList<Periodo>();
    for (final Cuenta c : cuentas) {
      for (final Periodo per : c.getPeriodos()) {
        if (!periodos.stream().anyMatch(p -> p.getFecha().equals(per.getFecha()))) {
          periodos.add(per);
        }
      }
    }
    return periodos;
  }

  public List<Cuenta> getCuentas() {
    return cuentas;
  }

  public void setCuentas(final List<Cuenta> cuentas) {
    this.cuentas = cuentas;
  }

  public String getNombre() {
    return nombre;
  }

  public void setNombre(final String nombre) {
    this.nombre = nombre;
  }

  @Override
  public String toString() {
    final StringBuilder builder = new StringBuilder();
    builder.append("{\nnombre : " + nombre + ",\n");
    builder.append("cuentas : " + cuentas.toString() + "\n}");
    return builder.toString();
  }

}
