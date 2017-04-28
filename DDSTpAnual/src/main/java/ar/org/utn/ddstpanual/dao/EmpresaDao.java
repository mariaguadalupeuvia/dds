package ar.org.utn.ddstpanual.dao;

import java.util.List;
import java.util.Map;

import ar.org.utn.ddstpanual.dto.EmpresaDto;
import ar.org.utn.ddstpanual.exception.DaoException;

public interface EmpresaDao {

  public void saveEmpresas(List<EmpresaDto> empresas) throws DaoException;

  public void saveEmpresas(Map<String, EmpresaDto> empresas) throws DaoException;

}
