package ar.org.utn.ddstpanual.service;

import java.io.FileInputStream;
import java.util.List;

import ar.org.utn.ddstpanual.exception.ServiceException;
import ar.org.utn.ddstpanual.model.Empresa;

public interface EmpresaService {

  void subirExcel(FileInputStream file) throws ServiceException;

  public List<Empresa> obtenerEmpresas() throws ServiceException;

}
