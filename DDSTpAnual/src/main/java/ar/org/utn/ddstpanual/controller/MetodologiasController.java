package ar.org.utn.ddstpanual.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import ar.org.utn.ddstpanual.db.EmpresaDb;
import ar.org.utn.ddstpanual.db.MetodologiaDb;
import ar.org.utn.ddstpanual.db.impl.EmpresaDbImpl;
import ar.org.utn.ddstpanual.db.impl.MetodologiaDbImpl;
import ar.org.utn.ddstpanual.exception.ArbolException;
import ar.org.utn.ddstpanual.exception.DbException;
import ar.org.utn.ddstpanual.model.Empresa;
import ar.org.utn.ddstpanual.model.Periodo;
import ar.org.utn.ddstpanual.model.metodologia.Condicion;
import ar.org.utn.ddstpanual.model.metodologia.Metodologia;
import db.FixtureDB;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

public class MetodologiasController {
	private static MetodologiaDb metodologiaDb = new MetodologiaDbImpl();
	private static EmpresaDb empresaDb = new EmpresaDbImpl();

	public static ModelAndView listar(Request req, Response res) throws DbException {
		List<Metodologia> metodologias = metodologiaDb.obtenerMetodologias();

		Map<String, List<Metodologia>> model = new HashMap<>();
		model.put("metodologias", metodologias);
		return new ModelAndView(model, "metodologias/listado.hbs");
	}

	public static ModelAndView ejecutar(Request req, Response res) throws ArbolException, DbException {
		String metodologiaSeleccionada = req.queryParams("metodologiaSeleccionada");
		String periodoSeleccionado = req.queryParams("periodoSeleccionado");
		Metodologia metodologia = metodologiaDb.obtenerMetodologia(metodologiaSeleccionada);
		List<Empresa> empresas = metodologia.ejecutarMetodologia(empresaDb.obtenerEmpresas(),
				new Periodo(periodoSeleccionado));
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
