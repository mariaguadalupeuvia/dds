package ar.org.utn.ddstpanual.tree;

import ar.org.utn.ddstpanual.tree.model.Node;

public class PilaNode {
  private Node[] a;
  private int top, m;

  public PilaNode(int max) {
    m = max;
    a = new Node[m];
    top = -1;
  }

  public void push(Node key) {
    a[++top] = key;
  }

  public Node pop() {
    return (a[top--]);
  }

  public boolean isEmpty() {
    return (top == -1);
  }
}
