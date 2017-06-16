package ar.org.utn.ddstpanual.tree.ejemplo;

public class Tree {
  private Node root;

  public Tree() {
    root = null;
  }

  public void insert(String s) {
    Conversion conversion = new Conversion(s);
    s = conversion.inToPost();
    Stack1 stk = new Stack1(s.length());
    s = s + "#";
    int i = 0;
    char symbol = s.charAt(i);
    Node newNode;
    while (symbol != '#') {
      if (symbol >= '0' && symbol <= '9' || symbol >= 'A' && symbol <= 'Z'
          || symbol >= 'a' && symbol <= 'z') {
        newNode = new Node(symbol);
        stk.push(newNode);
      } else if (symbol == '+' || symbol == '-' || symbol == '/' || symbol == '*') {
        Node ptr1 = stk.pop();
        Node ptr2 = stk.pop();
        newNode = new Node(symbol);
        newNode.leftChild = ptr2;
        newNode.rightChild = ptr1;
        stk.push(newNode);
      }
      symbol = s.charAt(++i);
    }
    root = stk.pop();
  }

  public void traverse(int type) {
    switch (type) {
      case 1:
        System.out.print("Preorder Traversal:-    ");
        preOrder(root);
        break;
      case 2:
        System.out.print("Inorder Traversal:-     ");
        inOrder(root);
        break;
      case 3:
        System.out.print("Postorder Traversal:-   ");
        postOrder(root);
        break;
      default:
        System.out.println("Invalid Choice");
    }
  }

  private void preOrder(Node localRoot) {
    if (localRoot != null) {
      localRoot.displayNode();
      preOrder(localRoot.leftChild);
      preOrder(localRoot.rightChild);
    }
  }

  private void inOrder(Node localRoot) {
    if (localRoot != null) {
      inOrder(localRoot.leftChild);
      localRoot.displayNode();
      inOrder(localRoot.rightChild);
    }
  }

  private void postOrder(Node localRoot) {
    if (localRoot != null) {
      postOrder(localRoot.leftChild);
      postOrder(localRoot.rightChild);
      localRoot.displayNode();
    }
  }
}
