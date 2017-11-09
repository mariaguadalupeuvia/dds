package ar.org.utn.ddstpanual.db;

import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;
import org.uqbarproject.jpa.java8.extras.transaction.TransactionalOps;

import java.util.List;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import ar.org.utn.ddstpanual.exception.DbException;
import ar.org.utn.ddstpanual.model.metodologia.Metodologia;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class MetodologiaDb implements WithGlobalEntityManager, TransactionalOps {

  public void guardarMetodologia(Metodologia metodologia) throws DbException {
    withTransaction(() -> {
      entityManager().persist(metodologia);
    });
  }

  public Metodologia obtenerMetodologia(String nombre) throws DbException {
    try {
      CriteriaBuilder cb = entityManager().getCriteriaBuilder();
      CriteriaQuery<Metodologia> cqry = cb.createQuery(Metodologia.class);
      Root<Metodologia> root = cqry.from(Metodologia.class);
      cqry.select(root);
      Predicate pEqualsNombre = cb.equal(root.get("nombre"), nombre);
      cqry.where(pEqualsNombre);
      TypedQuery<Metodologia> qry = entityManager().createQuery(cqry);
      return qry.getSingleResult();
    } catch (Exception e) {
      log.error(e.getMessage());
      throw new DbException(e.getMessage());
    }
  }

  public List<Metodologia> obtenerMetodologiasPorUsuario(int usuarioId) throws DbException {
    try {
      CriteriaBuilder cb = entityManager().getCriteriaBuilder();
      CriteriaQuery<Metodologia> cqry = cb.createQuery(Metodologia.class);
      Root<Metodologia> root = cqry.from(Metodologia.class);
      cqry.select(root);
      Predicate pEqualsUsuario = cb.equal(root.get("usuario_id"), usuarioId);
      Predicate pEqualsComun = cb.equal(root.get("usuario_id"), 0);
      Predicate pOr = cb.or(pEqualsUsuario, pEqualsComun);
      cqry.where(pOr);
      TypedQuery<Metodologia> qry = entityManager().createQuery(cqry);
      return qry.getResultList();
    } catch (Exception e) {
      log.error(e.getMessage());
      throw new DbException(e.getMessage());
    }
  }

  public List<Metodologia> obtenerMetodologias() throws DbException {
    try {
      CriteriaBuilder cb = entityManager().getCriteriaBuilder();
      CriteriaQuery<Metodologia> cqry = cb.createQuery(Metodologia.class);
      Root<Metodologia> root = cqry.from(Metodologia.class);
      cqry.select(root);
      TypedQuery<Metodologia> qry = entityManager().createQuery(cqry);
      return qry.getResultList();
    } catch (Exception e) {
      log.error(e.getMessage());
      throw new DbException(e.getMessage());
    }
  }

}
