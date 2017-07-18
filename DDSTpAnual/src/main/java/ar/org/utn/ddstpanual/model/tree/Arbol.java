package ar.org.utn.ddstpanual.model.tree;

public class Arbol {

  private String formula;
  private Node root;

  public String getFormula() {
    return formula;
  }

  public void setFormula(final String formula) {
    this.formula = formula;
  }

  public Node getRoot() {
    return root;
  }

  public void setRoot(final Node root) {
    this.root = root;
  }

}
