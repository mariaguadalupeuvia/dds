package ar.org.utn.ddstpanual.tree.ejemplo;

public class Node {
  public char data;
  public Node leftChild;
  public Node rightChild;

  public Node(char x) {
    data = x;
  }

  public void displayNode() {
    System.out.print(data);
  }
}
