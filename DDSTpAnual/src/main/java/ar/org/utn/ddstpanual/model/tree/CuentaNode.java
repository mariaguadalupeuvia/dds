package ar.org.utn.ddstpanual.model.tree;

import ar.org.utn.ddstpanual.exception.NodeException;
import ar.org.utn.ddstpanual.model.Empresa;
import ar.org.utn.ddstpanual.model.Periodo;

public class CuentaNode extends Node {

  public String nombreCuenta;

  public CuentaNode(final String nombreCuenta) {
    this.setTypeNode(Node.CUENTA);
    this.nombreCuenta = nombreCuenta.substring(1, nombreCuenta.length() - 1);
    System.out.println(this.nombreCuenta);
  }

  @Override
  public double obtenerValor(final Periodo periodo, final Empresa empresa) throws NodeException {
    final double valor = empresa.obtenerValor(nombreCuenta, periodo.getFecha());
    System.out.println(valor + "\n");
    if (valor == 0) {
      throw new NodeException(
          "La cuenta " + nombreCuenta + " no existe para la empresa " + empresa.getNombre() + " en el período " + periodo.getFecha());
    }
    return valor;
  }

}
