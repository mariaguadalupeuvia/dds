package ar.org.utn.ddstpanual.model;

import org.uqbar.commons.utils.Observable;

@Observable
public class Periodo {

  private String fecha;
  private float valor;
  
  public Periodo(){}
  public Periodo(String fecha){
	  this.fecha = fecha;
	  valor = 0;
  }

  public String getFecha() {
    return fecha;
  }

  public void setFecha(String fecha) {
    this.fecha = fecha;
  }

  public float getValor() {
    return valor;
  }

  public void setValor(float valor) {
    this.valor = valor;
  }
}
