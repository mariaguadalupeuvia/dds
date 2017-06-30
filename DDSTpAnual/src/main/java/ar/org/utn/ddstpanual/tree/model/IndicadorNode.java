package ar.org.utn.ddstpanual.tree.model;

import ar.org.utn.ddstpanual.archivo.impl.IndicadorArchivoImpl;

public class IndicadorNode extends Node {

  private String nombreIndicador;
  private Arbol arbol;

  public IndicadorNode(String nombreIndicador) {
    this.setTypeNode(Node.INDICADOR);
    this.nombreIndicador = nombreIndicador;
    this.arbol = new IndicadorArchivoImpl().obtenerArbolPorIdicador(nombreIndicador);
  }

  @Override
  public Integer obtenerValor(Integer periodo) {
    // TODO Se arma el arbol de este indicador y se devuelve su valor.
    return arbol.getRoot().obtenerValor(periodo);
  }

  public String getNombreIndicador() {
    return nombreIndicador;
  }

  public void setNombreIndicador(String nombreIndicador) {
    this.nombreIndicador = nombreIndicador;
  }

  public Arbol getArbol() {
    return arbol;
  }

  public void setArbol(Arbol arbol) {
    this.arbol = arbol;
  }
}
