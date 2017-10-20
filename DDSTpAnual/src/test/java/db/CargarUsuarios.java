package db;

import org.junit.Before;
import org.junit.Test;

import ar.org.utn.ddstpanual.db.UsuarioDb;
import ar.org.utn.ddstpanual.exception.DbException;
import ar.org.utn.ddstpanual.model.Usuario;

public class CargarUsuarios {
  UsuarioDb usuarioDB;

  @Before
  public void init() {
    usuarioDB = new UsuarioDb();
  }

  @Test
  public void testCargaUsuarios() throws DbException {
    Usuario usuario1 = new Usuario("miguel", "5f4dcc3b5aa765d61d8327deb882cf99"); // password en md5
    Usuario usuario2 = new Usuario("ivan", "5f4dcc3b5aa765d61d8327deb882cf99"); // password en md5
    Usuario usuario3 = new Usuario("guada", "5f4dcc3b5aa765d61d8327deb882cf99"); // password en md5
    Usuario usuario4 = new Usuario("nico", "5f4dcc3b5aa765d61d8327deb882cf99"); // password en md5
    Usuario usuAdmin = new Usuario("admin", "21232f297a57a5a743894a0e4a801fc3");
    try {
      usuarioDB.guardarUsuario(usuario1);
      usuarioDB.guardarUsuario(usuario2);
      usuarioDB.guardarUsuario(usuario3);
      usuarioDB.guardarUsuario(usuario4);
      usuarioDB.guardarUsuario(usuAdmin);
    } catch (DbException e) {
      throw new DbException(e.getMessage());
    }
  }

}
