package ar.org.utn.ddstpanual.tree.model;

public class IndicadorNode extends Node {

  private String nombreIndicador;

  public IndicadorNode(String nombreIndicador) {
    this.setTypeNode(Node.INDICADOR);
    this.nombreIndicador = nombreIndicador;
  }

  @Override
  public Integer obtenerValor(Integer periodo) {
    // TODO Se arma el arbol de este indicador y se devuelve su valor.
    return null;
  }

  public String getNombreIndicador() {
    return nombreIndicador;
  }

  public void setNombreIndicador(String nombreIndicador) {
    this.nombreIndicador = nombreIndicador;
  }
}
