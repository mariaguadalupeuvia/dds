package ar.org.utn.ddstpanual.model.tree;

import ar.org.utn.ddstpanual.exception.NodeException;
import ar.org.utn.ddstpanual.model.Empresa;

public class OperadorNode extends Node {

  private Node leftNode;
  private Node rigthNode;
  private String operador;

  public OperadorNode(final String operador) {
    this.setTypeNode(Node.OPERADOR);
    this.operador = operador;
  }

  @Override
	public double obtenerValor(final String fechaPeriodo, final Empresa empresa) throws NodeException {
    double valor = 0;
    if (operador.equals("+")) {
      valor = (leftNode.obtenerValor(fechaPeriodo, empresa) + rigthNode.obtenerValor(fechaPeriodo, empresa));
    }
    if (operador.equals("-")) {
      valor = (leftNode.obtenerValor(fechaPeriodo, empresa) - rigthNode.obtenerValor(fechaPeriodo, empresa));
    }
    if (operador.equals("*")) {
      valor = (leftNode.obtenerValor(fechaPeriodo, empresa) * rigthNode.obtenerValor(fechaPeriodo, empresa));
    }
    if (operador.equals("/")) {
      valor = verificarCociente(leftNode.obtenerValor(fechaPeriodo, empresa),rigthNode.obtenerValor(fechaPeriodo, empresa));
    }
    return valor;
  }

  
  private double verificarCociente(double numerador, double denominador) {
  	return (denominador != 0) ? (numerador/denominador):-1;
  	
  }
  
  public String getOperador() {
    return operador;
  }
  

  public void setOperador(final String operador) {
    this.operador = operador;
  }

  public Node getLeftNode() {
    return leftNode;
  }


  public void setLeftNode(final Node leftNode) {
    this.leftNode = leftNode;
  }

  public Node getRigthNode() {
    return rigthNode;
  }


  public void setRigthNode(final Node rigthNode) {
    this.rigthNode = rigthNode;
  }

}
