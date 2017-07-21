package ar.org.utn.ddstpanual.model.tree;

import ar.org.utn.ddstpanual.exception.NodeException;
import ar.org.utn.ddstpanual.model.Empresa;

public class ValorNode extends Node {

  private double valor;

  public ValorNode(final String valor) {
    this.setTypeNode(Node.VALOR);
    this.valor = Double.valueOf(valor);
  }

  public void setValor(final double valor) {
    this.valor = valor;
  }

  @Override
  public double obtenerValor(final String fechaPeriodo, final Empresa empresa) throws NodeException {
    return valor;
  }

}
