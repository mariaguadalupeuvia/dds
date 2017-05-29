package ar.org.utn.ddstpanual.archivo;

import ar.org.utn.ddstpanual.exception.ServiceException;
import ar.org.utn.ddstpanual.model.EmpresaExcel;

public interface EmpresaArchivo {

  public void guardarEmpresa(EmpresaExcel empresa) throws ServiceException;

  public boolean exists(EmpresaExcel empresa) throws ServiceException;

}
