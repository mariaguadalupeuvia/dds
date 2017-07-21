package ar.org.utn.ddstpanual.service.impl;

import ar.org.utn.ddstpanual.exception.CondicionException;
import ar.org.utn.ddstpanual.exception.ServiceException;
import ar.org.utn.ddstpanual.model.Empresa;
import ar.org.utn.ddstpanual.model.metodologia.Condicion;
import ar.org.utn.ddstpanual.model.metodologia.Metodologia;
import ar.org.utn.ddstpanual.service.CondicionService;

public class CondicionServiceImpl implements CondicionService {

  @Override
  public boolean cumpleCondiciones(Metodologia metodologia, Empresa empresa) throws ServiceException {
    try {
      boolean cumpleCond = true;
      for (Condicion condicion : metodologia.getCondiciones()) {
        if (!condicion.cumpleCondicion(empresa)) {
          cumpleCond = false;
        }
      }
      return cumpleCond;
    } catch (CondicionException e) {
      throw new ServiceException(e.getMessage());
    }
  }

}
