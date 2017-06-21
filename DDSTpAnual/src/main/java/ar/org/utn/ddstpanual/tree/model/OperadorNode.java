package ar.org.utn.ddstpanual.tree.model;

public class OperadorNode extends Node {

  private Node leftNode;
  private Node rigthNode;
  private String operador;

  public OperadorNode(String operador) {
    this.setTypeNode(Node.OPERADOR);
    this.operador = operador;
  }

  @Override
  public Integer obtenerValor(Integer periodo) {
    if (operador.equals("+")) {
      return (leftNode.obtenerValor(periodo) + rigthNode.obtenerValor(periodo));
    }
    if (operador.equals("-")) {
      return (leftNode.obtenerValor(periodo) - rigthNode.obtenerValor(periodo));
    }
    if (operador.equals("*")) {
      return (leftNode.obtenerValor(periodo) * rigthNode.obtenerValor(periodo));
    }
    if (operador.equals("/")) {
      return (leftNode.obtenerValor(periodo) / rigthNode.obtenerValor(periodo));
    }
    return null;
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
