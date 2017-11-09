package ar.org.utn.ddstpanual.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import ar.org.utn.ddstpanual.db.EmpresaDb;
import ar.org.utn.ddstpanual.db.MetodologiaDb;
import ar.org.utn.ddstpanual.exception.ArbolException;
import ar.org.utn.ddstpanual.exception.DbException;
import ar.org.utn.ddstpanual.model.Empresa;
import ar.org.utn.ddstpanual.model.Periodo;
import ar.org.utn.ddstpanual.model.Usuario;
import ar.org.utn.ddstpanual.model.metodologia.Metodologia;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

public class MetodologiasController {
  private static MetodologiaDb metodologiaDb = new MetodologiaDb();
  private static EmpresaDb empresaDb = new EmpresaDb();

  public static ModelAndView listar(Request req, Response res) {
    Map<String, Object> model = new HashMap<>();
    // Importante para mantener el usuario en pantalla
    Usuario usuarioLoggeado = req.session().attribute("currentUser");
    if (usuarioLoggeado == null) {
      return new ModelAndView(model, "login/login.hbs");
    }
    model.put("usuario", usuarioLoggeado);
    try {
      List<Metodologia> metodologias = metodologiaDb.obtenerMetodologiasPorUsuario(usuarioLoggeado.getId());
      List<Periodo> periodos = empresaDb.obtenerPeriodos();
      model.put("periodos", periodos);
      model.put("metodologias", metodologias);
    } catch (DbException e) {
      model.put("messageError", "No se ha podido traer los datos de la base de datos");
    }
    return new ModelAndView(model, "metodologias/listado.hbs");
  }

  public static ModelAndView ejecutar(Request req, Response res) {
    Map<String, Object> model = new HashMap<>();
    // Importante para mantener el usuario en pantalla
    Usuario usuarioLoggeado = req.session().attribute("currentUser");
    if (usuarioLoggeado == null) {
      return new ModelAndView(model, "login/login.hbs");
    }
    model.put("usuario", usuarioLoggeado);
    String metodologiaSeleccionada = req.queryParams("metodologiaSeleccionada");
    String periodoSeleccionado = req.queryParams("periodoSeleccionado");
    try {
      Metodologia metodologia = metodologiaDb.obtenerMetodologia(metodologiaSeleccionada);
      List<Metodologia> metodologias = metodologiaDb.obtenerMetodologiasPorUsuario(usuarioLoggeado.getId());
      List<Empresa> empresas = metodologia.ejecutarMetodologia(empresaDb.obtenerEmpresas(), new Periodo(periodoSeleccionado));
      List<Periodo> periodos = empresaDb.obtenerPeriodos();
      model.put("periodos", periodos);
      model.put("empresas", empresas);
      model.put("metodologias", metodologias);
      model.put("metodologiaSeleccionada", metodologia);
    } catch (DbException e) {
      model.put("messageError", "No se ha podido traer los datos de la base de datos");
    } catch (ArbolException e) {
      model.put("messageError", "Ha ocurrido un error al calcular los indicadores");
    }
    return new ModelAndView(model, "metodologias/listado.hbs");
  }

  public static ModelAndView crear(Request req, Response res) {
    Map<String, Object> model = new HashMap<>();
    // Importante para mantener el usuario en pantalla
    Usuario usuarioLoggeado = req.session().attribute("currentUser");
    if (usuarioLoggeado == null) {
      return new ModelAndView(model, "login/login.hbs");
    }
    model.put("usuario", usuarioLoggeado);
    return new ModelAndView(model, "metodologias/nuevo.hbs");
  }

}
