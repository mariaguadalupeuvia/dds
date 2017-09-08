package ar.org.utn.ddstpanual.model.metodologia;

import org.uqbar.commons.utils.Observable;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OrderColumn;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Observable
@Entity
@Table(name = "METODOLOGIA")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Metodologia {

  @Id
  @GeneratedValue
  private int id;

  private String nombre;
  @OneToMany(cascade = CascadeType.PERSIST)
  @JoinColumn(name = "metodologia_id")
  private List<Condicion> condiciones;
  
  @OneToMany(cascade = CascadeType.PERSIST)
  @JoinColumn(name = "metodologia_id")
  @OrderColumn(name="prioridad")
  private List<Orden> ordenes;

  
  public Metodologia(String nombre, List<Condicion> condiciones, List<Orden> ordenes) {
    this.nombre = nombre;
    this.condiciones = condiciones;
    this.ordenes = ordenes;
  }


  public String toJson() {
    StringBuilder builder = new StringBuilder();
    builder.append("{");
    builder.append("\"nombre\" : ");
    builder.append("\"" + nombre + "\"");
    builder.append(",");
    builder.append("\"condiciones\" : [");
    if (condiciones.size() > 0) {
      for (Condicion condicion : condiciones) {
        builder.append(condicion.toJson());
        builder.append(",");
      }
      builder.deleteCharAt(builder.length() - 1);
    }
    builder.append("\"ordenes\" : [");
    if (ordenes.size() > 0) {
      for (Orden orden : ordenes) {
        builder.append(orden.toJson());
        builder.append(",");
      }
      builder.deleteCharAt(builder.length() - 1);
    }
    builder.append("]");
    builder.append("}");
    return builder.toString();
  }


}
