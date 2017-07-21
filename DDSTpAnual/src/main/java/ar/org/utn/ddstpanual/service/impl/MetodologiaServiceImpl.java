package ar.org.utn.ddstpanual.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import ar.org.utn.ddstpanual.exception.ServiceException;
import ar.org.utn.ddstpanual.model.Empresa;
import ar.org.utn.ddstpanual.model.metodologia.Metodologia;
import ar.org.utn.ddstpanual.model.metodologia.MetodologiaResultado;
import ar.org.utn.ddstpanual.service.CondicionService;
import ar.org.utn.ddstpanual.service.MetodologiaService;

public class MetodologiaServiceImpl implements MetodologiaService {

  private CondicionService condicionService;

  @Override
  public List<MetodologiaResultado> ejecutarMetodologia(List<Empresa> empresas, Metodologia metodologia) throws ServiceException {
    List<MetodologiaResultado> resultado = new ArrayList<>();
    empresas = empresas.stream().filter(e -> {
      try {
        return getCondicionService().cumpleCondiciones(metodologia, e);
      } catch (ServiceException e1) {
        return false;
      }
    }).collect(Collectors.toList());
    return resultado;
  }

  public CondicionService getCondicionService() {
    if (condicionService != null) {
      return condicionService;
    }
    condicionService = new CondicionServiceImpl();
    return condicionService;
  }

}
