package ar.org.utn.ddstpanual.tree.model;

public abstract class Node {

  public static final Integer OPERADOR = 1;
  public static final Integer VALOR = 1;
  public static final Integer INDICADOR = 1;
  public static final Integer CUENTA = 1;

  private Integer typeNode;

  public abstract Integer obtenerValor(Integer periodo);

  public Integer getTypeNode() {
    return typeNode;
  }

  public void setTypeNode(Integer typeNode) {
    this.typeNode = typeNode;
  }

}
