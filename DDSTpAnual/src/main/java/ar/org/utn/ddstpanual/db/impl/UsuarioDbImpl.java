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
      Usuario usuarioDb = entityManager().createQuery("from Usuario u WHERE u.nombre LIKE :nombreUsuario", Usuario.class)
          .setParameter("nombreUsuario", usuario.getNombre()).setMaxResults(1).getSingleResult();
      return usuarioDb.getPassword().equals(usuario.getPassword());
    } catch (Exception e) {
      log.error(e.getMessage());
      throw new DbException(e.getMessage());
    }
  }

}
