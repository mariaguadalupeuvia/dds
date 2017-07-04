package ar.org.utn.ddstpanual.tree.model;

import ar.org.utn.ddstpanual.model.Empresa;
import ar.org.utn.ddstpanual.model.Periodo;

public class CuentaNode extends Node {

  public String nombreCuenta;

  public CuentaNode(String nombreCuenta) {
    this.setTypeNode(Node.CUENTA);
    this.nombreCuenta = nombreCuenta.substring(1,nombreCuenta.length()-1);
    System.out.println(this.nombreCuenta);
  }

  @Override
  public double obtenerValor(Periodo periodo, Empresa empresa) {
	double valor = empresa.obtenerValor(nombreCuenta, periodo.getFecha());
	System.out.println(valor + "\n");
    return valor;
  }

}
