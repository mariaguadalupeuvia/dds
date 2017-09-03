package ar.org.utn.ddstpanual.model.metodologia;

import java.util.List;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import ar.org.utn.ddstpanual.exception.FiltroException;
import ar.org.utn.ddstpanual.exception.ServiceException;
import ar.org.utn.ddstpanual.model.Empresa;
import ar.org.utn.ddstpanual.model.FormulaIndicador;
import ar.org.utn.ddstpanual.model.Periodo;

@Entity
@DiscriminatorValue("<")
public class FiltroMenor extends Filtro {

  private static final long serialVersionUID = 1L;

  public FiltroMenor() {
    this.setNombre("Menor");
  }

  @Override
  public boolean cumpleCondicion(Condicion condicion, Empresa empresa, Periodo periodo) throws FiltroException {
    try {
      List<FormulaIndicador> formulaIndicador =
          getIndicadorService().ejecutarIndicador(condicion.getIndicador().getFormula(), periodo.getFecha(), empresa);
      return formulaIndicador.get(0).getValor() < condicion.getValor();
    } catch (ServiceException e) {
      throw new FiltroException(e.getMessage());
    }
  }

  @Override
  public String toString() {
    return "Menor";
  }

}
