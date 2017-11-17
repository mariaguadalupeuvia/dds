package ar.org.utn.ddstpanual.controller;

import org.apache.commons.io.IOUtils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.servlet.MultipartConfigElement;
import javax.servlet.ServletException;
import javax.servlet.http.Part;

import ar.org.utn.ddstpanual.db.EmpresaDb;
import ar.org.utn.ddstpanual.exception.DbException;
import ar.org.utn.ddstpanual.exception.ServiceException;
import ar.org.utn.ddstpanual.model.CuentaValor;
import ar.org.utn.ddstpanual.model.Empresa;
import ar.org.utn.ddstpanual.model.Periodo;
import ar.org.utn.ddstpanual.model.Usuario;
import ar.org.utn.ddstpanual.service.IndicadorPrecalculadoService;
import lombok.extern.slf4j.Slf4j;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

@Slf4j
public class CuentasController {

  private static EmpresaDb empresaDb = new EmpresaDb();
  private static IndicadorPrecalculadoService indicadorPrecalculadoService = new IndicadorPrecalculadoService();

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
      Empresa empresa = empresaDb.obtenerEmpresa(req.queryParams("empresaSeleccionada"));
      List<Periodo> periodos = empresaDb.obtenerPeriodos();
      List<Empresa> empresas = empresaDb.obtenerEmpresas();
      if (!periodos.stream().anyMatch(p -> p.getFecha().equals(periodoSeleccionado))) {
        model.put("empresaSeleccionada", empresas.get(0).getNombre());
        model.put("periodoSeleccionado", periodos.get(0).getFecha());
        model.put("messageError", "El periodo ingresado no existe en las opciones.");
      } else {
        if (empresa != null) {
          List<CuentaValor> cuentas = new ArrayList<>();
          empresa.getCuentas().stream().map(c -> cuentas.add(new CuentaValor(empresa.getNombre(), c.getNombre(), periodoSeleccionado, empresa.obtenerValor(c.getNombre(), periodoSeleccionado))))
              .collect(Collectors.toList());
          model.put("cuentas", cuentas);
          model.put("empresaSeleccionada", req.queryParams("empresaSeleccionada"));
          model.put("periodoSeleccionado", periodoSeleccionado);
        } else {
          model.put("messageError", "La empresa ingresada es incorrecta.");
          model.put("empresaSeleccionada", empresas.get(0).getNombre());
          model.put("periodoSeleccionado", periodos.get(0).getFecha());
        }
      }
      model.put("empresas", empresas);
      model.put("periodos", periodos);
    } catch (DbException e) {
      log.error(e.getMessage());
      model.put("messageError", "Se ha produdido un error al buscar los datos con los valores ingresados.");
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
      List<Periodo> periodos = empresaDb.obtenerPeriodos();
      model.put("periodos", periodos);
      model.put("empresas", empresas);
      model.put("empresaSeleccionada", empresas.get(0).getNombre());
      model.put("periodoSeleccionado", periodos.get(0).getFecha());
    } catch (DbException e) {
      log.error(e.getMessage());
      model.put("messageError", "No se ha podido traer los datos de la base de datos");
    }
    return new ModelAndView(model, "cuentas/listadoCuentas.hbs");
  }

  public static ModelAndView upload(Request req, Response res) {
    Map<String, Object> model = new HashMap<>();
    // Importante para mantener el usuario en pantalla
    Usuario usuarioLoggeado = req.session().attribute("currentUser");
    if (usuarioLoggeado == null) {
      return new ModelAndView(model, "login/login.hbs");
    }
    model.put("usuario", usuarioLoggeado);
    return new ModelAndView(model, "cuentas/upload.hbs");
  }

  public static ModelAndView saveFile(Request req, Response res) {
    Map<String, Object> model = new HashMap<>();
    // Importante para mantener el usuario en pantalla
    Usuario usuarioLoggeado = req.session().attribute("currentUser");
    if (usuarioLoggeado == null) {
      return new ModelAndView(model, "login/login.hbs");
    }
    model.put("usuario", usuarioLoggeado);

    req.attribute("org.eclipse.jetty.multipartConfig", new MultipartConfigElement("C:/tmp"));
    try {
      File dir = new File("/opt/uploads/");
      dir.mkdirs();
      final Part filePart = req.raw().getPart("archivo");
      try (InputStream inputStream = filePart.getInputStream()) {
        OutputStream outputStream = new FileOutputStream(dir.getAbsolutePath() + "/" + filePart.getSubmittedFileName());
        IOUtils.copy(inputStream, outputStream);
        outputStream.close();
        model.put("messageSuccess", "Archivo guardado correctamente.");
      } catch (IOException e) {
        log.error(e.getMessage());
        model.put("messageError", "No se ha podido copiar el archivo dentro del sistema.");
      }
      try {
        indicadorPrecalculadoService.precalcularIndicadores();
      } catch (ServiceException e) {
        log.error(e.getMessage());
        model.put("messageError", "Ocurrio un error al precalcular los indicadores.");
      }
    } catch (IOException e) {
      log.error(e.getMessage());
      model.put("messageError", "Error al obtener el archivo.");
    } catch (ServletException e) {
      log.error(e.getMessage());
      model.put("messageError", "Error con los Servlets.");
    }
    return new ModelAndView(model, "cuentas/upload.hbs");
  }

}
