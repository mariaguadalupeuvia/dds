package ar.org.utn.ddstpanual.db.impl;

import org.uqbarproject.jpa.java8.extras.PerThreadEntityManagers;
import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;
import org.uqbarproject.jpa.java8.extras.transaction.TransactionalOps;

import java.util.List;

import javax.persistence.EntityManager;

import ar.org.utn.ddstpanual.db.MetodologiaDb;
import ar.org.utn.ddstpanual.exception.DbException;
import ar.org.utn.ddstpanual.model.metodologia.Metodologia;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class MetodologiaDbImpl implements MetodologiaDb, WithGlobalEntityManager, TransactionalOps {

  @Override
  public void guardarMetodologia(Metodologia metodologia) throws DbException {
    withTransaction(() -> {
      entityManager().persist(metodologia);
    });
  }

  @Override
  public Metodologia obtenerMetodologia(String nombre) throws DbException {
    try {
      return entityManager().createQuery("from Metodologia m WHERE m.nombre LIKE :nombreX", Metodologia.class)
          .setParameter("nombreX", nombre).setMaxResults(1).getSingleResult();
    } catch (Exception e) {
      log.error(e.getMessage());
      throw new DbException(e.getMessage());
    }
  }

  @Override
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
