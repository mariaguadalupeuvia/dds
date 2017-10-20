package testDbTest;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;


import ar.org.utn.ddstpanual.db.impl.UsuarioDbImpl;
import ar.org.utn.ddstpanual.exception.DbException;
import ar.org.utn.ddstpanual.model.Usuario;

public class UsuarioDBTest {
  UsuarioDbImpl usuarioDB;
  
  @Before
  public void init() throws DbException{
    usuarioDB = new UsuarioDbImpl();
    
  }
  
//Test Usuario
  
 @Test
 public void testVerificarUsuario() throws DbException {
   Usuario usuario = new Usuario("miguel", "5f4dcc3b5aa765d61d8327deb882cf99");
   assertTrue(usuarioDB.verificarUsuario(usuario));
 }
 

}
