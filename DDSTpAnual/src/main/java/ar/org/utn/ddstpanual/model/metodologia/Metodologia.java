package ar.org.utn.ddstpanual.model.metodologia;

import org.uqbar.commons.utils.Observable;

import java.util.List;

@Observable
public class Metodologia {

  private String nombre;
  private List<Condicion> condiciones;
  private Orden orden;

  public Metodologia() {

  }

  public Metodologia(String nombre, List<Condicion> condiciones, Orden orden) {
    this.nombre = nombre;
    this.condiciones = condiciones;
    this.orden = orden;
  }

  public String getNombre() {
    return nombre;
  }

  public void setNombre(String nombre) {
    this.nombre = nombre;
  }

  public List<Condicion> getCondiciones() {
    return condiciones;
  }

  public void setCondiciones(List<Condicion> condiciones) {
    this.condiciones = condiciones;
  }
  
  public Orden getOrden() {
    return orden;
  }
  
  public void setOrden(Orden orden) {
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
