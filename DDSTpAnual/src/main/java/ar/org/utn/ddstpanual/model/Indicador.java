package ar.org.utn.ddstpanual.model;

import org.apache.commons.lang3.StringUtils;
import org.uqbar.commons.utils.Observable;

@Observable
public class Indicador {

  String nombre;
  String formula;

  public Indicador() {}

  public Indicador(final String nombre, final String formula) {
    this.nombre = nombre;
    this.formula = formula;
  }

  public String getNombre() {
    return nombre;
  }

  public void setNombre(final String nombre) {
    this.nombre = nombre;
  }

  public String getFormula() {
    return formula;
  }

  public void setFormula(final String formula) {
    this.formula = formula;
  }

  public void sacarEspacios() {
    nombre = StringUtils.remove(nombre, " ");
    formula = StringUtils.remove(formula, " ");
  }

  @Override
  public String toString() {
    final StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append(this.formula);
    return stringBuilder.toString();
  }
}
