package ar.org.utn.ddstpanual.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import ar.org.utn.ddstpanual.db.EmpresaDb;
import ar.org.utn.ddstpanual.db.impl.EmpresaDbImpl;
import ar.org.utn.ddstpanual.exception.DbException;
import ar.org.utn.ddstpanual.model.CuentaValor;
import ar.org.utn.ddstpanual.model.Empresa;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

public class CuentasController {
	private static EmpresaDb empresaDb = new EmpresaDbImpl();
	
  public static ModelAndView ejecutar(Request req, Response res) throws DbException {
    String periodoSeleccionado = req.queryParams("periodoSeleccionado");
    
    Empresa empresa = empresaDb.obtenerEmpresa(req.queryParams("empresaSeleccionada"));

    Map<String, List<CuentaValor>> model = new HashMap<>();
    List<CuentaValor> cuentas = new ArrayList<>();
    empresa.getCuentas().stream().map(c -> cuentas.add(
        new CuentaValor(empresa.getNombre(), c.getNombre(), periodoSeleccionado, empresa.obtenerValor(c.getNombre(), periodoSeleccionado))))
        .collect(Collectors.toList());
    model.put("cuentas", cuentas);

    return new ModelAndView(model, "cuentas/cuentaValor.hbs");
  }

  public static ModelAndView listar(Request req, Response res) throws DbException {
    List<Empresa> empresas = empresaDb.obtenerEmpresas();

    Map<String, List<Empresa>> model = new HashMap<>();
    model.put("empresas", empresas);
    return new ModelAndView(model, "cuentas/listadoCuentas.hbs");
  }

}
