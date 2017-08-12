package ar.org.utn.ddstpanual.model.metodologia;

import java.util.List;

import ar.org.utn.ddstpanual.exception.FiltroException;
import ar.org.utn.ddstpanual.exception.ServiceException;
import ar.org.utn.ddstpanual.model.Empresa;
import ar.org.utn.ddstpanual.model.FormulaIndicador;
import ar.org.utn.ddstpanual.model.Indicador;
import ar.org.utn.ddstpanual.model.Periodo;

public class FiltroMenorIgual extends Filtro {

  private static final long serialVersionUID = 1L;

  public FiltroMenorIgual() {
    this.setNombre("Menor o igual");
  }

  public FiltroMenorIgual(Integer valor) {
    this.setNombre("Menor o igual");
    super.setValor(valor);
  }

  @Override
  public boolean cumpleCondicion(Indicador indicador, Empresa empresa, Periodo periodo) throws FiltroException {
    try {
      List<FormulaIndicador> formulaIndicador =
          getIndicadorService().ejecutarIndicador(indicador.getFormula(), periodo.getFecha(), empresa);
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
