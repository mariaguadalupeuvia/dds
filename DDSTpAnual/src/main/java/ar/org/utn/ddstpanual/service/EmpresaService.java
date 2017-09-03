package ar.org.utn.ddstpanual.service;

import java.util.List;

import ar.org.utn.ddstpanual.exception.ServiceException;
import ar.org.utn.ddstpanual.model.Cuenta;
import ar.org.utn.ddstpanual.model.Empresa;
import ar.org.utn.ddstpanual.model.EmpresaExcel;
import ar.org.utn.ddstpanual.model.Periodo;

public interface EmpresaService {

  void subirExcel(String rutaArchivo) throws ServiceException;

  public List<Empresa> obtenerEmpresas() throws ServiceException;

  public List<EmpresaExcel> buscar(Empresa empresa, Cuenta cuenta, Periodo periodo) throws ServiceException;

  public List<Periodo> obtenerPeriodos() throws ServiceException;

  public Empresa obtenerEmpresa(String nombre) throws ServiceException;

}
