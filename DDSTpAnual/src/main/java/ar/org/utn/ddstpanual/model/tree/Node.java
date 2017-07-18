package ar.org.utn.ddstpanual.model.tree;

import ar.org.utn.ddstpanual.exception.NodeException;
import ar.org.utn.ddstpanual.model.Empresa;
import ar.org.utn.ddstpanual.model.Periodo;

public abstract class Node {

  public static final Integer OPERADOR = 1;
  public static final Integer VALOR = 2;
  public static final Integer INDICADOR = 3;
  public static final Integer CUENTA = 4;

  private Integer typeNode;

  public abstract double obtenerValor(Periodo periodo, Empresa empresa) throws NodeException;

  public Integer getTypeNode() {
    return typeNode;
  }

  public void setTypeNode(final Integer typeNode) {
    this.typeNode = typeNode;
  }

}
