package ar.org.utn.ddstpanual.model;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.uqbar.commons.utils.Observable;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Data;


@Entity
@Table(name = "CUENTAS")
@Observable

public @Data class Cuenta {

  @Id
  @GeneratedValue
  private int id;
  private String nombre;
  @OneToMany
  @JoinColumn(name="cuenta_id")
  @Cascade(value = CascadeType.ALL)
  private List<Periodo> periodos;

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
