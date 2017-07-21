package ar.org.utn.ddstpanual.service;

import java.util.List;

import ar.org.utn.ddstpanual.exception.ServiceException;
import ar.org.utn.ddstpanual.model.Empresa;
import ar.org.utn.ddstpanual.model.metodologia.Metodologia;
import ar.org.utn.ddstpanual.model.metodologia.MetodologiaResultado;

public interface MetodologiaService {

  public List<MetodologiaResultado> ejecutarMetodologia(List<Empresa> empresas, Metodologia metodologia) throws ServiceException;

}
