package ar.org.utn.ddstpanual.model;

import org.uqbar.commons.utils.Observable;

@Observable
public class FormulaIndicador {

	private String nombre;
	private String fecha;
	private double valor;
	
	public FormulaIndicador(){}
	
	public FormulaIndicador(String nombre, String fecha, double valor){
		this.nombre = nombre;
		this.fecha = fecha;
		this.valor = valor;
	}
	
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getFecha() {
		return fecha;
	}
	public void setFecha(String fecha) {
		this.fecha = fecha;
	}
	public double getValor() {
		return valor;
	}
	public void setValor(double valor) {
		this.valor = valor;
	}	
}
