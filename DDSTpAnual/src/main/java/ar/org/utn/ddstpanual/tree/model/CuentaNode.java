package ar.org.utn.ddstpanual.tree.model;

public class CuentaNode extends Node {

  public String nombreCuenta;

  public CuentaNode(String nombreCuenta) {
    this.setTypeNode(Node.CUENTA);
    this.nombreCuenta = nombreCuenta;
  }

  @Override
  public Integer obtenerValor(Integer periodo) {
    // TODO Buscar la cuenta con el periodo buscado
    return null;
  }

}
