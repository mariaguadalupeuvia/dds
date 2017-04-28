package ar.org.utn.ddstpanual.dao.impl;

import java.util.List;

import ar.org.utn.ddstpanual.dao.EmpresaDao;
import ar.org.utn.ddstpanual.dto.EmpresaDto;
import ar.org.utn.ddstpanual.exception.DaoException;

public class EmpresaDaoImpl implements EmpresaDao {

	public void saveEmpresas(List<EmpresaDto> empresas) throws DaoException {
		empresas.stream().forEach(e -> e.toString());
	}
}
