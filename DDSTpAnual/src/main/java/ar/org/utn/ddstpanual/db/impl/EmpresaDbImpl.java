package ar.org.utn.ddstpanual.db.impl;

import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;
import org.uqbarproject.jpa.java8.extras.transaction.TransactionalOps;

import java.util.List;

import javax.persistence.NoResultException;

import ar.org.utn.ddstpanual.db.EmpresaDb;
import ar.org.utn.ddstpanual.exception.ArchivoException;
import ar.org.utn.ddstpanual.model.Cuenta;
import ar.org.utn.ddstpanual.model.Empresa;
import ar.org.utn.ddstpanual.model.Periodo;


public class EmpresaDbImpl implements EmpresaDb, WithGlobalEntityManager, TransactionalOps {

  @Override
  public void guardarEmpresa(Empresa empresa) throws ArchivoException {
    withTransaction(() -> {
      entityManager().persist(empresa);//persist(empresa);
    });
  }

  @Override
  public boolean exists(Empresa empresa) throws ArchivoException {
    return entityManager().contains(empresa);
  }

  @Override
  public List<Empresa> obtenerEmpresas() throws ArchivoException {
    return entityManager().createQuery("from Empresa", Empresa.class).getResultList();
  }

  @Override
  public List<Periodo> obtenerPeriodos() throws ArchivoException {
    return entityManager().createQuery("from Periodo", Periodo.class).getResultList();
  }

  @Override
  public Empresa obtenerEmpresa(String nombre) throws ArchivoException {
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

  @Override
  public Cuenta obtenerCuenta(Integer idEmpresa, String nombreCuenta) throws ArchivoException {
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

  @Override
  public Periodo obtenerPeriodo(Integer idCuenta, String fecha) throws ArchivoException {
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
