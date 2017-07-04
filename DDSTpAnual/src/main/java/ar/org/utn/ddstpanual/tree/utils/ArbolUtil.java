package ar.org.utn.ddstpanual.tree.utils;

//import java.util.Scanner;
import java.util.Stack;

import ar.org.utn.ddstpanual.exception.ServiceException;
import ar.org.utn.ddstpanual.model.Empresa;
//import ar.org.utn.ddstpanual.model.FormulaIndicador;
import ar.org.utn.ddstpanual.model.Periodo;
import ar.org.utn.ddstpanual.tree.model.Arbol;
import ar.org.utn.ddstpanual.tree.model.Node;
import ar.org.utn.ddstpanual.tree.model.OperadorNode;

public class ArbolUtil {

  public static Arbol convertFormulaToArbol(String formula) throws ServiceException {
    Arbol arbol = new Arbol();
    arbol.setFormula(formula);
    Stack<Node> formulaPost = FormulaUtil.inToPost(formula);
    
    while(formulaPost.peek() == null){formulaPost.pop();}
    
    Node root = formulaPost.pop();
    
    if (root.getTypeNode().equals(Node.OPERADOR)) {
      ((OperadorNode) root).setRigthNode(obtenerSiguienteNodo(formulaPost));
      ((OperadorNode) root).setLeftNode(obtenerSiguienteNodo(formulaPost));
    }
    arbol.setRoot(root);
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

  public double obtenerValor(String formula, Periodo periodo, Empresa empresa){
	double valor = 0;
	try {
		Arbol arbol = convertFormulaToArbol(formula);
		valor = arbol.getRoot().obtenerValor(periodo, empresa);
	} catch (ServiceException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	  catch(NullPointerException e){
		  e.printStackTrace();
	  }
	return valor;
	  
  }

  public static void main(String[] args) {
    // Entrada de datos
    try {
    	Arbol arbol = convertFormulaToArbol("[c1]+[c2]/(([c3]*[c4])+50)");
    	System.out.println(arbol.getRoot().getTypeNode());
    } catch (ServiceException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }

  }

}
