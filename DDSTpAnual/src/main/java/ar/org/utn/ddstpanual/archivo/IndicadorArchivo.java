package ar.org.utn.ddstpanual.archivo;

import java.util.List;

import ar.org.utn.ddstpanual.exception.ServiceException;
import ar.org.utn.ddstpanual.model.Indicador;

public interface IndicadorArchivo {

  public void guardarIndicador(Indicador indicador) throws ServiceException;

  public List<Indicador> obtenerIndicadores() throws ServiceException;

}
