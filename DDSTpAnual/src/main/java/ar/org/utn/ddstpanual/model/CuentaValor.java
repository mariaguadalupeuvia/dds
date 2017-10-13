package ar.org.utn.ddstpanual.model;

import lombok.Data;
import lombok.NoArgsConstructor;

public @Data @NoArgsConstructor class CuentaValor {
  private String nombreEmpresa;
  private String nombreCuenta;
  private String fecha;
  private double valor;

  public CuentaValor(String nombreEmpresa, String nombreCuenta, String fecha, double valor) {
    this.nombreEmpresa = nombreEmpresa;
    this.nombreCuenta = nombreCuenta;
    this.fecha = fecha;
    this.valor = valor;
  }

}
