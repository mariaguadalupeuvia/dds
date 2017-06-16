package ar.org.utn.ddstpanual.tree.utils;

import ar.org.utn.ddstpanual.tree.model.Arbol;
import ar.org.utn.ddstpanual.tree.model.Node;

public class ArbolUtil {

  public static Arbol convertFormulaToArbol(String formula) {
    Arbol arbol = new Arbol();
    arbol.setFormula(formula);
    String formulaPost = FormulaUtil.inToPost(formula);
    Node root;
    
    //TODO recorrer la formula post orden creando los nodos
    
    return arbol;
  }

}
