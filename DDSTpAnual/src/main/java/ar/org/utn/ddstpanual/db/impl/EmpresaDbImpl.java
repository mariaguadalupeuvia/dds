package ar.org.utn.ddstpanual.db.impl;

import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;
import org.uqbarproject.jpa.java8.extras.transaction.TransactionalOps;

import java.util.List;

import ar.org.utn.ddstpanual.db.EmpresaDb;
import ar.org.utn.ddstpanual.exception.ArchivoException;
import ar.org.utn.ddstpanual.model.Empresa;
import ar.org.utn.ddstpanual.model.Periodo;


public class EmpresaDbImpl implements EmpresaDb, WithGlobalEntityManager, TransactionalOps {

  @Override
  public void guardarEmpresa(Empresa empresa) throws ArchivoException {
    withTransaction(() -> {
      entityManager().merge(empresa);//persist(empresa);
    });
  }

  @Override
  public boolean exists(Empresa empresa) throws ArchivoException {
    return entityManager().contains(empresa);
  }

  @Override
  public List<Empresa> obtenerEmpresas() throws ArchivoException {
    return entityManager().createQuery("from Empresa", Empresa.class).getResultList();
  }

  @Override
  public List<Periodo> obtenerPeriodos() throws ArchivoException {
    return entityManager().createQuery("from Periodo", Periodo.class).getResultList();
  }

  @Override
  public Empresa obtenerEmpresa(String nombre) throws ArchivoException {
    return entityManager().createQuery("from Empresa nombre = :nombre", Empresa.class).setParameter("nombre", nombre).getSingleResult();
  }

}
