package ar.org.utn.ddstpanual.db.impl;

import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;
import org.uqbarproject.jpa.java8.extras.transaction.TransactionalOps;

import ar.org.utn.ddstpanual.db.UsuarioDb;
import ar.org.utn.ddstpanual.exception.DbException;
import ar.org.utn.ddstpanual.model.Usuario;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class UsuarioDbImpl implements UsuarioDb, WithGlobalEntityManager, TransactionalOps {

  @Override
  public boolean verificarUsuario(Usuario usuario) throws DbException {
    try {
      return obtenerUsuario(usuario.getNombre()).getPassword().equals(usuario.getPassword());
    } catch (Exception e) {
      log.error(e.getMessage());
      throw new DbException(e.getMessage());
    }
  }

  @Override
  public void guardarUsuario(Usuario usuario) throws DbException {
    withTransaction(() -> {
      entityManager().persist(usuario);
    });   
  }
  
  @Override
  public void eliminarUsuario(Usuario usuario) throws DbException {
    withTransaction(() -> {
      entityManager().remove(usuario);
    });   
    
  }

  @Override
  public Usuario obtenerUsuario(String nombreUsuario) throws DbException {
    try {
      Usuario usuarioDb = entityManager().createQuery("from Usuario u WHERE u.nombre LIKE :nombreUsuario", Usuario.class)
          .setParameter("nombreUsuario", nombreUsuario).setMaxResults(1).getSingleResult();
      return usuarioDb;
    } catch (Exception e) {
      log.error(e.getMessage());
      throw new DbException(e.getMessage());
    }
  }

}
