package ar.org.utn.ddstpanual.db;

import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;
import org.uqbarproject.jpa.java8.extras.transaction.TransactionalOps;

import java.util.List;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaDelete;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import ar.org.utn.ddstpanual.exception.ArbolException;
import ar.org.utn.ddstpanual.exception.DbException;
import ar.org.utn.ddstpanual.model.Empresa;
import ar.org.utn.ddstpanual.model.Indicador;
import ar.org.utn.ddstpanual.model.IndicadorPrecalculado;
import ar.org.utn.ddstpanual.model.Periodo;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class IndicadorPrecalculadoDb implements WithGlobalEntityManager, TransactionalOps {

  public void guardarIndicadorPrecalculado(IndicadorPrecalculado indicadorPrecalculado) throws DbException {
    try {
      withTransaction(() -> {
        entityManager().persist(indicadorPrecalculado);
      });
    } catch (Exception e) {
      log.error(e.getMessage());
      throw new DbException(e.getMessage());
    }
  }

  public void precalcularIndicadores(List<Empresa> empresas, List<Indicador> indicadores, List<Periodo> periodos) throws DbException {
    Double valor;
    for (Indicador indicador : indicadores) {
      for (Empresa empresa : empresas) {
        for (Periodo periodo : periodos) {
          try {
            valor = indicador.ejecutarIndicador(periodo.getFecha(), empresa);
            IndicadorPrecalculado indicadorPrecalculado = new IndicadorPrecalculado(empresa, indicador, periodo.getFecha(), valor);
            guardarIndicadorPrecalculado(indicadorPrecalculado);
          } catch (ArbolException e) {
            log.error(e.getMessage());
            throw new DbException("Ocurrio un error al tratar de procesar la empresa " + empresa.getNombre() + " usando el indicador " + indicador.getNombre() + " en la fecha " + periodo.getFecha());
          }
        }
      }
    }
  }

  public IndicadorPrecalculado obtenerIndicadorPrecalculado(Empresa empresa, Indicador indicador, String fecha) throws DbException {
    try {
      CriteriaBuilder cb = entityManager().getCriteriaBuilder();
      CriteriaQuery<IndicadorPrecalculado> cqry = cb.createQuery(IndicadorPrecalculado.class);
      Root<IndicadorPrecalculado> root = cqry.from(IndicadorPrecalculado.class);
      cqry.select(root);
      Predicate pEqualsEmpresa = cb.equal(root.get("empresa"), empresa);
      Predicate pEqualsIndicador = cb.equal(root.get("indicador"), indicador);
      Predicate pEqualsFecha = cb.equal(root.get("fecha"), fecha);
      Predicate pAnd = cb.and(pEqualsEmpresa, pEqualsIndicador, pEqualsFecha);
      cqry.where(pAnd);
      TypedQuery<IndicadorPrecalculado> qry = entityManager().createQuery(cqry);
      return qry.getSingleResult();
    } catch (Exception e) {
      log.error(e.getMessage());
      throw new DbException(e.getMessage());
    }
  }

  public void borrarIndicadoresPrecalculados() throws DbException {
    try {
      withTransaction(() -> {
        CriteriaBuilder cb = entityManager().getCriteriaBuilder();
        CriteriaDelete<IndicadorPrecalculado> cqry = cb.createCriteriaDelete(IndicadorPrecalculado.class);
        cqry.from(IndicadorPrecalculado.class);
        entityManager().createQuery(cqry).executeUpdate();
      });
    } catch (Exception e) {
      log.error(e.getMessage());
      throw new DbException(e.getMessage());
    }
  }

  public List<IndicadorPrecalculado> obtenerIndicadoresPrecalculados() throws DbException {
    try {
      CriteriaBuilder cb = entityManager().getCriteriaBuilder();
      CriteriaQuery<IndicadorPrecalculado> cqry = cb.createQuery(IndicadorPrecalculado.class);
      Root<IndicadorPrecalculado> root = cqry.from(IndicadorPrecalculado.class);
      cqry.select(root);
      TypedQuery<IndicadorPrecalculado> qry = entityManager().createQuery(cqry);
      return qry.getResultList();
    } catch (Exception e) {
      log.error(e.getMessage());
      throw new DbException(e.getMessage());
    }
  }

}
