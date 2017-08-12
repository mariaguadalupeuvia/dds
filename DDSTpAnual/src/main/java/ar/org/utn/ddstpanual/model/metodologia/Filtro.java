package ar.org.utn.ddstpanual.model.metodologia;

import org.uqbar.commons.utils.Observable;

import java.io.Serializable;

import ar.org.utn.ddstpanual.exception.FiltroException;
import ar.org.utn.ddstpanual.model.Empresa;
import ar.org.utn.ddstpanual.model.Indicador;
import ar.org.utn.ddstpanual.model.Periodo;
import ar.org.utn.ddstpanual.service.IndicadorService;
import ar.org.utn.ddstpanual.service.impl.IndicadorServiceImpl;

@Observable
public abstract class Filtro implements Serializable {

  /**
   * 
   */
  private static final long serialVersionUID = 1L;

  private IndicadorService indicadorService;

  private Integer valor;
  private String nombre;

  public Filtro() {

  }
  
  public Filtro(Integer valor) {
    this.valor = valor;
  }

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

  public abstract boolean cumpleCondicion(Indicador indicador, Empresa empresa, Periodo periodo) throws FiltroException;

  public IndicadorService getIndicadorService() {
    if (indicadorService != null) {
      return indicadorService;
    }
    indicadorService = new IndicadorServiceImpl();
    return indicadorService;
  }

  public String toJson() {
    StringBuilder builder = new StringBuilder();
    builder.append("{");
    builder.append("\"nombre\" : ");
    builder.append("\"" + nombre + "\",");
    builder.append("\"valor\" : ");
    builder.append("\"" + valor + "\"");
    builder.append("}");
    return builder.toString();
  }

}
