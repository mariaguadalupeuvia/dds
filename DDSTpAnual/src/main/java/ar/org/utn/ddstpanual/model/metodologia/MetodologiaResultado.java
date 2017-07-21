package ar.org.utn.ddstpanual.model.metodologia;

import org.uqbar.commons.utils.Observable;

@Observable
public class MetodologiaResultado {

  private String nombreEmpresa;

  public String getNombreEmpresa() {
    return nombreEmpresa;
  }

  public void setNombreEmpresa(String nombreEmpresa) {
    this.nombreEmpresa = nombreEmpresa;
  }

}
