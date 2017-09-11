package ar.org.utn.ddstpanual.service;

import ar.org.utn.ddstpanual.model.Empresa;
import ar.org.utn.ddstpanual.model.Periodo;
import ar.org.utn.ddstpanual.model.metodologia.Metodologia;

public interface CondicionService {

  public boolean cumpleCondiciones(Metodologia metodologia, Empresa empresa, Periodo periodo);

}
