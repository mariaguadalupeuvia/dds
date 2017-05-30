package ar.org.utn.ddstpanual.archivo;

import java.util.Map;

import ar.org.utn.ddstpanual.exception.ServiceException;
import ar.org.utn.ddstpanual.model.Empresa;
import ar.org.utn.ddstpanual.model.EmpresaExcel;

public interface EmpresaArchivo {

  public void guardarEmpresa(EmpresaExcel empresa) throws ServiceException;

  public boolean exists(EmpresaExcel empresa) throws ServiceException;

  public Map<String,Empresa> obtenerEmpresas() throws ServiceException;

}
