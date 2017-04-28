package ar.org.utn.ddstpanual.dao.impl;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import ar.org.utn.ddstpanual.dao.EmpresaDao;
import ar.org.utn.ddstpanual.dto.EmpresaDto;
import ar.org.utn.ddstpanual.exception.DaoException;

public class EmpresaDaoImpl implements EmpresaDao {

  public void saveEmpresas(List<EmpresaDto> empresas) throws DaoException {
    empresas.stream().forEach(e -> e.toString());
  }

  public void saveEmpresas(Map<String, EmpresaDto> empresas) throws DaoException {
    for (Entry<String, EmpresaDto> entry : empresas.entrySet()) {
      System.out.println(entry.getValue().toString());
    }
  }
}
