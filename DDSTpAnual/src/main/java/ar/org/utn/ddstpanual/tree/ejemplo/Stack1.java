package ar.org.utn.ddstpanual.tree.ejemplo;

public class Stack1 {
  private Node[] a;
  private int top, m;

  public Stack1(int max) {
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
