package ar.org.utn.ddstpanual.tree.model;

public class OperadorNode extends Node {

  private String operador;

  @Override
  public Integer obtenerValor(Integer periodo) {
    if (operador.equals("+")) {
      return (this.getLeftNode().obtenerValor(periodo) + this.getRigthNode().obtenerValor(periodo));
    }
    if (operador.equals("-")) {
      return (this.getLeftNode().obtenerValor(periodo) - this.getRigthNode().obtenerValor(periodo));
    }
    if (operador.equals("*")) {
      return (this.getLeftNode().obtenerValor(periodo) * this.getRigthNode().obtenerValor(periodo));
    }
    if (operador.equals("/")) {
      return (this.getLeftNode().obtenerValor(periodo) / this.getRigthNode().obtenerValor(periodo));
    }
    return null;
  }


  public String getOperador() {
    return operador;
  }


  public void setOperador(String operador) {
    this.operador = operador;
  }

}
