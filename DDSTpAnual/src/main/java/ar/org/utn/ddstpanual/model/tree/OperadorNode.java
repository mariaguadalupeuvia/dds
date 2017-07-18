package ar.org.utn.ddstpanual.model.tree;

import ar.org.utn.ddstpanual.exception.NodeException;
import ar.org.utn.ddstpanual.model.Empresa;
import ar.org.utn.ddstpanual.model.Periodo;

public class OperadorNode extends Node {

  private Node leftNode;
  private Node rigthNode;
  private String operador;

  public OperadorNode(final String operador) {
    this.setTypeNode(Node.OPERADOR);
    this.operador = operador;
  }

  @Override
  public double obtenerValor(final Periodo periodo, final Empresa empresa) throws NodeException {
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


  public void setOperador(final String operador) {
    this.operador = operador;
  }

  public Node getLeftNode() {
    return leftNode;
  }


  public void setLeftNode(final Node leftNode) {
    this.leftNode = leftNode;
  }

  public Node getRigthNode() {
    return rigthNode;
  }


  public void setRigthNode(final Node rigthNode) {
    this.rigthNode = rigthNode;
  }

}
