package ar.org.utn.ddstpanual.tree.model;

public abstract class Node {

  private Node leftNode;
  private Node rigthNode;

  public abstract Integer obtenerValor(Integer periodo);

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
