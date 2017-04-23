package ar.org.utn.ddstpanual.model;

import java.util.List;

public class Empresa {

  List<Cuenta> cuentas;
  String nombre;
  
  public List<Cuenta> getCuentas() {
    return cuentas;
  }

  public void setCuentas(List<Cuenta> cuentas) {
    this.cuentas = cuentas;
  }
  
  public String getNombre(){
	  return nombre;
  }
  
  public void setNombre(String nombre){
	  this.nombre = nombre;
  }

}
