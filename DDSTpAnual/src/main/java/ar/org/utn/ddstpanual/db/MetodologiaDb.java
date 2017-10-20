package ar.org.utn.ddstpanual.db;

import org.uqbarproject.jpa.java8.extras.PerThreadEntityManagers;
import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;
import org.uqbarproject.jpa.java8.extras.transaction.TransactionalOps;

import java.util.List;

import javax.persistence.EntityManager;

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
      return entityManager().createQuery("from Metodologia m WHERE m.nombre LIKE :nombreX", Metodologia.class)
          .setParameter("nombreX", nombre).setMaxResults(1).getSingleResult();
    } catch (Exception e) {
      log.error(e.getMessage());
      throw new DbException(e.getMessage());
    }
  }

  public List<Metodologia> obtenerMetodologias() throws DbException {
    try {
      EntityManager entity = PerThreadEntityManagers.getEntityManager();
      return entity.createQuery("from Metodologia", Metodologia.class).getResultList();
    } catch (Exception e) {
      log.error(e.getMessage());
      throw new DbException(e.getMessage());
    }
  }

}
