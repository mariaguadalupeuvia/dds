package ar.org.utn.ddstpanual.model.metodologia;

import org.uqbar.commons.utils.Observable;

import java.time.Year;
import java.util.List;

import ar.org.utn.ddstpanual.exception.FiltroException;
import ar.org.utn.ddstpanual.model.Empresa;
import ar.org.utn.ddstpanual.model.FormulaIndicador;
import ar.org.utn.ddstpanual.model.Periodo;
import ar.org.utn.ddstpanual.service.IndicadorService;
import ar.org.utn.ddstpanual.service.impl.IndicadorServiceImpl;

@Observable
public enum Filtro {

	CRECIENTE("Estrictamente creciente"), DECRECIENTE("Estrictamente decreciente"), IGUAL("Igual"), MAYOR(
			"Mayor"), MENOR("Menor"), MENORIGUAL("Menor Igual"), MAYORIGUAL("Mayor igual");

	private String nombre;
	IndicadorService indicadorService = new IndicadorServiceImpl();

	Filtro(String nombre) {
		this.setNombre(nombre);
	}

	public boolean cumpleCondicion(Condicion condicion, Empresa empresa, Periodo periodo) throws FiltroException {

		List<FormulaIndicador> formulaIndicador = indicadorService
				.ejecutarIndicador(condicion.getIndicador().getFormula(), periodo.getFecha(), empresa);

		switch (this) {
		case CRECIENTE:
			return comparadorEstricto(condicion, empresa, periodo, true);
		case DECRECIENTE:
			return comparadorEstricto(condicion, empresa, periodo, false);
		case IGUAL:
			return formulaIndicador.get(0).getValor() == condicion.getValor();
		case MAYOR:
			return formulaIndicador.get(0).getValor() > condicion.getValor();
		case MENOR:
			return formulaIndicador.get(0).getValor() < condicion.getValor();
		case MENORIGUAL:
			return formulaIndicador.get(0).getValor() <= condicion.getValor();
		case MAYORIGUAL:
			return formulaIndicador.get(0).getValor() >= condicion.getValor();
		default:
			throw new AssertionError("Filtro desconocido " + this);
		}
	}

	public boolean comparadorEstricto(Condicion condicion, Empresa empresa, Periodo periodo, Boolean esCreciente) {

		Year fechaPeriodoDesde = Year.now().minusYears(condicion.getValor());
		FormulaIndicador valorIndicadorDesde = indicadorService
				.ejecutarIndicador(condicion.getIndicador().getFormula(), fechaPeriodoDesde.toString(), empresa).get(0);
		fechaPeriodoDesde.plusYears(1);
		while (fechaPeriodoDesde.isBefore(Year.now()) || fechaPeriodoDesde.equals(Year.now())) {
			FormulaIndicador valorIndicador = indicadorService
					.ejecutarIndicador(condicion.getIndicador().getFormula(), fechaPeriodoDesde.toString(), empresa)
					.get(0);
			if (esCreciente) {
				if (valorIndicador.getValor() < valorIndicadorDesde.getValor()) {
					return false;
				}
			} else {
				if (valorIndicador.getValor() > valorIndicadorDesde.getValor()) {
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

