package ar.org.utn.ddstpanual.tree.model;

import ar.org.utn.ddstpanual.model.Empresa;
import ar.org.utn.ddstpanual.model.Periodo;

public class ValorNode extends Node {

  private double valor;

  public ValorNode(String valor) {
    this.setTypeNode(Node.VALOR);
    this.valor = Double.valueOf(valor);
  }

  public void setValor(double valor) {
    this.valor = valor;
  }

  @Override
  public double obtenerValor(Periodo periodo, Empresa empresa) {
	  // TODO Auto-generated method stub
	  return valor;
  }

}
