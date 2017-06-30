package ar.org.utn.ddstpanual.tree.utils;

import java.util.Scanner;
import java.util.Stack;

import ar.org.utn.ddstpanual.exception.ServiceException;
import ar.org.utn.ddstpanual.tree.model.Arbol;
import ar.org.utn.ddstpanual.tree.model.Node;
import ar.org.utn.ddstpanual.tree.model.OperadorNode;

public class ArbolUtil {

  public static Arbol convertFormulaToArbol(String formula) throws ServiceException {
    Arbol arbol = new Arbol();
    arbol.setFormula(formula);
    Stack<Node> formulaPost = FormulaUtil.inToPost(formula);
    Node root = formulaPost.pop();

    if (root.getTypeNode().equals(Node.OPERADOR)) {
      ((OperadorNode) root).setRigthNode(obtenerSiguienteNodo(formulaPost));
      ((OperadorNode) root).setLeftNode(obtenerSiguienteNodo(formulaPost));
    }

    return arbol;
  }

  public static Node obtenerSiguienteNodo(Stack<Node> formulaPost) {
    Node nodo = formulaPost.pop();
    if (nodo.getTypeNode().equals(Node.OPERADOR)) {
      ((OperadorNode) nodo).setRigthNode(obtenerSiguienteNodo(formulaPost));
      ((OperadorNode) nodo).setLeftNode(obtenerSiguienteNodo(formulaPost));
    } else {
      return nodo;
    }
    return nodo;
  }


  public static void main(String[] args) {
    // Entrada de datos
    System.out.println("*Escribe una expresión algebraica: ");
    Scanner leer = new Scanner(System.in);
    try {
      Stack<Node> formulaPost = FormulaUtil.inToPost(leer.nextLine());
    } catch (ServiceException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }

  }

}
