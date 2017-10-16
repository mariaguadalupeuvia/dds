package ar.org.utn.ddstpanual.db;

import ar.org.utn.ddstpanual.exception.DbException;
import ar.org.utn.ddstpanual.model.Usuario;

public interface UsuarioDb {

  public boolean verificarUsuario(Usuario usuario) throws DbException;

}
