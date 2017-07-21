package ar.org.utn.ddstpanual.model.metodologia;

import org.uqbar.commons.utils.Observable;

import java.util.List;

@Observable
public class Metodologia {

  private String nombre;
  private List<Condicion> condiciones;

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

}
