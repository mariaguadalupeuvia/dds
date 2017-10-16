package ar.org.utn.ddstpanual.server;

import ar.org.utn.ddstpanual.controller.HomeController;
import ar.org.utn.ddstpanual.controller.LogInController;
import ar.org.utn.ddstpanual.spark.utils.BooleanHelper;
import ar.org.utn.ddstpanual.spark.utils.HandlebarsTemplateEngineBuilder;
import spark.Spark;
import spark.template.handlebars.HandlebarsTemplateEngine;

public class Router {

  public static void configure() {
    HandlebarsTemplateEngine engine =
        HandlebarsTemplateEngineBuilder.create().withDefaultHelpers().withHelper("isTrue", BooleanHelper.isTrue).build();

    Spark.staticFiles.location("/public");

    Spark.get("/", HomeController::home, engine);
    Spark.get("/login", LogInController::login, engine);
    Spark.post("/login", LogInController::ingresar, engine);
  }

}
