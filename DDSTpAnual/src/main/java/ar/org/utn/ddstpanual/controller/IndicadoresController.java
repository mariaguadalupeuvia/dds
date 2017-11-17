package ar.org.utn.ddstpanual.controller;

import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import ar.org.utn.ddstpanual.antlr.AntlrFormulaListener;
import ar.org.utn.ddstpanual.db.EmpresaDb;
import ar.org.utn.ddstpanual.db.IndicadorDb;
import ar.org.utn.ddstpanual.db.IndicadorPrecalculadoDb;
import ar.org.utn.ddstpanual.exception.DbException;
import ar.org.utn.ddstpanual.exception.ServiceException;
import ar.org.utn.ddstpanual.model.Empresa;
import ar.org.utn.ddstpanual.model.FormulaIndicador;
import ar.org.utn.ddstpanual.model.Indicador;
import ar.org.utn.ddstpanual.model.Periodo;
import ar.org.utn.ddstpanual.model.Usuario;
import ar.org.utn.ddstpanual.service.IndicadorPrecalculadoService;
import lombok.extern.slf4j.Slf4j;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

@Slf4j
public class IndicadoresController {

  private static EmpresaDb empresaDb = new EmpresaDb();
  private static IndicadorDb indicadorDb = new IndicadorDb();
  private static IndicadorPrecalculadoDb indicadorPrecalculadoDb = new IndicadorPrecalculadoDb();
  private static IndicadorPrecalculadoService indicadorPrecalculadoService = new IndicadorPrecalculadoService();

  public static ModelAndView ejecutar(final Request req, final Response res) {
    final Map<String, Object> model = new HashMap<>();
    // Importante para mantener el usuario en pantalla
    final Usuario usuarioLoggeado = req.session().attribute("currentUser");
    if (usuarioLoggeado == null) {
      return new ModelAndView(model, "login/login.hbs");
    }
    model.put("usuario", usuarioLoggeado);
    final String periodoSeleccionado = req.queryParams("periodoSeleccionado");
    Double monto;
    try {
      final List<Periodo> periodos = empresaDb.obtenerPeriodos();
      final List<Empresa> empresas = empresaDb.obtenerEmpresas();
      final Empresa empresa = empresaDb.obtenerEmpresa(req.queryParams("empresaSeleccionada"));
      final List<FormulaIndicador> indicadoresEvaluados = new ArrayList<>();
      for (final Indicador indicador : indicadorDb.obtenerIndicadoresPorUsuario(usuarioLoggeado.getId())) {
        monto = indicadorPrecalculadoDb.obtenerIndicadorPrecalculado(empresa, indicador, periodoSeleccionado).getValorIndicador();
        if (monto >= 0) {
          indicadoresEvaluados.add(new FormulaIndicador(indicador.getNombre(), indicador.getFormula(), periodoSeleccionado, monto));
        }
      }
      model.put("empresaSeleccionada", req.queryParams("empresaSeleccionada"));
      model.put("periodoSeleccionado", periodoSeleccionado);
      model.put("periodos", periodos);
      model.put("empresas", empresas);
      model.put("indicadoresEvaluados", indicadoresEvaluados);
    } catch (final DbException e) {
      log.error(e.getMessage());
      model.put("messageError", "No se ha podido traer los datos de la base de datos");
    }
    return new ModelAndView(model, "indicadores/listadoIndicadores.hbs");
  }

  public static ModelAndView listar(final Request req, final Response res) {
    final Map<String, Object> model = new HashMap<>();
    // Importante para mantener el usuario en pantalla
    final Usuario usuarioLoggeado = req.session().attribute("currentUser");
    if (usuarioLoggeado == null) {
      return new ModelAndView(model, "login/login.hbs");
    }
    model.put("usuario", usuarioLoggeado);
    try {
      final List<Empresa> empresas = empresaDb.obtenerEmpresas();
      final List<Periodo> periodos = empresaDb.obtenerPeriodos();
      model.put("empresaSeleccionada", empresas.get(0).getNombre());
      model.put("periodoSeleccionado", periodos.get(0).getFecha());
      model.put("periodos", periodos);
      model.put("empresas", empresas);
    } catch (final DbException e) {
      model.put("messageError", "No se ha podido traer los datos de la base de datos");
    }
    return new ModelAndView(model, "indicadores/listadoIndicadores.hbs");
  }

  public static ModelAndView alta(final Request req, final Response res) {
    final Map<String, Object> model = new HashMap<>();
    // Importante para mantener el usuario en pantalla
    final Usuario usuarioLoggeado = req.session().attribute("currentUser");
    if (usuarioLoggeado == null) {
      return new ModelAndView(model, "login/login.hbs");
    }
    model.put("usuario", usuarioLoggeado);
    try {
      final List<Indicador> indicadores = indicadorDb.obtenerIndicadoresPorUsuario(usuarioLoggeado.getId());
      model.put("indicadores", indicadores);
      return new ModelAndView(model, "indicadores/alta.hbs");
    } catch (final DbException e) {
      log.error(e.getMessage());
      model.put("messageError", "No se ha podido traer los datos de la base de datos");
      return new ModelAndView(model, "home/home.hbs");
    }
  }

  public static ModelAndView crear(final Request req, final Response res) {
    final Map<String, Object> model = new HashMap<>();
    // Importante para mantener el usuario en pantalla
    final Usuario usuarioLoggeado = req.session().attribute("currentUser");
    if (usuarioLoggeado == null) {
      return new ModelAndView(model, "login/login.hbs");
    }
    model.put("usuario", usuarioLoggeado);
    try {
      final String nombre = StringUtils.replace(req.queryParams("nombre"), " ", "");
      final String formula = StringUtils.replace(req.queryParams("formula"), " ", "");
      if (StringUtils.isNotEmpty(nombre) && StringUtils.isNotEmpty(formula)) {
        if (validarFormula(formula)) {
          final Indicador indicador = new Indicador(nombre, formula, usuarioLoggeado.getId());
          if (!indicadorDb.exists(indicador)) {
            indicadorDb.guardarIndicador(indicador);
            model.put("messageSuccess", "Se guardo correctamente el indicador " + nombre);
          } else {
            model.put("messageError", "El indicador que quiere guardar ya existe.");
          }
        } else {
          model.put("messageError", "La formula ingresada contiene un error sintactico.");
        }
        try {
          indicadorPrecalculadoService.precalcularIndicadores();
        } catch (ServiceException e) {
          log.error(e.getMessage());
          model.put("messageError", "Ocurrio un error al precalcular los indicadores.");
        }
      } else {
        model.put("messageError", "Complete todos los campos del formulario.");
      }
      final List<Indicador> indicadores = indicadorDb.obtenerIndicadores();
      model.put("indicadores", indicadores);
      return new ModelAndView(model, "indicadores/alta.hbs");
    } catch (final DbException e) {
      model.put("messageError", "No se ha podido traer los datos de la base de datos");
      return new ModelAndView(model, "home/home.hbs");
    }
  }

  private static boolean validarFormula(final String formula) {
    final AntlrFormulaListener entrada = new AntlrFormulaListener();
    return entrada.validarFormula(formula);
  }

}
