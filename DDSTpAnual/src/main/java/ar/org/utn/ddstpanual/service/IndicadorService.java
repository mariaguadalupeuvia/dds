package ar.org.utn.ddstpanual.service;

import java.util.List;

import ar.org.utn.ddstpanual.exception.ServiceException;
import ar.org.utn.ddstpanual.model.EmpresaExcel;
import ar.org.utn.ddstpanual.model.Indicador;

public interface IndicadorService {

  public void guardarIndicador(Indicador indicador) throws ServiceException;

  public List<Indicador> obtenerIndicadores() throws ServiceException;

  public List<String> obtenerNombresIndicadores() throws ServiceException;

  public void eliminarIndicador(Indicador indicador) throws ServiceException;

  public boolean validarFormula(String formula) throws ServiceException;
  
  public List<EmpresaExcel> ejecutarIndicador() throws ServiceException;

}
