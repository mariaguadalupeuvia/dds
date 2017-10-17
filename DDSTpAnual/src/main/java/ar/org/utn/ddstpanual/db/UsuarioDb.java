package ar.org.utn.ddstpanual.db;

import ar.org.utn.ddstpanual.exception.DbException;
import ar.org.utn.ddstpanual.model.Usuario;

public interface UsuarioDb {
  
  public void guardarUsuario(Usuario usuario) throws DbException;
  
  public Usuario obtenerUsuario(String nombreUsuario) throws DbException;
  
  public void eliminarUsuario(Usuario usuario) throws DbException;
  
  public boolean verificarUsuario(Usuario usuario) throws DbException;

}
