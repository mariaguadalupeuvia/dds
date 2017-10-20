package ar.org.utn.ddstpanual.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import ar.org.utn.ddstpanual.db.EmpresaDb;
import ar.org.utn.ddstpanual.exception.DbException;
import ar.org.utn.ddstpanual.model.CuentaValor;
import ar.org.utn.ddstpanual.model.Empresa;
import ar.org.utn.ddstpanual.model.Usuario;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

public class CuentasController {
  private static EmpresaDb empresaDb = new EmpresaDb();

  public static ModelAndView ejecutar(Request req, Response res) {
    Map<String, Object> model = new HashMap<>();
    // Importante para mantener el usuario en pantalla
    Usuario usuarioLoggeado = req.session().attribute("currentUser");
    if (usuarioLoggeado == null) {
      return new ModelAndView(model, "login/login.hbs");
    }
    model.put("usuario", usuarioLoggeado);
    try {
      String periodoSeleccionado = req.queryParams("periodoSeleccionado");
      List<Empresa> empresas = empresaDb.obtenerEmpresas();
      Empresa empresa = empresaDb.obtenerEmpresa(req.queryParams("empresaSeleccionada"));
      List<CuentaValor> cuentas = new ArrayList<>();
      empresa.getCuentas().stream().map(c -> cuentas.add(new CuentaValor(empresa.getNombre(), c.getNombre(), periodoSeleccionado,
          empresa.obtenerValor(c.getNombre(), periodoSeleccionado)))).collect(Collectors.toList());
      model.put("cuentas", cuentas);
      model.put("empresas", empresas);
    } catch (DbException e) {
      model.put("messageError", "No se ha podido traer los datos de la base de datos");
    }
    return new ModelAndView(model, "cuentas/listadoCuentas.hbs");
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
    return new ModelAndView(model, "cuentas/listadoCuentas.hbs");
  }

}
