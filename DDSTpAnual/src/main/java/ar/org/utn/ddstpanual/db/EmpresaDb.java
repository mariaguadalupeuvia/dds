package ar.org.utn.ddstpanual.db;

import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;
import org.uqbarproject.jpa.java8.extras.transaction.TransactionalOps;

import java.util.List;

import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

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
    CriteriaBuilder cb = entityManager().getCriteriaBuilder();
    CriteriaQuery<Empresa> cqry = cb.createQuery(Empresa.class);
    Root<Empresa> root = cqry.from(Empresa.class);
    cqry.select(root);
    TypedQuery<Empresa> qry = entityManager().createQuery(cqry);
    return qry.getResultList();
  }

  public List<Periodo> obtenerPeriodos() throws DbException {
    try {
      CriteriaBuilder cb = entityManager().getCriteriaBuilder();
      CriteriaQuery<Periodo> cqry = cb.createQuery(Periodo.class);
      Root<Periodo> root = cqry.from(Periodo.class);
      cqry.multiselect(root.get("fecha"));
      cqry.groupBy(root.get("fecha"));
      TypedQuery<Periodo> qry = entityManager().createQuery(cqry);
      return qry.getResultList();
    } catch (Exception ex) {
      log.error(ex.getMessage());
      throw new DbException(ex.getMessage());
    }
  }

  public Empresa obtenerEmpresa(String nombre) throws DbException {
    try {
      CriteriaBuilder cb = entityManager().getCriteriaBuilder();
      CriteriaQuery<Empresa> cqry = cb.createQuery(Empresa.class);
      Root<Empresa> root = cqry.from(Empresa.class);
      cqry.select(root);
      Predicate pEqualsNombre = cb.equal(root.get("nombre"), nombre);
      cqry.where(pEqualsNombre);
      TypedQuery<Empresa> qry = entityManager().createQuery(cqry);
      return qry.getSingleResult();
    } catch (NoResultException ex) {
      return null;
    } catch (Exception ex) {
      log.error(ex.getMessage());
      throw new DbException(ex.getMessage());
    }
  }

  public Cuenta obtenerCuenta(Integer idEmpresa, String nombreCuenta) throws DbException {
    try {
      CriteriaBuilder cb = entityManager().getCriteriaBuilder();
      CriteriaQuery<Cuenta> cqry = cb.createQuery(Cuenta.class);
      Root<Cuenta> root = cqry.from(Cuenta.class);
      cqry.select(root);
      Predicate pEqualsEmpresa = cb.equal(root.get("empresa_id"), idEmpresa);
      Predicate pEqualsNombre = cb.equal(root.get("nombre"), nombreCuenta);
      Predicate pAnd = cb.and(pEqualsEmpresa, pEqualsNombre);
      cqry.where(pAnd);
      TypedQuery<Cuenta> qry = entityManager().createQuery(cqry);
      return qry.getSingleResult();
    } catch (NoResultException ex) {
      return null;
    } catch (Exception ex) {
      log.error(ex.getMessage());
      throw new DbException(ex.getMessage());
    }
  }

  public Periodo obtenerPeriodo(Integer idCuenta, String fecha) throws DbException {
    try {
      CriteriaBuilder cb = entityManager().getCriteriaBuilder();
      CriteriaQuery<Periodo> cqry = cb.createQuery(Periodo.class);
      Root<Periodo> root = cqry.from(Periodo.class);
      cqry.select(root);
      Predicate pEqualsCuenta = cb.equal(root.get("idCuenta"), idCuenta);
      Predicate pEqualsFecha = cb.equal(root.get("fecha"), fecha);
      Predicate pAnd = cb.and(pEqualsFecha, pEqualsCuenta);
      cqry.where(pAnd);
      TypedQuery<Periodo> qry = entityManager().createQuery(cqry);
      return qry.getSingleResult();
    } catch (NoResultException ex) {
      return null;
    } catch (Exception ex) {
      log.error(ex.getMessage());
      throw new DbException(ex.getMessage());
    }
  }

}
