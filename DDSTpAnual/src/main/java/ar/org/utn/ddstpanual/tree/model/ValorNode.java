package ar.org.utn.ddstpanual.tree.model;

public class ValorNode extends Node {

  private Integer valor;

  @Override
  public Integer obtenerValor(Integer periodo) {
    return valor;
  }

  public void setValor(Integer valor) {
    this.valor = valor;
  }

}
