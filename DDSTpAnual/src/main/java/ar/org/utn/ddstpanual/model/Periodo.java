package ar.org.utn.ddstpanual.model;

import org.uqbar.commons.utils.Observable;

@Observable
public class Periodo {

  private String fecha;
  private double valor;
  
  public Periodo(){}
  public Periodo(String fecha){
	  this.fecha = fecha;
	  valor = 0;
  }
  
  public Periodo(String fecha, float valor){
	  this.fecha = fecha;
	  this.valor = valor;
  }

  public String getFecha() {
    return fecha;
  }

  public void setFecha(String fecha) {
    this.fecha = fecha;
  }

  public double getValor() {
    return valor;
  }

  public void setValor(double valor) {
    this.valor = valor;
  }
}
