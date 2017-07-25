package ar.org.utn.ddstpanual.model.metodologia;

import java.time.Year;

import ar.org.utn.ddstpanual.exception.FiltroException;
import ar.org.utn.ddstpanual.exception.ServiceException;
import ar.org.utn.ddstpanual.model.Empresa;
import ar.org.utn.ddstpanual.model.FormulaIndicador;
import ar.org.utn.ddstpanual.model.Indicador;

public class FiltroDecreciente extends Filtro {

  /**
   * 
   */
  private static final long serialVersionUID = 1L;

  public FiltroDecreciente() {
    this.setNombre("Estrictamente decreciente");
  }

  @Override
  public boolean cumpleCondicion(Indicador indicador, Empresa empresa) throws FiltroException {
    try {
      Year fechaPeriodoDesde = Year.now().minusYears(this.getValor());
      FormulaIndicador valorIndicadorDesde =
          getIndicadorService().ejecutarIndicador(indicador.getFormula(), fechaPeriodoDesde.toString(), empresa).get(0);
      fechaPeriodoDesde.plusYears(1);
      while (fechaPeriodoDesde.isBefore(Year.now()) || fechaPeriodoDesde.equals(Year.now())) {
        FormulaIndicador valorIndicador =
            getIndicadorService().ejecutarIndicador(indicador.getFormula(), fechaPeriodoDesde.toString(), empresa).get(0);
        if (valorIndicador.getValor() > valorIndicadorDesde.getValor()) {
          return false;
        }
        fechaPeriodoDesde.plusYears(1);
      }
      return true;
    } catch (ServiceException e) {
      throw new FiltroException(e.getMessage());
    }
  }

  @Override
  public String toString() {
    return "Estrictamente decreciente";
  }

}
