package ar.org.utn.ddstpanual.tree.model;

import ar.org.utn.ddstpanual.archivo.impl.IndicadorArchivoImpl;
import ar.org.utn.ddstpanual.model.Empresa;
import ar.org.utn.ddstpanual.model.Periodo;
import ar.org.utn.ddstpanual.service.IndicadorService;
import ar.org.utn.ddstpanual.tree.utils.ArbolUtil;

public class IndicadorNode extends Node {

  private String nombreIndicador;
  private Arbol arbol;
  private ArbolUtil util;

  public IndicadorNode(String nombreIndicador) {
    this.setTypeNode(Node.INDICADOR);
    this.nombreIndicador = nombreIndicador;
    //this.arbol = new IndicadorArchivoImpl().obtenerArbolPorIdicador(nombreIndicador);
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

  @Override
  public double obtenerValor(Periodo periodo, Empresa empresa) {
	 return util.obtenerValor(nombreIndicador, periodo, empresa);
  }
}
