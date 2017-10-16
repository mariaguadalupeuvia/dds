package ar.org.utn.ddstpanual.service.impl;

import org.apache.commons.codec.digest.DigestUtils;

import ar.org.utn.ddstpanual.db.UsuarioDb;
import ar.org.utn.ddstpanual.db.impl.UsuarioDbImpl;
import ar.org.utn.ddstpanual.exception.DbException;
import ar.org.utn.ddstpanual.exception.ServiceException;
import ar.org.utn.ddstpanual.model.Usuario;
import ar.org.utn.ddstpanual.service.UsuarioService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class UsuarioServiceImpl implements UsuarioService {

  UsuarioDb usuarioDb;

  @Override
  public boolean verificarUsuario(Usuario usuario) throws ServiceException {
    try {
      String passwordHash = DigestUtils.md5Hex(usuario.getPassword()).toUpperCase();
      usuario.setPassword(passwordHash);
      return getUsuarioDb().verificarUsuario(usuario);
    } catch (DbException e) {
      log.error(e.getMessage());
      throw new ServiceException(e.getMessage());
    }
  }

  public UsuarioDb getUsuarioDb() {
    if (usuarioDb != null) {
      return usuarioDb;
    }
    usuarioDb = new UsuarioDbImpl();
    return usuarioDb;
  }


}
