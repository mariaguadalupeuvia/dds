package ar.org.utn.ddstpanual.db.impl;

import org.uqbarproject.jpa.java8.extras.PerThreadEntityManagers;
import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;
import org.uqbarproject.jpa.java8.extras.transaction.TransactionalOps;

import java.util.List;

import javax.persistence.EntityManager;

import ar.org.utn.ddstpanual.db.MetodologiaDb;
import ar.org.utn.ddstpanual.exception.ArchivoException;
import ar.org.utn.ddstpanual.model.metodologia.Metodologia;

public class MetodologiaDbImpl implements MetodologiaDb, WithGlobalEntityManager, TransactionalOps {

  @Override
  public void guardarMetodologia(Metodologia metodologia) throws ArchivoException {
    // TODO Auto-generated method stub
  }

  @Override
  public Metodologia obtenerMetodologia(String nombre) throws ArchivoException {
    try {
      return entityManager().createQuery("from Metodologia m WHERE m.nombre LIKE :nombreX", Metodologia.class)
          .setParameter("nombreX", nombre).getSingleResult();
    } catch (Exception e) {
      System.out.println(e.getMessage());
    }
    return null;
  }


  @Override
  public List<Metodologia> obtenerMetodologias() throws ArchivoException {
    try {
      EntityManager entity = PerThreadEntityManagers.getEntityManager();
      return entity.createQuery("from Metodologia", Metodologia.class).getResultList();
    } catch (Exception e) {
      System.out.println(e.getMessage());
    }
    return null;
  }


}
