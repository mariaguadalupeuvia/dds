package Repositorio;

import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;
import org.uqbarproject.jpa.java8.extras.transaction.TransactionalOps;

import java.util.List;
import javax.persistence.NoResultException;

import ar.org.utn.ddstpanual.exception.ArchivoException;
import ar.org.utn.ddstpanual.model.Cuenta;
import ar.org.utn.ddstpanual.model.Empresa;
import ar.org.utn.ddstpanual.model.Periodo;


public class RepositorioEmpresas implements WithGlobalEntityManager, TransactionalOps {

	public static RepositorioEmpresas instancia = new RepositorioEmpresas();
	
  public void guardarEmpresa(Empresa empresa) throws ArchivoException {
    withTransaction(() -> {
      entityManager().persist(empresa);
    });
  }

  public boolean exists(Empresa empresa) {
    return entityManager().contains(empresa);
  }

  public List<Empresa> obtenerEmpresas() {
    return entityManager().createQuery("from Empresa", Empresa.class).getResultList();
  }

  public List<Periodo> obtenerPeriodos() {
    return entityManager().createQuery("from Periodo", Periodo.class).getResultList();
  }

  public Empresa obtenerEmpresa(String nombre) {
    Empresa empresa;
    try{
      empresa =  entityManager().createQuery("from Empresa where nombre = :nombre", Empresa.class).setParameter("nombre", nombre).getSingleResult();
    } catch (NoResultException ex){
      return null;
    } catch (Exception ex){
      throw ex;
    }  
    return empresa;
  }

  public Cuenta obtenerCuenta(Integer idEmpresa, String nombreCuenta) {
    Cuenta cuenta;
    try{
      cuenta =  entityManager().createQuery("from Cuenta where nombre = :nombre and empresa_id = :empresa_id", Cuenta.class)
            .setParameter("nombre", nombreCuenta)
            .setParameter("empresa_id", idEmpresa)
            .getSingleResult();
    } catch (NoResultException ex){
      return null;
    } catch (Exception ex){
      throw ex;
    }  
    return cuenta;
  }

  public Periodo obtenerPeriodo(Integer idCuenta, String fecha) {
    Periodo periodo;
    try{
      periodo =  entityManager().createQuery("from Periodo where fecha = :fecha and cuenta_id = :cuenta_id", Periodo.class)
            .setParameter("fecha", fecha)
            .setParameter("cuenta_id", idCuenta)
            .getSingleResult();
    } catch (NoResultException ex){
      return null;
    } catch (Exception ex){
      throw ex;
    }  
    return periodo;
  }

}
