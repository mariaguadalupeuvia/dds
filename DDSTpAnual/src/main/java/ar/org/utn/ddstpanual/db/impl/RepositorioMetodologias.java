package ar.org.utn.ddstpanual.db.impl;

import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;
import org.uqbarproject.jpa.java8.extras.transaction.TransactionalOps;

import java.util.List;
import ar.org.utn.ddstpanual.model.metodologia.Metodologia;


public class RepositorioMetodologias implements WithGlobalEntityManager, TransactionalOps {
	
  public static RepositorioMetodologias instancia = new RepositorioMetodologias();
	
  public void guardarMetodologia(Metodologia metodologia)
  {
      entityManager().persist(metodologia);
  }

  public Metodologia obtenerMetodologia(String nombre) {
      return entityManager().createQuery("from Metodologia m WHERE m.nombre LIKE :nombreX", Metodologia.class)
          .setParameter("nombreX", nombre).setMaxResults(1).getSingleResult();
  }
  
	public Metodologia buscar(int id){
		return entityManager().find(Metodologia.class, id);
	}
	
  public List<Metodologia> obtenerMetodologias() {
	     return entityManager().createQuery("from Metodologia", Metodologia.class).getResultList();
  }


}
