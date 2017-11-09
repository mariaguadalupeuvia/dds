package ar.org.utn.ddstpanual.db;

import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;
import org.uqbarproject.jpa.java8.extras.transaction.TransactionalOps;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import ar.org.utn.ddstpanual.exception.DbException;
import ar.org.utn.ddstpanual.model.Usuario;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class UsuarioDb implements WithGlobalEntityManager, TransactionalOps {

  public boolean verificarUsuario(Usuario usuario) throws DbException {
    try {
      return obtenerUsuario(usuario.getNombre()).getPassword().equals(usuario.getPassword());
    } catch (Exception e) {
      log.error(e.getMessage());
      throw new DbException(e.getMessage());
    }
  }

  public void guardarUsuario(Usuario usuario) throws DbException {
    withTransaction(() -> {
      entityManager().persist(usuario);
    });
  }

  public void eliminarUsuario(Usuario usuario) throws DbException {
    withTransaction(() -> {
      entityManager().remove(usuario);
    });
  }

  public Usuario obtenerUsuario(String nombreUsuario) throws DbException {
    try {
      CriteriaBuilder cb = entityManager().getCriteriaBuilder();
      CriteriaQuery<Usuario> cqry = cb.createQuery(Usuario.class);
      Root<Usuario> root = cqry.from(Usuario.class);
      cqry.select(root);
      Predicate pEqualsNombre = cb.equal(root.get("nombre"), nombreUsuario);
      cqry.where(pEqualsNombre);
      TypedQuery<Usuario> qry = entityManager().createQuery(cqry);
      return qry.getSingleResult();
    } catch (Exception e) {
      log.error(e.getMessage());
      throw new DbException(e.getMessage());
    }
  }

}
