package ar.org.utn.ddstpanual.model;

import org.uqbar.commons.utils.Observable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Observable
@Table(name = "PERIODOS")
@NoArgsConstructor

public @Data class Periodo {

  @Id
  @GeneratedValue
  private int id;
  private String fecha;
  private double valor;

  public Periodo(final String fecha) {
    this.fecha = fecha;
    valor = 0;
  }

  public Periodo(final String fecha, final float valor) {
    this.fecha = fecha;
    this.valor = valor;
  }

  @Override
  public String toString() {
    final StringBuilder builder = new StringBuilder();
    builder.append("{\n fecha : " + fecha + ", \n");
    builder.append("valor : " + valor + "\n}");
    return builder.toString();
  }
}
