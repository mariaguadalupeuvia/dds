package ar.org.utn.ddstpanual.controller;

import java.util.HashMap;
import java.util.Map;

import ar.org.utn.ddstpanual.exception.ServiceException;
import ar.org.utn.ddstpanual.model.Usuario;
import ar.org.utn.ddstpanual.service.UsuarioService;
import ar.org.utn.ddstpanual.service.impl.UsuarioServiceImpl;
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
        model.put("usuario", usuario);
        return new ModelAndView(model, "home/home.hbs");
      } else {
        return new ModelAndView(model, "/");
      }
    } catch (ServiceException e) {
      log.error(e.getMessage());
      return new ModelAndView(model, "/");
    }

  }

  public static UsuarioService getUsuarioService() {
    if (usuarioService == null) {
      usuarioService = new UsuarioServiceImpl();
    }
    return usuarioService;
  }
}
