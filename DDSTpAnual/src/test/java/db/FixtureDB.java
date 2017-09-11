package db;

import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;
import org.uqbarproject.jpa.java8.extras.test.AbstractPersistenceTest;

import ar.org.utn.ddstpanual.model.metodologia.TipoOrden;

public class FixtureDB extends AbstractPersistenceTest implements WithGlobalEntityManager {
  public FixtureDB(){
    try{
    // Creación de los tipos de Orden 
    TipoOrden tipoOrden1 = new TipoOrden();
    tipoOrden1.setIdTipoOrden(TipoOrden.ASCENDENTE);
    tipoOrden1.setNombreOrden("ASCENDENTE");
    
    TipoOrden tipoOrden2 = new TipoOrden();
    tipoOrden2.setIdTipoOrden(TipoOrden.DESCENDENTE);
    tipoOrden2.setNombreOrden("DESCENDENTE");
    
    withTransaction(() -> {
      entityManager().persist(tipoOrden1);
      entityManager().persist(tipoOrden2);      
    });
    } catch(Exception e){
      //e.printStackTrace(); Esto permite que se cree una sola vez el tipo de Orden en la base
    }
  }
}
