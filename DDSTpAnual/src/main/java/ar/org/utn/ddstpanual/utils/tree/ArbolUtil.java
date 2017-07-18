package ar.org.utn.ddstpanual.utils.tree;

// import java.util.Scanner;
import java.util.Stack;

import ar.org.utn.ddstpanual.exception.ArbolException;
import ar.org.utn.ddstpanual.exception.FormulaException;
import ar.org.utn.ddstpanual.exception.NodeException;
import ar.org.utn.ddstpanual.model.Empresa;
// import ar.org.utn.ddstpanual.model.FormulaIndicador;
import ar.org.utn.ddstpanual.model.Periodo;
import ar.org.utn.ddstpanual.model.tree.Arbol;
import ar.org.utn.ddstpanual.model.tree.Node;
import ar.org.utn.ddstpanual.model.tree.OperadorNode;

public class ArbolUtil {

  public static Arbol convertFormulaToArbol(final String formula) throws ArbolException {
    final Arbol arbol = new Arbol();
    arbol.setFormula(formula);
    Stack<Node> formulaPost;
    try {
      formulaPost = FormulaUtil.inToPost(formula);
    } catch (final FormulaException e) {
      throw new ArbolException(e.getMessage());
    }

    while (formulaPost.peek() == null) {
      formulaPost.pop();
    }

    final Node root = formulaPost.pop();

    if (root.getTypeNode().equals(Node.OPERADOR)) {
      ((OperadorNode) root).setRigthNode(obtenerSiguienteNodo(formulaPost));
      ((OperadorNode) root).setLeftNode(obtenerSiguienteNodo(formulaPost));
    }
    arbol.setRoot(root);
    return arbol;
  }

  public static Node obtenerSiguienteNodo(final Stack<Node> formulaPost) {
    final Node nodo = formulaPost.pop();
    if (nodo.getTypeNode().equals(Node.OPERADOR)) {
      ((OperadorNode) nodo).setRigthNode(obtenerSiguienteNodo(formulaPost));
      ((OperadorNode) nodo).setLeftNode(obtenerSiguienteNodo(formulaPost));
    } else {
      return nodo;
    }
    return nodo;
  }

  public double obtenerValor(final String formula, final Periodo periodo, final Empresa empresa) throws ArbolException {
    double valor = 0;
    try {
      final Arbol arbol = convertFormulaToArbol(formula);
      try {
        valor = arbol.getRoot().obtenerValor(periodo, empresa);
      } catch (final NodeException e) {
        throw new ArbolException(e.getMessage());
      }
    } catch (final NullPointerException e) {
      throw new ArbolException(e.getMessage());
    } catch (final StackOverflowError e) {
      throw new ArbolException(e.getMessage());
    }

    return valor;

  }
}
