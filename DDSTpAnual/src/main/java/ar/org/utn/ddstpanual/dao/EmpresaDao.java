package ar.org.utn.ddstpanual.dao;

import java.util.List;

import ar.org.utn.ddstpanual.dto.EmpresaDto;
import ar.org.utn.ddstpanual.exception.DaoException;

public interface EmpresaDao {

	public void saveEmpresas(List<EmpresaDto> empresas) throws DaoException;

}
