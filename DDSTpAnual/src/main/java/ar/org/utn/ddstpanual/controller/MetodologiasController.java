package ar.org.utn.ddstpanual.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import ar.org.utn.ddstpanual.db.EmpresaDb;
import ar.org.utn.ddstpanual.db.IndicadorPrecalculadoDb;
import ar.org.utn.ddstpanual.db.MetodologiaDb;
import ar.org.utn.ddstpanual.exception.DbException;
import ar.org.utn.ddstpanual.model.Empresa;
import ar.org.utn.ddstpanual.model.EmpresaMetodologia;
import ar.org.utn.ddstpanual.model.Indicador;
import ar.org.utn.ddstpanual.model.IndicadorPrecalculado;
import ar.org.utn.ddstpanual.model.Periodo;
import ar.org.utn.ddstpanual.model.Usuario;
import ar.org.utn.ddstpanual.model.metodologia.Condicion;
import ar.org.utn.ddstpanual.model.metodologia.Metodologia;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

public class MetodologiasController {
  private static MetodologiaDb metodologiaDb = new MetodologiaDb();
  private static EmpresaDb empresaDb = new EmpresaDb();
  private static IndicadorPrecalculadoDb indicadorPrecalculadoDb = new IndicadorPrecalculadoDb();

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
      model.put("metodologiaSeleccionada", metodologias.get(0).getNombre());
      model.put("periodoSeleccionado", periodos.get(0).getFecha());
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
      List<Periodo> periodos = empresaDb.obtenerPeriodos();
      List<Metodologia> metodologias = metodologiaDb.obtenerMetodologiasPorUsuario(usuarioLoggeado.getId());
      Metodologia metodologia = metodologiaDb.obtenerMetodologia(metodologiaSeleccionada);
      if (metodologia != null) {
        if (periodos.stream().anyMatch(p -> p.getFecha().equals(periodoSeleccionado))) {
          List<Empresa> empresas = metodologia.ejecutarMetodologia(empresaDb.obtenerEmpresas(), new Periodo(periodoSeleccionado));
          List<IndicadorPrecalculado> indicadoresPrecalculados = indicadorPrecalculadoDb.obtenerIndicadoresPrecalculados();

          List<EmpresaMetodologia> empresasMetodologias = new ArrayList<>();
          for (Empresa empresa : empresas) {
            List<IndicadorPrecalculado> indisPre = indicadoresPrecalculados.stream().filter(indicadorPre -> empresa.equals(indicadorPre.getEmpresa())).collect(Collectors.toList());
            indisPre = indisPre.stream().filter(indicadorPre -> contieneIndicador(metodologia.getCondiciones(), indicadorPre.getIndicador())).collect(Collectors.toList());
            indisPre = indisPre.stream().filter(indicadorPre -> indicadorPre.getFecha().equals(periodoSeleccionado)).collect(Collectors.toList());
            empresasMetodologias.add(new EmpresaMetodologia(empresa, indisPre));
          }
          model.put("empresasMetodologias", empresasMetodologias);
          model.put("metodologiaSeleccionada", metodologiaSeleccionada);
          model.put("metodologiaEncontrada", metodologia);
          model.put("periodoSeleccionado", periodoSeleccionado);
        } else {
          model.put("metodologiaSeleccionada", metodologias.get(0).getNombre());
          model.put("periodoSeleccionado", periodos.get(0).getFecha());
          model.put("messageError", "El periodo ingresada no existe.");
        }
      } else {
        model.put("metodologiaSeleccionada", metodologias.get(0).getNombre());
        model.put("periodoSeleccionado", periodos.get(0).getFecha());
        model.put("messageError", "La metodologia ingresada no existe.");
      }
      model.put("metodologias", metodologias);
      model.put("periodos", periodos);
    } catch (DbException e) {
      model.put("messageError", "No se ha podido traer los datos de la base de datos.");
    }
    return new ModelAndView(model, "metodologias/listado.hbs");
  }

  public static boolean contieneIndicador(List<Condicion> condiciones, Indicador indicador) {
    boolean flag = false;
    for (Condicion condicion : condiciones) {
      if (condicion.getIndicador().equals(indicador)) {
        flag = true;
      }
    }
    return flag;
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
