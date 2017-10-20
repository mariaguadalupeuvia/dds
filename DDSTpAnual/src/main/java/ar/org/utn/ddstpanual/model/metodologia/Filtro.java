package ar.org.utn.ddstpanual.model.metodologia;

import org.uqbar.commons.utils.Observable;

import java.time.Year;

import ar.org.utn.ddstpanual.exception.ArbolException;
import ar.org.utn.ddstpanual.exception.FiltroException;
import ar.org.utn.ddstpanual.model.Empresa;
import ar.org.utn.ddstpanual.model.Indicador;
import ar.org.utn.ddstpanual.model.Periodo;

@Observable
public enum Filtro {

	CRECIENTE("Estrictamente creciente"), DECRECIENTE("Estrictamente decreciente"), IGUAL("Igual"), MAYOR(
			"Mayor"), MENOR("Menor"), MENORIGUAL("Menor Igual"), MAYORIGUAL("Mayor igual");

	private String nombre;

	Filtro(String nombre) {
		this.setNombre(nombre);
	}

	public boolean cumpleCondicion(Condicion condicion, Empresa empresa, Periodo periodo) throws FiltroException, ArbolException {

		Double valorIndicador = condicion.getIndicador().ejecutarIndicador(periodo.getFecha(), empresa);

		switch (this) {
		case CRECIENTE:
			return comparadorEstricto(condicion, empresa, periodo, true);
		case DECRECIENTE:
			return comparadorEstricto(condicion, empresa, periodo, false);
		case IGUAL:
			return valorIndicador == condicion.getValor();
		case MAYOR:
			return valorIndicador > condicion.getValor();
		case MENOR:
			return valorIndicador < condicion.getValor();
		case MENORIGUAL:
			return valorIndicador <= condicion.getValor();
		case MAYORIGUAL:
			return valorIndicador >= condicion.getValor();
		default:
			throw new AssertionError("Filtro desconocido " + this);
		}
	}

	public boolean comparadorEstricto(Condicion condicion, Empresa empresa, Periodo periodo, Boolean esCreciente) throws ArbolException {

		Year fechaPeriodoDesde = Year.now().minusYears(Math.round(condicion.getValor()));
		Indicador indicador = condicion.getIndicador();

		Double valorIndicadorDesde = indicador.ejecutarIndicador(fechaPeriodoDesde.toString(), empresa);

		fechaPeriodoDesde.plusYears(1);
		while (fechaPeriodoDesde.isBefore(Year.now()) || fechaPeriodoDesde.equals(Year.now())) {
			Double valorIndicador = indicador.ejecutarIndicador(fechaPeriodoDesde.toString(), empresa);

			if (esCreciente) {
				if (valorIndicador < valorIndicadorDesde) {
					return false;
				}
			} else {
				if (valorIndicador > valorIndicadorDesde) {
					return false;
				}
			}

			fechaPeriodoDesde.plusYears(1);
		}
		return true;

	}

	@Override
	public String toString() {
		return nombre;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String toJson() {
		StringBuilder builder = new StringBuilder();
		builder.append("{");
		builder.append("\"nombre\" : ");
		builder.append("\"" + nombre + "\"");
		builder.append("}");
		return builder.toString();
	}
}
