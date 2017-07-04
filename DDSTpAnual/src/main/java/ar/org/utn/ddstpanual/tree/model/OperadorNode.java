package ar.org.utn.ddstpanual.tree.model;

import ar.org.utn.ddstpanual.model.Empresa;
import ar.org.utn.ddstpanual.model.Periodo;

public class OperadorNode extends Node {

  private Node leftNode;
  private Node rigthNode;
  private String operador;

  public OperadorNode(String operador) {
    this.setTypeNode(Node.OPERADOR);
    this.operador = operador;
  }

  @Override
  public double obtenerValor(Periodo periodo, Empresa empresa) {
	double valor = 0;
    if (operador.equals("+")) {
      valor = (leftNode.obtenerValor(periodo, empresa) + rigthNode.obtenerValor(periodo, empresa));
    }
    if (operador.equals("-")) {
      valor = (leftNode.obtenerValor(periodo, empresa) - rigthNode.obtenerValor(periodo, empresa));
    }
    if (operador.equals("*")) {
      valor = (leftNode.obtenerValor(periodo, empresa) * rigthNode.obtenerValor(periodo, empresa));
    }
    if (operador.equals("/")) {
      valor = (leftNode.obtenerValor(periodo, empresa) / rigthNode.obtenerValor(periodo, empresa));
    }
    return valor;
  }


  public String getOperador() {
    return operador;
  }


  public void setOperador(String operador) {
    this.operador = operador;
  }

  public Node getLeftNode() {
    return leftNode;
  }


  public void setLeftNode(Node leftNode) {
    this.leftNode = leftNode;
  }

  public Node getRigthNode() {
    return rigthNode;
  }


  public void setRigthNode(Node rigthNode) {
    this.rigthNode = rigthNode;
  }

}
