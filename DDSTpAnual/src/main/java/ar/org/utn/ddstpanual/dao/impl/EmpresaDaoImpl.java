package ar.org.utn.ddstpanual.dao.impl;

import java.util.Map;
import java.util.Map.Entry;

import ar.org.utn.ddstpanual.dao.EmpresaDao;
import ar.org.utn.ddstpanual.exception.DaoException;
import ar.org.utn.ddstpanual.model.Empresa;

public class EmpresaDaoImpl implements EmpresaDao {

  public void saveEmpresas(Map<String, Empresa> empresas) throws DaoException {

    for (Entry<String, Empresa> entry : empresas.entrySet()) {
      System.out.println(entry.getKey() + "/" + entry.getValue().toString());
    }
  }
}
