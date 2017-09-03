package ar.org.utn.ddstpanual.model.metodologia;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.uqbar.commons.utils.Observable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

@Observable
@Entity
@Table(name = "METODOLOGIAS")
@AllArgsConstructor
@NoArgsConstructor

@Data public class Metodologia 
{

  @Id
  @GeneratedValue
  private int id;
  
  private String nombre;
  @OneToMany
  @Cascade(value=CascadeType.ALL)
  @JoinColumn(name="metodologia_id")
  private List<Condicion> condiciones;
  @Transient
  private Orden orden;


  public Metodologia(String nombre, List<Condicion> condiciones, Orden orden) {
    this.nombre = nombre;
    this.condiciones = condiciones;
    this.orden = orden;
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
    builder.append("]");
    builder.append("}");
    return builder.toString();
  }


}
