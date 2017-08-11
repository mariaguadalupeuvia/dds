package ar.org.utn.ddstpanual.model;

import org.uqbar.commons.utils.Observable;

@Observable
public class EmpresaExcel {
  private String nombreEmpresa;
  private String nombreCuenta;
  private String fecha;
  private double valor;

  public EmpresaExcel(String nombreEmpresa, String nombreCuenta, String fecha, double valor){
    this.nombreEmpresa = nombreEmpresa;
    this.nombreCuenta = nombreCuenta;
    this.fecha = fecha;
    this.valor = valor;
  }
  
  public EmpresaExcel(){}
  
  public String getNombreEmpresa() {
    return nombreEmpresa;
  }

  public void setNombreEmpresa(final String nombreEmpresa) {
    this.nombreEmpresa = nombreEmpresa;
  }

  public String getNombreCuenta() {
    return nombreCuenta;
  }

  public void setNombreCuenta(final String nombreCuenta) {
    this.nombreCuenta = nombreCuenta;
  }

  public String getFecha() {
    return fecha;
  }

  public void setFecha(final String fecha) {
    this.fecha = fecha;
  }

  public double getValor() {
    return valor;
  }

  public void setValor(final double valor) {
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
