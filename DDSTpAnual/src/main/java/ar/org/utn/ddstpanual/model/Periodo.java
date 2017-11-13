package ar.org.utn.ddstpanual.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "PERIODO")
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
  public boolean equals(final Object o) {
    if (o == this)
      return true;
    if (!(o instanceof Periodo)) {
      return false;
    }
    final Periodo periodo = (Periodo) o;
    return periodo.fecha.equals(fecha);
  }

  @Override
  public int hashCode() {
    int result = 17;
    result = 31 * result + fecha.hashCode();
    return result;
  }

  @Override
  public String toString() {
    final StringBuilder builder = new StringBuilder();
    builder.append("{\n fecha : " + fecha + ", \n");
    builder.append("valor : " + valor + "\n}");
    return builder.toString();
  }
}
