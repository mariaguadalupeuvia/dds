package ar.org.utn.ddstpanual.model;

import org.uqbar.commons.utils.Observable;

@Observable
public class EmpresaExcel {
  private String nombreEmpresa;
  private String nombreCuenta;
  private String fecha;
  private float valor;

  public String getNombreEmpresa() {
    return nombreEmpresa;
  }

  public void setNombreEmpresa(String nombreEmpresa) {
    this.nombreEmpresa = nombreEmpresa;
  }

  public String getNombreCuenta() {
    return nombreCuenta;
  }

  public void setNombreCuenta(String nombreCuenta) {
    this.nombreCuenta = nombreCuenta;
  }

  public String getFecha() {
    return fecha;
  }

  public void setFecha(String fecha) {
    this.fecha = fecha;
  }

  public float getValor() {
    return valor;
  }

  public void setValor(float valor) {
    this.valor = valor;
  }

  @Override
  public String toString() {
    StringBuilder builder = new StringBuilder();
    builder.append("Empresa: " + nombreEmpresa + "\n");
    builder.append("Cuenta: " + nombreCuenta + "\n");
    builder.append("Fecha: " + fecha + "\n");
    builder.append("Valor: " + valor + "\n");
    return builder.toString();
  }

}
