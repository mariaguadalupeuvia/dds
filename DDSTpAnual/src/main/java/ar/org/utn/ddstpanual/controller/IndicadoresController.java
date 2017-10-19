package ar.org.utn.ddstpanual.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import ar.org.utn.ddstpanual.model.Empresa;
import ar.org.utn.ddstpanual.model.FormulaIndicador;
import ar.org.utn.ddstpanual.model.Indicador;
import ar.org.utn.ddstpanual.model.Periodo;
import ar.org.utn.ddstpanual.repositorio.EmpresasRepositorio;
import ar.org.utn.ddstpanual.repositorio.IndicadoresRepositorio;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

public class IndicadoresController {

  Indicador indicador;
  List<Empresa> empresas;
  Empresa empresaCheckbox;
  List<Periodo> periodos;
  Periodo periodoCheckbox;

  public static ModelAndView ejecutar(Request req, Response res) {
    String periodoSeleccionado = req.queryParams("periodoSeleccionado");
    Empresa empresa = EmpresasRepositorio.instancia.obtenerEmpresa(req.queryParams("empresaSeleccionada"));
    Map<String, List<FormulaIndicador>> model = new HashMap<>();

    List<FormulaIndicador> indicadoresEvaluados = new ArrayList<>();
    IndicadoresRepositorio.instancia.obtenerIndicadores().stream()
        .map(indicador -> indicadoresEvaluados.add(
            new FormulaIndicador(indicador.getNombre(), periodoSeleccionado, indicador.ejecutarIndicador(periodoSeleccionado, empresa))))
        .collect(Collectors.toList());
    model.put("indicadoresEvaluados", indicadoresEvaluados);

    return new ModelAndView(model, "indicadores/indicadorEvaluado.hbs");
  }

  public static ModelAndView listar(Request req, Response res) {
    List<Empresa> empresas = EmpresasRepositorio.instancia.obtenerEmpresas();

    Map<String, List<Empresa>> model = new HashMap<>();
    model.put("empresas", empresas);
    return new ModelAndView(model, "indicadores/listadoIndicadores.hbs");
  }

  public static ModelAndView nuevo(Request req, Response res) {
    return new ModelAndView(null, "indicadores/nuevo.hbs");
  }

  public static ModelAndView crear(Request req, Response res) {
    IndicadoresRepositorio.instancia.guardarIndicador(new Indicador(req.queryParams("nombre"), req.queryParams("formula")));
    res.redirect("/indicadores/nuevo");
    return null;
  }

  public List<Indicador> obtenerIndicadores() {
    return IndicadoresRepositorio.instancia.obtenerIndicadores();
  }

  public Double ejecutarIndicador() {
    return indicador.ejecutarIndicador(periodoCheckbox.getFecha(), empresaCheckbox);
  }


}
