package ar.org.utn.ddstpanual.model;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.uqbar.commons.utils.Observable;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Observable
@Table(name = "EMPRESAS")
@AllArgsConstructor
@NoArgsConstructor
public @Data class Empresa {

  @Id
  @GeneratedValue
  private int id;
  @OneToMany 
  @Cascade(value=CascadeType.ALL)
  private List<Cuenta> cuentas;
  private String nombre;

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

  @Override
  public String toString() {
    final StringBuilder builder = new StringBuilder();
    builder.append("{\nnombre : " + nombre + ",\n");
    builder.append("cuentas : " + cuentas.toString() + "\n}");
    return builder.toString();
  }

}
