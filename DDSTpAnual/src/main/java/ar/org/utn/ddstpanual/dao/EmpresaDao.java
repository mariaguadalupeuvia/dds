package ar.org.utn.ddstpanual.dao;

import java.util.Map;

import ar.org.utn.ddstpanual.exception.DaoException;
import ar.org.utn.ddstpanual.model.Empresa;

public interface EmpresaDao {

  public void saveEmpresas(Map<String, Empresa> empresas) throws DaoException;

}
