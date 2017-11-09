package ar.org.utn.ddstpanual.controller;

import java.util.HashMap;
import java.util.Map;

import ar.org.utn.ddstpanual.exception.ServiceException;
import ar.org.utn.ddstpanual.model.Usuario;
import ar.org.utn.ddstpanual.service.UsuarioService;
import lombok.extern.slf4j.Slf4j;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

@Slf4j
public class LogInController {

  private static UsuarioService usuarioService;

  public static ModelAndView login(Request req, Response res) {
    return new ModelAndView(null, "login/login.hbs");
  }

  public static ModelAndView ingresar(Request req, Response res) {
    Map<String, Object> model = new HashMap<>();
    Usuario usuario = new Usuario(req.queryParams("usuario"), req.queryParams("password"));
    try {
      if (getUsuarioService().verificarUsuario(usuario)) {
        usuario = getUsuarioService().obtenerUsuario(usuario.getNombre());
        req.session().attribute("currentUser", usuario);
        res.redirect("/home");
      } else {
        throw new ServiceException("El usuario no paso la verificación.");
      }
      return null;
    } catch (ServiceException e) {
      log.error(e.getMessage());
      model.put("ERROR_LOGIN", "El usuario o contraseña son incorrectos. Inténtalo de nuevo.");
      return new ModelAndView(model, "login/login.hbs");
    }

  }

  public static ModelAndView logout(Request req, Response res) {
    req.session().removeAttribute("currentUser");
    res.redirect("/login");
    return null;
  }

  public static UsuarioService getUsuarioService() {
    if (usuarioService == null) {
      usuarioService = new UsuarioService();
    }
    return usuarioService;
  }
}
