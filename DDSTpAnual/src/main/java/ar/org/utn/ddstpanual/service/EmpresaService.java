package ar.org.utn.ddstpanual.service;

import java.io.FileInputStream;

import ar.org.utn.ddstpanual.exception.ServiceException;

public interface EmpresaService {

  void subirExcel(FileInputStream file) throws ServiceException;
  
}
