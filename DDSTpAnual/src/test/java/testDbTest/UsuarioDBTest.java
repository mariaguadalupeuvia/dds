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
    
    // Descomentar para la primera ves que se corre
    //Usuario usuario1 = new Usuario("miguel", "5f4dcc3b5aa765d61d8327deb882cf99"); //password en md5
    //usuarioDB.guardarUsuario(usuario1);
    //Usuario usuario2 = new Usuario("ivan", "5f4dcc3b5aa765d61d8327deb882cf99"); //password en md5
    //usuarioDB.guardarUsuario(usuario2);
    //Usuario usuario3 = new Usuario("guada", "5f4dcc3b5aa765d61d8327deb882cf99"); //password en md5
    //usuarioDB.guardarUsuario(usuario3);
    //Usuario usuario4 = new Usuario("nico", "5f4dcc3b5aa765d61d8327deb882cf99"); //password en md5
    //usuarioDB.guardarUsuario(usuario4);
  }
  
//Test Usuario
  
 @Test
 public void testVerificarUsuario() throws DbException {
   Usuario usuario = new Usuario("miguel", "5f4dcc3b5aa765d61d8327deb882cf99");
   assertTrue(usuarioDB.verificarUsuario(usuario));
 }
 
 @After
 public void end() throws DbException{
   // Borrar usuarios, por si existen duplicados
   //Usuario usuario1 = usuarioDB.obtenerUsuario("miguel");
   //usuarioDB.eliminarUsuario(usuario1);
 }

}
