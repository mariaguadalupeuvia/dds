package ar.org.utn.ddstpanual.model;

import org.uqbar.commons.utils.Observable;

import lombok.Data;
import lombok.NoArgsConstructor;

@Observable
public @Data @NoArgsConstructor class EmpresaExcel {
  private String nombreEmpresa;
  private String nombreCuenta;
  private String fecha;
  private double valor;

  public EmpresaExcel(String nombreEmpresa, String nombreCuenta, String fecha, double valor) {
    this.nombreEmpresa = nombreEmpresa;
    this.nombreCuenta = nombreCuenta;
    this.fecha = fecha;
    this.valor = valor;
  }

  @Override
  public String toString() {
    final StringBuilder builder = new StringBuilder();
    builder.append("Empresa: " + nombreEmpresa + "\n");
    builder.append("Cuenta: " + nombreCuenta + "\n");
    builder.append("Fecha: " + fecha + "\n");
    builder.append("Valor: " + valor + "\n");
    return builder.toString();
  }

}
