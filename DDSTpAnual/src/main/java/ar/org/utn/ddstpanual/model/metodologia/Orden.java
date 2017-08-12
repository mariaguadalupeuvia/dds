package ar.org.utn.ddstpanual.model.metodologia;

import org.uqbar.commons.utils.Observable;

import ar.org.utn.ddstpanual.model.Indicador;

@Observable
public class Orden {

  private Indicador indicador;
  private TipoOrden tipoOrden;
  
  public Orden(){}
  
  public Orden(Indicador indicador, TipoOrden tipoOrden){
    this.indicador = indicador;
    this.tipoOrden = tipoOrden;
  }
  public Indicador getIndicador() {
    return indicador;
  }

  public void setIndicador(Indicador indicador) {
    this.indicador = indicador;
  }

  public TipoOrden getTipoOrden() {
    return tipoOrden;
  }

  public void setTipoOrden(TipoOrden tipoOrden) {
    this.tipoOrden = tipoOrden;
  }

}
