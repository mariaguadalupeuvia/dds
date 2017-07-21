package ar.org.utn.ddstpanual.model.metodologia;

import org.uqbar.commons.utils.Observable;

import ar.org.utn.ddstpanual.exception.FiltroException;
import ar.org.utn.ddstpanual.model.Empresa;
import ar.org.utn.ddstpanual.model.Indicador;
import ar.org.utn.ddstpanual.service.IndicadorService;
import ar.org.utn.ddstpanual.service.impl.IndicadorServiceImpl;

@Observable
public abstract class Filtro {

  private IndicadorService indicadorService;

  private Integer valor;
  private String nombre;

  public Integer getValor() {
    return valor;
  }

  public void setValor(Integer valor) {
    this.valor = valor;
  }

  public String getNombre() {
    return nombre;
  }

  public void setNombre(String nombre) {
    this.nombre = nombre;
  }

  public abstract boolean cumpleCondicion(Indicador indicador, Empresa empresa) throws FiltroException;

  public IndicadorService getIndicadorService() {
    if (indicadorService != null) {
      return indicadorService;
    }
    indicadorService = new IndicadorServiceImpl();
    return indicadorService;
  }

}
