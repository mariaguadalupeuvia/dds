package ar.org.utn.ddstpanual.model;

import java.util.List;

public class Cuenta {

	String nombre;
	List<Periodo> periodos;

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public List<Periodo> getPeriodos() {
		return periodos;
	}

	public void setPeriodos(List<Periodo> periodos) {
		this.periodos = periodos;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("{\n nombre : " + nombre + ", \n");
		builder.append("valores : " + periodos.toString() + "\n}");
		return builder.toString();
	}

}
