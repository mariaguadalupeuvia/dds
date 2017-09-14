package ar.org.utn.ddstpanual.service;

import java.util.List;

import ar.org.utn.ddstpanual.exception.ServiceException;
import ar.org.utn.ddstpanual.model.Empresa;
import ar.org.utn.ddstpanual.model.Indicador;
import ar.org.utn.ddstpanual.model.Periodo;
import ar.org.utn.ddstpanual.model.metodologia.Condicion;
import ar.org.utn.ddstpanual.model.metodologia.Metodologia;
import ar.org.utn.ddstpanual.model.metodologia.Orden;

public interface MetodologiaService {

  public void guardarMetodologia(Metodologia metodologia) throws ServiceException;

  public Metodologia obtenerMetodologia(String nombre) throws ServiceException;

  public List<Metodologia> obtenerMetodologias() throws ServiceException;

  public List<Empresa> ejecutarMetodologia(List<Empresa> empresas, Metodologia metodologia, Periodo periodo) throws ServiceException;

  public List<Condicion> agregarCondicion(List<Condicion> condiciones, Condicion condicion) throws ServiceException;

  public List<Indicador> agregarIndicadorSeleccionado(List<Indicador> indicadores, Indicador indicador) throws ServiceException;

  public List<Orden> agregarOrden(List<Orden> ordenes, Orden orden) throws ServiceException;

}
