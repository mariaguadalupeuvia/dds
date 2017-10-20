package ar.org.utn.ddstpanual.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import ar.org.utn.ddstpanual.db.EmpresaDb;
import ar.org.utn.ddstpanual.db.IndicadorDb;
import ar.org.utn.ddstpanual.db.impl.EmpresaDbImpl;
import ar.org.utn.ddstpanual.db.impl.IndicadorDbImpl;
import ar.org.utn.ddstpanual.exception.ArbolException;
import ar.org.utn.ddstpanual.exception.DbException;
import ar.org.utn.ddstpanual.model.Empresa;
import ar.org.utn.ddstpanual.model.FormulaIndicador;
import ar.org.utn.ddstpanual.model.Indicador;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

public class IndicadoresController {
	private static EmpresaDb empresaDb = new EmpresaDbImpl();
	private static IndicadorDb indicadorDb = new IndicadorDbImpl();

	public static ModelAndView ejecutar(Request req, Response res) throws DbException, ArbolException {
		String periodoSeleccionado = req.queryParams("periodoSeleccionado");
		Empresa empresa = empresaDb.obtenerEmpresa(req.queryParams("empresaSeleccionada"));
		Map<String, List<FormulaIndicador>> model = new HashMap<>();
		double monto;
		List<FormulaIndicador> indicadoresEvaluados = new ArrayList<>();

		try {
			for (Indicador ind : indicadorDb.obtenerIndicadores()) {
				monto = ind.ejecutarIndicador(periodoSeleccionado, empresa);
				if (monto >= 0)
					indicadoresEvaluados.add(new FormulaIndicador(ind.getNombre(), periodoSeleccionado, monto));
			}

		} catch (DbException e) {
			throw new DbException("No se ha podido traer los datos de la base de datos");
		} catch (ArbolException e) {
			throw new ArbolException("Ha ocurrido un error al calcular los indicadores");
		}
		model.put("indicadoresEvaluados", indicadoresEvaluados);

		return new ModelAndView(model, "indicadores/indicadorEvaluado.hbs");
	}

	public static ModelAndView listar(Request req, Response res) throws DbException {
		List<Empresa> empresas = empresaDb.obtenerEmpresas();

		Map<String, List<Empresa>> model = new HashMap<>();
		model.put("empresas", empresas);
		return new ModelAndView(model, "indicadores/listadoIndicadores.hbs");
	}

	public static ModelAndView nuevo(Request req, Response res) {
		return new ModelAndView(null, "indicadores/nuevo.hbs");
	}

	public static ModelAndView crear(Request req, Response res) throws DbException {
		indicadorDb.guardarIndicador(new Indicador(req.queryParams("nombre"), req.queryParams("formula")));
		res.redirect("/indicadores/nuevo");
		return null;
	}

	public List<Indicador> obtenerIndicadores() throws DbException {
		return indicadorDb.obtenerIndicadores();
	}
}
