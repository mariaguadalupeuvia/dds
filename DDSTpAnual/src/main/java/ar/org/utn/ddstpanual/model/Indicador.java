package ar.org.utn.ddstpanual.model;

import org.uqbar.commons.utils.Observable;

@Observable
public class Indicador {

  String nombre;
  String formula;

  public Indicador() {}

  public Indicador(String nombre, String formula) {
    this.nombre = nombre;
    this.formula = formula;
  }

  public String getNombre() {
    return nombre;
  }

  public void setNombre(String nombre) {
    this.nombre = nombre;
  }

  public String getFormula() {
    return formula;
  }

  public void setFormula(String formula) {
    this.formula = formula;
  }

  @Override
  public String toString() {
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append(this.formula);
    return stringBuilder.toString();
  }
}
