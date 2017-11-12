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

import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Table(name = "CUENTA")
@Observable
@NoArgsConstructor
public @Data class Cuenta {

  @Id
  @GeneratedValue
  private int id;
  private String nombre;
  @OneToMany(cascade = CascadeType.PERSIST)
  @JoinColumn(name = "cuenta_id")
  private List<Periodo> periodos;

 public Cuenta(final String nombre){
   this.nombre = nombre;
   this.periodos = new ArrayList<Periodo>();
 }

  public Cuenta(final String nombre, final List<Periodo> periodos) {
    this.nombre = nombre;
    this.periodos = periodos;
  }

  public double obtenerValor(final String fecha) {
    return periodos.stream()                        
    .filter(p -> p.getFecha().equals(fecha))
    .map(p -> p.getValor())
    .findAny()                                      
    .orElse(0.0);   
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
