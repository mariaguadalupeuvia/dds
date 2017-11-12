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
import ar.org.utn.ddstpanual.model.Indicador;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class IndicadorDb implements WithGlobalEntityManager, TransactionalOps {

  public void guardarIndicador(Indicador indicador) throws DbException {
    try {
      withTransaction(() -> {
        entityManager().persist(indicador);
      });
    } catch (Exception e) {
      log.error(e.getMessage());
      throw new DbException(e.getMessage());
    }
  }

  public List<Indicador> obtenerIndicadores() throws DbException {
    try {
      CriteriaBuilder cb = entityManager().getCriteriaBuilder();
      CriteriaQuery<Indicador> cqry = cb.createQuery(Indicador.class);
      Root<Indicador> root = cqry.from(Indicador.class);
      cqry.select(root);
      TypedQuery<Indicador> qry = entityManager().createQuery(cqry);
      return qry.getResultList();
    } catch (Exception e) {
      log.error(e.getMessage());
      throw new DbException(e.getMessage());
    }
  }

  public List<Indicador> obtenerIndicadoresPorUsuario(int usuario_id) throws DbException {
    try {
      CriteriaBuilder cb = entityManager().getCriteriaBuilder();
      CriteriaQuery<Indicador> cqry = cb.createQuery(Indicador.class);
      Root<Indicador> root = cqry.from(Indicador.class);
      cqry.select(root);
      Predicate pEqualsUsuario = cb.equal(root.get("usuario_id"), usuario_id);
      Predicate pEqualsComun = cb.equal(root.get("usuario_id"), 0);
      Predicate pOr = cb.or(pEqualsUsuario, pEqualsComun);
      cqry.where(pOr);
      TypedQuery<Indicador> qry = entityManager().createQuery(cqry);
      return qry.getResultList();
    } catch (Exception e) {
      log.error(e.getMessage());
      throw new DbException(e.getMessage());
    }
  }

  public boolean exists(Indicador indicador) throws DbException {
    CriteriaBuilder cb = entityManager().getCriteriaBuilder();
    CriteriaQuery<Indicador> cqry = cb.createQuery(Indicador.class);
    Root<Indicador> root = cqry.from(Indicador.class);
    cqry.select(root);
    Predicate pEqualsNombre = cb.equal(root.get("nombre"), indicador.getNombre());
    Predicate pEqualsFormula = cb.equal(root.get("formula"), indicador.getFormula());
    Predicate pOr = cb.or(pEqualsNombre, pEqualsFormula);
    cqry.where(pOr);
    TypedQuery<Indicador> qry = entityManager().createQuery(cqry);
    List<Indicador> indicadores = qry.getResultList();
    return !indicadores.isEmpty();
  }

  public Indicador obtenerIndicador(String nombreIndicador) throws DbException {
    try {
      CriteriaBuilder cb = entityManager().getCriteriaBuilder();
      CriteriaQuery<Indicador> cqry = cb.createQuery(Indicador.class);
      Root<Indicador> root = cqry.from(Indicador.class);
      cqry.select(root);
      Predicate pEqualsNombre = cb.equal(root.get("nombre"), nombreIndicador);
      cqry.where(pEqualsNombre);
      TypedQuery<Indicador> qry = entityManager().createQuery(cqry);
      return qry.getSingleResult();
    } catch (Exception e) {
      log.error(e.getMessage());
      throw new DbException(e.getMessage());
    }
  }

}
