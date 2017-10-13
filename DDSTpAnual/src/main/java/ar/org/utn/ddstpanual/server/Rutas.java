package ar.org.utn.ddstpanual.server;


import ar.org.utn.ddstpanual.controller.IndicadoresController;
import ar.org.utn.ddstpanual.controller.MenuPrincipalController;
import ar.org.utn.ddstpanual.controller.MetodologiasController;
import ar.org.utn.ddstpanual.spark.utils.BooleanHelper;
import ar.org.utn.ddstpanual.spark.utils.HandlebarsTemplateEngineBuilder;

import spark.Spark;
import spark.template.handlebars.HandlebarsTemplateEngine;


public class Rutas {

	public static void configure() {
		HandlebarsTemplateEngine engine = HandlebarsTemplateEngineBuilder
				.create()
				.withDefaultHelpers()
				.withHelper("isTrue", BooleanHelper.isTrue)
				.build();

		Spark.staticFiles.location("/public");
		MenuPrincipalController menu = new MenuPrincipalController();
		Spark.get("/", menu::home, engine);
		MetodologiasController metodologiasController = new MetodologiasController();
		Spark.get("/metodologias", metodologiasController::listar, engine);
		//Spark.get("/metodologias/:id", metodologiasController::mostrar, engine);
		Spark.get("/metodologias/ejecutar", metodologiasController::ejecutar, engine);
		//Spark.get("/metodologias/:id/:periodo/ejecutar", metodologiasController::ejecutar, engine);
		Spark.post("/metodologias/crear",  metodologiasController::crear);
		Spark.get("/metodologias/crear",  metodologiasController::crear, engine);
		
		IndicadoresController indicadoresController = new IndicadoresController();
		Spark.get("/indicadores/ejecutar", indicadoresController::ejecutar, engine);
		Spark.get("/indicadores", indicadoresController::listar, engine);
	}

}