package ar.org.utn.ddstpanual.model;

import org.uqbar.commons.utils.Observable;

@Observable
public class FormulaIndicador {

	private String nombre;
	private String fecha;
	private float valor;
	
	public FormulaIndicador(){}
	
	public FormulaIndicador(String nombre, String fecha, float valor){
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
	public float getValor() {
		return valor;
	}
	public void setValor(float valor) {
		this.valor = valor;
	}	
}
