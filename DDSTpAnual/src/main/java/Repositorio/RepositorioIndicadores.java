package Repositorio;

import java.util.List;

import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;
import org.uqbarproject.jpa.java8.extras.transaction.TransactionalOps;

import ar.org.utn.ddstpanual.model.Indicador;


public class RepositorioIndicadores implements WithGlobalEntityManager, TransactionalOps {
  public static RepositorioIndicadores instancia = new RepositorioIndicadores();
	 
  public void guardarIndicador(Indicador indicador){
	    try {
	    	  withTransaction(() -> {
	    		  entityManager().persist(indicador);
	    	  });
	      } catch (Exception e) {
	        System.out.println(e.getMessage());
	      }
  }

  public List<Indicador> obtenerIndicadores() {
    try {
      return entityManager().createQuery("from Indicador", Indicador.class).getResultList();
    } catch (Exception e) {
      System.out.println(e.getMessage());
    }
    return null;
  }

//--------------------------------------------------------------------
  public boolean exists(Indicador indicador) {
    return (obtenerFormula(indicador.getNombre()).equals(indicador.getFormula())) || (obtenerNombre(indicador.getFormula()).equals(indicador.getNombre()));
  }

  public String obtenerFormula(String nombre)
  {
    try {
      return entityManager().createQuery("from Indicador i WHERE i.nombre LIKE '" + nombre + "'", Indicador.class)
          .setMaxResults(1).getSingleResult().getFormula();
    } catch (Exception e) {
      System.out.println(e.getMessage());
    }
    return "";
  }
  
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
