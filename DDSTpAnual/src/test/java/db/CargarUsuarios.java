package db;

import org.junit.Before;
import org.junit.Test;

import ar.org.utn.ddstpanual.db.impl.UsuarioDbImpl;
import ar.org.utn.ddstpanual.exception.DbException;
import ar.org.utn.ddstpanual.model.Usuario;

public class CargarUsuarios {
  UsuarioDbImpl usuarioDB;
  
  @Before 
  public void init(){
    usuarioDB = new UsuarioDbImpl();
  }
  @Test
  public void testCargaUsuarios() {
    Usuario usuario1 = new Usuario("miguel", "5f4dcc3b5aa765d61d8327deb882cf99"); //password en md5
    Usuario usuario2 = new Usuario("ivan", "5f4dcc3b5aa765d61d8327deb882cf99"); //password en md5
    Usuario usuario3 = new Usuario("guada", "5f4dcc3b5aa765d61d8327deb882cf99"); //password en md5
    Usuario usuario4 = new Usuario("nico", "5f4dcc3b5aa765d61d8327deb882cf99"); //password en md5
    try {
      usuarioDB.guardarUsuario(usuario1);
      usuarioDB.guardarUsuario(usuario2);
      usuarioDB.guardarUsuario(usuario3);
      usuarioDB.guardarUsuario(usuario4);
    } catch (DbException e) {
      e.printStackTrace();
    }
  }

}
