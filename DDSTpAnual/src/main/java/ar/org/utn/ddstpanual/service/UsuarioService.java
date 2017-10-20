package ar.org.utn.ddstpanual.service;

import ar.org.utn.ddstpanual.exception.ServiceException;
import ar.org.utn.ddstpanual.model.Usuario;

public interface UsuarioService {

  public boolean verificarUsuario(Usuario usuario) throws ServiceException;

  public Usuario obtenerUsuario(String nombreUsuario) throws ServiceException;

}
