package ar.org.utn.ddstpanual.archivo.impl;

import java.util.List;

import javax.persistence.EntityManager;
import org.uqbarproject.jpa.java8.extras.PerThreadEntityManagers;

import ar.org.utn.ddstpanual.archivo.MetodologiaAlmacenamiento;
import ar.org.utn.ddstpanual.exception.ArchivoException;
import ar.org.utn.ddstpanual.model.metodologia.Metodologia;

public class MetodologiaBD implements MetodologiaAlmacenamiento {

	@Override
	public void guardarMetodologia(Metodologia metodologia) throws ArchivoException 
	{
		// TODO Auto-generated method stub
	}

	@Override
	public Metodologia obtenerMetodologia(String nombre) throws ArchivoException 
	{
	  	try
	  	{
	  		EntityManager entity = PerThreadEntityManagers.getEntityManager();
	  		List<Metodologia> metodologias =  entity.createQuery("from Metodologia m WHERE m.nombre LIKE :nombreX", Metodologia.class).setParameter("nombreX", nombre).setMaxResults(1).getResultList();
	  		return metodologias.stream().findAny().orElse(null);
	  	}
	  	catch(Exception e)
	  	{
	  		System.out.println(e.getMessage());
	  	}
	  	return null;
	}
	

	@Override
	public List<Metodologia> obtenerMetodologias() throws ArchivoException {
	  	try
	  	{
	  		EntityManager entity = PerThreadEntityManagers.getEntityManager();
	  		return  entity.createQuery("from Metodologia", Metodologia.class).getResultList();
	  	}
	  	catch(Exception e)
	  	{
	  		System.out.println(e.getMessage());
	  	}
	  	return null;
	}
	

}
