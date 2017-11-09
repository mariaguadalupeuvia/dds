package ar.org.utn.ddstpanual.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import ar.org.utn.ddstpanual.antlr.AntlrFormulaListener;
import ar.org.utn.ddstpanual.db.EmpresaDb;
import ar.org.utn.ddstpanual.db.IndicadorDb;
import ar.org.utn.ddstpanual.exception.ArbolException;
import ar.org.utn.ddstpanual.exception.DbException;
import ar.org.utn.ddstpanual.model.Empresa;
import ar.org.utn.ddstpanual.model.FormulaIndicador;
import ar.org.utn.ddstpanual.model.Indicador;
import ar.org.utn.ddstpanual.model.Usuario;
import lombok.extern.slf4j.Slf4j;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

@Slf4j
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
      List<String> periodos = empresaDb.obtenerPeriodos();
      List<Empresa> empresas = empresaDb.obtenerEmpresas();
      Empresa empresa = empresaDb.obtenerEmpresa(req.queryParams("empresaSeleccionada"));
      List<FormulaIndicador> indicadoresEvaluados = new ArrayList<>();
      for (Indicador ind : indicadorDb.obtenerIndicadoresPorUsuario(usuarioLoggeado.getId())) {
        monto = ind.ejecutarIndicador(periodoSeleccionado, empresa);
        if (monto >= 0) {
          indicadoresEvaluados.add(new FormulaIndicador(ind.getNombre(), ind.getFormula(), periodoSeleccionado, monto));
        }
      }
      model.put("periodos", periodos);
      model.put("empresas", empresas);
      model.put("indicadoresEvaluados", indicadoresEvaluados);
    } catch (DbException e) {
      log.error(e.getMessage());
      model.put("messageError", "No se ha podido traer los datos de la base de datos");
    } catch (ArbolException e) {
      log.error(e.getMessage());
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
      List<String> periodos = empresaDb.obtenerPeriodos();
      model.put("periodos", periodos);
      model.put("empresas", empresas);
    } catch (DbException e) {
      model.put("messageError", "No se ha podido traer los datos de la base de datos");
    }
    return new ModelAndView(model, "indicadores/listadoIndicadores.hbs");
  }

  public static ModelAndView nuevo(Request req, Response res) {
    Map<String, Object> model = new HashMap<>();
    // Importante para mantener el usuario en pantalla
    Usuario usuarioLoggeado = req.session().attribute("currentUser");
    if (usuarioLoggeado == null) {
      return new ModelAndView(model, "login/login.hbs");
    }
    model.put("usuario", usuarioLoggeado);
    try {
      List<Indicador> indicadores = indicadorDb.obtenerIndicadores();
      model.put("indicadores", indicadores);
      return new ModelAndView(model, "indicadores/nuevo.hbs");
    } catch (DbException e) {
      log.error(e.getMessage());
      model.put("messageError", "No se ha podido traer los datos de la base de datos");
      return new ModelAndView(model, "home/home.hbs");
    }
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
      List<Indicador> indicadores = indicadorDb.obtenerIndicadores();
      List<String> periodos = empresaDb.obtenerPeriodos();
      model.put("periodos", periodos);
      model.put("indicadores", indicadores);
      String nombre = req.queryParams("nombre");
      String formula = req.queryParams("formula");
      if (validarFormula(formula)) {
        Indicador indicador = new Indicador(nombre, formula, usuarioLoggeado.getId());
        indicadorDb.guardarIndicador(indicador);
        model.put("messageSuccess", "Se guardo correctamente el indicador " + req.queryParams("nombre"));
      } else {
        model.put("messageError", "La formula ingresada contiene un error sintactico.");
      }
      return new ModelAndView(model, "indicadores/nuevo.hbs");
    } catch (DbException e) {
      model.put("messageError", "No se ha podido traer los datos de la base de datos");
      return new ModelAndView(model, "home/home.hbs");
    }
  }

  private static boolean validarFormula(String formula) {
    final AntlrFormulaListener entrada = new AntlrFormulaListener();
    return entrada.validarFormula(formula);
  }

}
