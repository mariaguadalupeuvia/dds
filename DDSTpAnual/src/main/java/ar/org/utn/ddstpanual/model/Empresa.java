package ar.org.utn.ddstpanual.model;

import org.uqbar.commons.utils.Observable;

import java.util.ArrayList;
import java.util.List;

@Observable
public class Empresa {

  List<Cuenta> cuentas;
  String nombre;
  
  public Empresa(){}
  
  public Empresa(List<Cuenta> cuentas, String nombre){
	  this.cuentas = cuentas;
	  this.nombre = nombre;
  }

  public double obtenerValor(String nombreCuenta, String periodo){
	  double valor = 0;
	  for(Cuenta cue : cuentas){
		  if(cue.getNombre().equals(nombreCuenta))
			  valor = cue.obtenerValor(periodo);
	  }
	  return valor;
	  
  }
  
  public List<Periodo> obtenerPeriodos(){
	  List<Periodo> periodos = new ArrayList<Periodo>();
	  for(Cuenta c : cuentas){
		  for(Periodo per : c.getPeriodos()){
			  if(!periodos.stream().anyMatch(p -> p.getFecha().equals(per.getFecha()))){
				  periodos.add(per);
			  }
		  }
	  }
	  return periodos;
  }
  
  public List<Cuenta> getCuentas() {
    return cuentas;
  }

  public void setCuentas(List<Cuenta> cuentas) {
    this.cuentas = cuentas;
  }
  
  public String getNombre() {
    return nombre;
  }

  public void setNombre(String nombre) {
    this.nombre = nombre;
  }

  @Override
  public String toString() {
    StringBuilder builder = new StringBuilder();
    builder.append("{\nnombre : " + nombre + ",\n");
    builder.append("cuentas : " + cuentas.toString() + "\n}");
    return builder.toString();
  }

}
