package ar.org.utn.ddstpanual.service;

import java.util.List;

import ar.org.utn.ddstpanual.exception.ServiceException;
import ar.org.utn.ddstpanual.model.Indicador;

public interface IndicadorService {

  public void guardarIndicador() throws ServiceException;
  
  public List<Indicador> obtenerIndicadores() throws ServiceException;
  
  public void eliminarIndicador() throws ServiceException;
  
}
