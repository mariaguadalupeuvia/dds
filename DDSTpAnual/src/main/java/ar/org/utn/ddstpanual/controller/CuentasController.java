package ar.org.utn.ddstpanual.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import ar.org.utn.ddstpanual.model.CuentaValor;
import ar.org.utn.ddstpanual.model.Empresa;
import ar.org.utn.ddstpanual.repositorio.EmpresasRepositorio;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

public class CuentasController {

  public static ModelAndView ejecutar(Request req, Response res) {
    String periodoSeleccionado = req.queryParams("periodoSeleccionado");
    Empresa empresa = EmpresasRepositorio.instancia.obtenerEmpresa(req.queryParams("empresaSeleccionada"));

    Map<String, List<CuentaValor>> model = new HashMap<>();
    List<CuentaValor> cuentas = new ArrayList<>();
    empresa.getCuentas().stream().map(c -> cuentas.add(
        new CuentaValor(empresa.getNombre(), c.getNombre(), periodoSeleccionado, empresa.obtenerValor(c.getNombre(), periodoSeleccionado))))
        .collect(Collectors.toList());
    model.put("cuentas", cuentas);

    return new ModelAndView(model, "cuentas/cuentaValor.hbs");
  }

  public static ModelAndView listar(Request req, Response res) {
    List<Empresa> empresas = EmpresasRepositorio.instancia.obtenerEmpresas();

    Map<String, List<Empresa>> model = new HashMap<>();
    model.put("empresas", empresas);
    return new ModelAndView(model, "cuentas/listadoCuentas.hbs");
  }

}
