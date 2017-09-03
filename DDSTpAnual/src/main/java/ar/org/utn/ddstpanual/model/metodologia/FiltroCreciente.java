package ar.org.utn.ddstpanual.model.metodologia;

import java.time.Year;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import ar.org.utn.ddstpanual.exception.FiltroException;
import ar.org.utn.ddstpanual.exception.ServiceException;
import ar.org.utn.ddstpanual.model.Empresa;
import ar.org.utn.ddstpanual.model.FormulaIndicador;
import ar.org.utn.ddstpanual.model.Periodo;

@Entity
@DiscriminatorValue("Estrictamente creciente")
public class FiltroCreciente extends Filtro {

  private static final long serialVersionUID = 1L;

  public FiltroCreciente() {
    this.setNombre("Estrictamente creciente");
  }

  @Override
  public boolean cumpleCondicion(Condicion condicion, Empresa empresa, Periodo periodo) throws FiltroException {
    try {
      Year fechaPeriodoDesde = Year.now().minusYears(condicion.getValor());
      FormulaIndicador valorIndicadorDesde =
          getIndicadorService().ejecutarIndicador(condicion.getIndicador().getFormula(), fechaPeriodoDesde.toString(), empresa).get(0);
      fechaPeriodoDesde.plusYears(1);
      while (fechaPeriodoDesde.isBefore(Year.now()) || fechaPeriodoDesde.equals(Year.now())) {
        FormulaIndicador valorIndicador =
            getIndicadorService().ejecutarIndicador(condicion.getIndicador().getFormula(), fechaPeriodoDesde.toString(), empresa).get(0);
        if (valorIndicador.getValor() < valorIndicadorDesde.getValor()) {
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
    return "Estrictamente creciente";
  }

}
