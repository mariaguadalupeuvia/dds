package ar.org.utn.ddstpanual.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import ar.org.utn.ddstpanual.db.EmpresaDb;
import ar.org.utn.ddstpanual.db.IndicadorDb;
import ar.org.utn.ddstpanual.exception.ArbolException;
import ar.org.utn.ddstpanual.exception.DbException;
import ar.org.utn.ddstpanual.model.Empresa;
import ar.org.utn.ddstpanual.model.FormulaIndicador;
import ar.org.utn.ddstpanual.model.Indicador;
import ar.org.utn.ddstpanual.model.Usuario;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

public class IndicadoresController {
  private static EmpresaDb empresaDb = new EmpresaDb();
  private static IndicadorDb indicadorDb = new IndicadorDb();

  public static ModelAndView ejecutar(Request req, Response res) {
    Map<String, Object> model = new HashMap<>();
    // Importante para mantener el usuario en pantalla
    Usuario usuarioLoggeado = req.session().attribute("currentUser");
    if (usuarioLoggeado == null) {
      return new ModelAndView(model, "login/login.hbs");
    }
    model.put("usuario", usuarioLoggeado);
    String periodoSeleccionado = req.queryParams("periodoSeleccionado");
    Double monto;
    try {
      List<Empresa> empresas = empresaDb.obtenerEmpresas();
      Empresa empresa = empresaDb.obtenerEmpresa(req.queryParams("empresaSeleccionada"));
      List<FormulaIndicador> indicadoresEvaluados = new ArrayList<>();
      for (Indicador ind : indicadorDb.obtenerIndicadoresPorUsuario(usuarioLoggeado.getId())) {
        monto = ind.ejecutarIndicador(periodoSeleccionado, empresa);
        if (monto >= 0) {
          indicadoresEvaluados.add(new FormulaIndicador(ind.getNombre(), periodoSeleccionado, monto));
        }
      }
      model.put("empresas", empresas);
      model.put("indicadoresEvaluados", indicadoresEvaluados);
    } catch (DbException e) {
      model.put("messageError", "No se ha podido traer los datos de la base de datos");
    } catch (ArbolException e) {
      model.put("messageError", "Ha ocurrido un error al calcular los indicadores");
    }
    return new ModelAndView(model, "indicadores/listadoIndicadores.hbs");
  }

  public static ModelAndView listar(Request req, Response res) {
    Map<String, Object> model = new HashMap<>();
    // Importante para mantener el usuario en pantalla
    Usuario usuarioLoggeado = req.session().attribute("currentUser");
    if (usuarioLoggeado == null) {
      return new ModelAndView(model, "login/login.hbs");
    }
    model.put("usuario", usuarioLoggeado);
    try {
      List<Empresa> empresas = empresaDb.obtenerEmpresas();
      model.put("empresas", empresas);
    } catch (DbException e) {
      model.put("messageError", "No se ha podido traer los datos de la base de datos");
    }
    return new ModelAndView(model, "indicadores/listadoIndicadores.hbs");
  }

  public static ModelAndView nuevo(Request req, Response res) {
    return new ModelAndView(null, "indicadores/nuevo.hbs");
  }

  public static ModelAndView crear(Request req, Response res) {
    Map<String, Object> model = new HashMap<>();
    // Importante para mantener el usuario en pantalla
    Usuario usuarioLoggeado = req.session().attribute("currentUser");
    if (usuarioLoggeado == null) {
      return new ModelAndView(model, "login/login.hbs");
    }
    model.put("usuario", usuarioLoggeado);
    try {
      indicadorDb.guardarIndicador(new Indicador(req.queryParams("nombre"), req.queryParams("formula"), usuarioLoggeado.getId()));
      model.put("messageSuccess", "Se guardo correctamente el indicador " + req.queryParams("nombre"));
    } catch (DbException e) {
      model.put("messageError", "No se ha podido traer los datos de la base de datos");
    }
    return new ModelAndView(model, "home/home.hbs");
  }

  public List<Indicador> obtenerIndicadores() throws DbException {
    return indicadorDb.obtenerIndicadores();
  }
}
