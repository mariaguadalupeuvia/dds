package ar.org.utn.ddstpanual.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import ar.org.utn.ddstpanual.model.Empresa;
import ar.org.utn.ddstpanual.model.Periodo;
import ar.org.utn.ddstpanual.model.metodologia.Condicion;
import ar.org.utn.ddstpanual.model.metodologia.Metodologia;
import ar.org.utn.ddstpanual.repositorio.EmpresasRepositorio;
import ar.org.utn.ddstpanual.repositorio.MetodologiasRepositorio;
import db.FixtureDB;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

public class MetodologiasController {

  public static ModelAndView listar(Request req, Response res) {
    List<Metodologia> metodologias = MetodologiasRepositorio.instancia.obtenerMetodologias();

    Map<String, List<Metodologia>> model = new HashMap<>();
    model.put("metodologias", metodologias);
    return new ModelAndView(model, "metodologias/listado.hbs");
  }

  public static ModelAndView ejecutar(Request req, Response res) {
    String metodologiaSeleccionada = req.queryParams("metodologiaSeleccionada");
    String periodoSeleccionado = req.queryParams("periodoSeleccionado");
    Metodologia metodologia = MetodologiasRepositorio.instancia.obtenerMetodologia(metodologiaSeleccionada);
    List<Empresa> empresas =
        metodologia.ejecutarMetodologia(EmpresasRepositorio.instancia.obtenerEmpresas(), new Periodo(periodoSeleccionado));
    Map<String, List<Empresa>> model = new HashMap<>();
    model.put("empresas", empresas);
    return new ModelAndView(model, "metodologias/ejecutado.hbs");
  }

  public static ModelAndView crear(Request req, Response res) {
    FixtureDB fixture = new FixtureDB();
    Map<String, Object> model = new HashMap<>();
    // model.put("filtros", fixture.getFiltros());
    // model.put("indicadores", fixture.getIndicadores());
    return new ModelAndView(model, "metodologias/nuevo.hbs");
  }

  public static void guardar(Request req, Response res) {
    List<Condicion> condiciones = new ArrayList<Condicion>();
    res.redirect("/metodologias");
  }
}
