package ar.org.utn.ddstpanual.db.impl;

import java.util.List;

import javax.persistence.EntityManager;

import org.uqbarproject.jpa.java8.extras.PerThreadEntityManagers;
import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;
import org.uqbarproject.jpa.java8.extras.transaction.TransactionalOps;

import ar.org.utn.ddstpanual.db.IndicadorDb;
import ar.org.utn.ddstpanual.exception.ArchivoException;
import ar.org.utn.ddstpanual.model.Indicador;

public class IndicadorDbImpl implements IndicadorDb, WithGlobalEntityManager, TransactionalOps {

  @Override
  public void guardarIndicador(Indicador indicador) throws ArchivoException {
    withTransaction(() -> {
      entityManager().persist(indicador);
    });

  }

  @Override
  public void eliminarIndicador(Indicador indicador) throws ArchivoException {
    // TODO Auto-generated method stub

  }

  @Override
  public List<Indicador> obtenerIndicadores() throws ArchivoException {
    try {
      EntityManager entity = PerThreadEntityManagers.getEntityManager();
      return entity.createQuery("from Indicador", Indicador.class).getResultList();
    } catch (Exception e) {
      System.out.println(e.getMessage());
    }
    return null;
  }

  @Override
  public boolean exists(Indicador indicador) throws ArchivoException {
    return (obtenerFormula(indicador.getNombre()).equals(indicador.getFormula())) || (obtenerNombre(indicador.getFormula()).equals(indicador.getNombre()));
  }

  @Override
  public String obtenerFormula(String nombre) throws ArchivoException {
    try {
      return entityManager().createQuery("from Indicador i WHERE i.nombre LIKE '" + nombre + "'", Indicador.class)
          .setMaxResults(1).getSingleResult().getFormula();
    } catch (Exception e) {
      System.out.println(e.getMessage());
    }
    return "";
  }
  @Override
  public String obtenerNombre(String formula) {
	try {
	  return entityManager().createQuery("from Indicador i WHERE i.formula LIKE '" + formula + "'",Indicador.class)
		.setMaxResults(1).getSingleResult().getNombre();
	} catch (Exception e) {
	      System.out.println(e.getMessage());
	 }
	return "";
  }
  
}
