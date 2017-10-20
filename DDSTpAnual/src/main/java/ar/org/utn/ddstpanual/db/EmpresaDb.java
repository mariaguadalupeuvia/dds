package ar.org.utn.ddstpanual.db;

import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;
import org.uqbarproject.jpa.java8.extras.transaction.TransactionalOps;

import java.util.List;

import javax.persistence.NoResultException;

import ar.org.utn.ddstpanual.exception.DbException;
import ar.org.utn.ddstpanual.model.Cuenta;
import ar.org.utn.ddstpanual.model.Empresa;
import ar.org.utn.ddstpanual.model.Periodo;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class EmpresaDb implements WithGlobalEntityManager, TransactionalOps {

  public void guardarEmpresa(Empresa empresa) throws DbException {
    withTransaction(() -> {
      entityManager().persist(empresa);
    });
  }

  public boolean exists(Empresa empresa) throws DbException {
    return entityManager().contains(empresa);
  }

  public List<Empresa> obtenerEmpresas() throws DbException {
    return entityManager().createQuery("from Empresa", Empresa.class).getResultList();
  }

  public List<Periodo> obtenerPeriodos() throws DbException {
    return entityManager().createQuery("from Periodo", Periodo.class).getResultList();
  }

  public Empresa obtenerEmpresa(String nombre) throws DbException {
    try {
      return entityManager().createQuery("from " + Empresa.class.getName() + " where nombre = :nombre", Empresa.class)
          .setParameter("nombre", nombre).getSingleResult();
    } catch (NoResultException ex) {
      return null;
    } catch (Exception ex) {
      log.error(ex.getMessage());
      throw new DbException(ex.getMessage());
    }
  }

  public Cuenta obtenerCuenta(Integer idEmpresa, String nombreCuenta) throws DbException {
    try {
      return entityManager().createQuery("from Cuenta where nombre = :nombre and empresa_id = :empresa_id", Cuenta.class)
          .setParameter("nombre", nombreCuenta).setParameter("empresa_id", idEmpresa).getSingleResult();
    } catch (NoResultException ex) {
      return null;
    } catch (Exception ex) {
      log.error(ex.getMessage());
      throw new DbException(ex.getMessage());
    }
  }

  public Periodo obtenerPeriodo(Integer idCuenta, String fecha) throws DbException {
    try {
      return entityManager().createQuery("from Periodo where fecha = :fecha and cuenta_id = :cuenta_id", Periodo.class)
          .setParameter("fecha", fecha).setParameter("cuenta_id", idCuenta).getSingleResult();
    } catch (NoResultException ex) {
      return null;
    } catch (Exception ex) {
      log.error(ex.getMessage());
      throw new DbException(ex.getMessage());
    }
  }

}
