package ar.org.utn.ddstpanual.service.impl;

import ar.org.utn.ddstpanual.model.Empresa;
import ar.org.utn.ddstpanual.model.Periodo;
import ar.org.utn.ddstpanual.model.metodologia.Condicion;
import ar.org.utn.ddstpanual.model.metodologia.Metodologia;
import ar.org.utn.ddstpanual.service.CondicionService;

public class CondicionServiceImpl implements CondicionService {

  @Override
  public boolean cumpleCondiciones(Metodologia metodologia, Empresa empresa, Periodo periodo) {
 
      for (Condicion condicion : metodologia.getCondiciones()) {
			if (!condicion.cumpleCondicion(empresa, periodo)) {
				return false;
			}

      }
      return true;
  }

}
