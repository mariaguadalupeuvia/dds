package ar.org.utn.ddstpanual.model.metodologia;

import org.uqbar.commons.utils.Observable;

import ar.org.utn.ddstpanual.exception.CondicionException;
import ar.org.utn.ddstpanual.exception.FiltroException;
import ar.org.utn.ddstpanual.model.Empresa;
import ar.org.utn.ddstpanual.model.Indicador;

@Observable
public class Condicion {

  private Indicador indicador;
  private Filtro filtro;

  public Condicion() {

  }

  public Condicion(Indicador indicador, Filtro filtro) {
    this.indicador = indicador;
    this.filtro = filtro;
  }

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

  public boolean cumpleCondicion(Empresa empresa) throws CondicionException {
    try {
      return filtro.cumpleCondicion(indicador, empresa);
    } catch (FiltroException e) {
      throw new CondicionException(e.getMessage());
    }
  }

  public String toJson() {
    StringBuilder builder = new StringBuilder();
    builder.append("{");
    builder.append("\"indicador\" : ");
    builder.append(indicador.toJson() + ",");
    builder.append("\"filtro\" : ");
    builder.append(filtro.toJson());
    builder.append("}");
    return builder.toString();
  }

}
