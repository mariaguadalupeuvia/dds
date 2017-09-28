package ar.org.utn.ddstpanual.server;


import ar.org.utn.ddstpanual.controller.MetodologiasController;
import ar.org.utn.ddstpanual.spark.utils.BooleanHelper;
import ar.org.utn.ddstpanual.spark.utils.HandlebarsTemplateEngineBuilder;

import spark.Spark;
import spark.template.handlebars.HandlebarsTemplateEngine;


public class Router {

	public static void configure() {
		HandlebarsTemplateEngine engine = HandlebarsTemplateEngineBuilder
				.create()
				.withDefaultHelpers()
				.withHelper("isTrue", BooleanHelper.isTrue)
				.build();

		Spark.staticFiles.location("/public");
		
		MetodologiasController metodologiasController = new MetodologiasController();
		
		//Spark.get("/", HomeController::home, engine);
		Spark.get("/metodologias", metodologiasController::listar, engine);
	//	Spark.get("/metodologias/new", metodologiasController::nuevo, engine);
		Spark.get("/metodologias/:id", metodologiasController::mostrar, engine);
		Spark.get("/metodologias/:id/:periodo/ejecutar", metodologiasController::ejecutar, engine);
	//	Spark.post("/metodologias", metodologiasController::crear);
	}

}