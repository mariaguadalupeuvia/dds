package ar.org.utn.ddstpanual.service;

import java.util.List;

import ar.org.utn.ddstpanual.exception.ServiceException;
import ar.org.utn.ddstpanual.model.Empresa;
import ar.org.utn.ddstpanual.model.metodologia.Condicion;
import ar.org.utn.ddstpanual.model.metodologia.Metodologia;
import ar.org.utn.ddstpanual.model.metodologia.MetodologiaResultado;

public interface MetodologiaService {

  public void guardarMetodologia(Metodologia metodologia) throws ServiceException;

  public Metodologia obtenerMetodologia(String nombre) throws ServiceException;

  public List<Metodologia> obtenerMetodologias() throws ServiceException;

  public List<MetodologiaResultado> ejecutarMetodologia(List<Empresa> empresas, Metodologia metodologia) throws ServiceException;

  public List<Condicion> agregarCondicion(List<Condicion> condiciones, Condicion condicion) throws ServiceException;

}
