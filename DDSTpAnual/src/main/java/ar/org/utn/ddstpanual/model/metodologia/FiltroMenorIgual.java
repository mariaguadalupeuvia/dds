package ar.org.utn.ddstpanual.model.metodologia;

import java.time.Year;
import java.util.List;

import ar.org.utn.ddstpanual.exception.FiltroException;
import ar.org.utn.ddstpanual.exception.ServiceException;
import ar.org.utn.ddstpanual.model.Empresa;
import ar.org.utn.ddstpanual.model.FormulaIndicador;
import ar.org.utn.ddstpanual.model.Indicador;

public class FiltroMenorIgual extends Filtro {

  public FiltroMenorIgual() {
    this.setNombre("Menor o igual");
  }

  @Override
  public boolean cumpleCondicion(Indicador indicador, Empresa empresa) throws FiltroException {
    try {
      List<FormulaIndicador> formulaIndicador =
          getIndicadorService().ejecutarIndicador(indicador.getFormula(), Year.now().toString(), empresa);
      return formulaIndicador.get(0).getValor() <= this.getValor();
    } catch (ServiceException e) {
      throw new FiltroException(e.getMessage());
    }
  }

  @Override
  public String toString() {
    return "Menor o igual";
  }

}
