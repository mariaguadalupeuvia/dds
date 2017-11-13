package ar.org.utn.ddstpanual.model;

import org.uqbar.commons.utils.Observable;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Observable
@Table(name = "EMPRESA")
@AllArgsConstructor
@NoArgsConstructor
public @Data class Empresa {

  @Id
  @GeneratedValue
  private int id;
  @OneToMany(cascade = CascadeType.PERSIST)
  @JoinColumn(name = "empresa_id")
  private List<Cuenta> cuentas;
  private String nombre;

  public Empresa(String nombre) {
    this.nombre = nombre;
    this.cuentas = new ArrayList<Cuenta>();
  }
  
  public Empresa(String nombre, List<Cuenta> cuentas) {
    this.nombre = nombre;
    this.cuentas = cuentas;
  }

  public double obtenerValor(final String nombreCuenta, final String periodo) {
    return cuentas.stream()
        .filter(c -> c.getNombre().equals(nombreCuenta))
        .map(c -> c.obtenerValor(periodo))
        .findAny()
        .orElse(0.0);
  }

  @Override
  public boolean equals(final Object o) {
    if (o == this)
      return true;
    if (!(o instanceof Empresa)) {
      return false;
    }
    final Empresa empresa = (Empresa) o;
    return empresa.nombre.equals(nombre);
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
    builder.append("{\nnombre : " + nombre + ",\n");
    builder.append("cuentas : " + cuentas.toString() + "\n}");
    return builder.toString();
  }

}
