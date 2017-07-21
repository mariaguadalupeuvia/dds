package ar.org.utn.ddstpanual.model.metodologia;

import org.uqbar.commons.utils.Observable;

import ar.org.utn.ddstpanual.exception.CondicionException;
import ar.org.utn.ddstpanual.exception.FiltroException;
import ar.org.utn.ddstpanual.model.Empresa;
import ar.org.utn.ddstpanual.model.Indicador;

@Observable
public class Condicion {

  String asd;
  private Indicador indicador;
  private Filtro filtro;

  public Indicador getIndicador() {
    return indicador;
  }

  public void setIndicador(Indicador indicador) {
    this.indicador = indicador;
  }

  public Filtro getFiltro() {
    return filtro;
  }

  public void setFiltro(Filtro filtro) {
    this.filtro = filtro;
  }

  public String getAsd() {
    return asd;
  }

  public void setAsd(String asd) {
    this.asd = asd;
  }

  public boolean cumpleCondicion(Empresa empresa) throws CondicionException {
    try {
      return filtro.cumpleCondicion(indicador, empresa);
    } catch (FiltroException e) {
      throw new CondicionException(e.getMessage());
    }
  }

}
