package ar.org.utn.ddstpanual.service;

import java.util.List;

import ar.org.utn.ddstpanual.exception.ArbolException;
import ar.org.utn.ddstpanual.exception.ArchivoException;
import ar.org.utn.ddstpanual.exception.FormulaInfinitaException;
import ar.org.utn.ddstpanual.exception.NoEncuentraFormulaException;
import ar.org.utn.ddstpanual.exception.NoSeEncuentraCuentaException;
import ar.org.utn.ddstpanual.exception.ServiceException;
import ar.org.utn.ddstpanual.model.Empresa;
import ar.org.utn.ddstpanual.model.EmpresaExcel;
import ar.org.utn.ddstpanual.model.Indicador;
import ar.org.utn.ddstpanual.model.Periodo;
import ar.org.utn.ddstpanual.model.FormulaIndicador;

public interface IndicadorService {

  public void guardarIndicador(Indicador indicador) throws ServiceException;

  public List<Indicador> obtenerIndicadores() throws ServiceException;

  public void eliminarIndicador(Indicador indicador) throws ServiceException;

  public boolean validarFormula(String formula) throws ServiceException;

  public List<FormulaIndicador> ejecutarIndicador(String nombre, Periodo periodo, Empresa empresa) throws ServiceException, ArchivoException, ArbolException, NoEncuentraFormulaException, NoSeEncuentraCuentaException, FormulaInfinitaException;

  public String obtenerFormula(String nombre) throws ServiceException;



}
