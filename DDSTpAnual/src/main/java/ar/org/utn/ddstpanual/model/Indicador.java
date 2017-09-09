package ar.org.utn.ddstpanual.model;

import org.apache.commons.lang3.StringUtils;
import org.uqbar.commons.utils.Observable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Observable
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "INDICADOR")
public @Data class Indicador {
  @Id
  @GeneratedValue
  private int id;

  String nombre;
  String formula;

  public void sacarEspacios() {
    nombre = StringUtils.remove(nombre, " ");
    formula = StringUtils.remove(formula, " ");
  }

  @Override
  public String toString() {
    final StringBuilder builder = new StringBuilder();
    builder.append(this.nombre);
    builder.append(": ");
    builder.append(this.formula);
    return builder.toString();
  }

  public String toJson() {
    StringBuilder builder = new StringBuilder();
    builder.append("{");
    builder.append("\"nombre\" : ");
    builder.append("\"" + nombre + "\",");
    builder.append("\"formula\" : ");
    builder.append("\"" + formula + "\"");
    builder.append("}");
    return builder.toString();
  }

  public Indicador(String nombre, String formula) {
    this.nombre = nombre;
    this.formula = formula;
  }
}
