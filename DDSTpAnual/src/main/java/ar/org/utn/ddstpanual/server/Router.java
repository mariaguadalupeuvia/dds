package ar.org.utn.ddstpanual.server;

import ar.org.utn.ddstpanual.controller.CuentasController;
import ar.org.utn.ddstpanual.controller.HomeController;
import ar.org.utn.ddstpanual.controller.IndicadoresController;
import ar.org.utn.ddstpanual.controller.LogInController;
import ar.org.utn.ddstpanual.controller.MetodologiasController;
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
    Spark.get("/home", HomeController::home, engine);
    Spark.get("/login", LogInController::login, engine);
    Spark.post("/login", LogInController::ingresar, engine);
    Spark.get("/logout", LogInController::logout, engine);

    Spark.get("/metodologias", MetodologiasController::listar, engine);
    Spark.get("/metodologias/ejecutar", MetodologiasController::ejecutar, engine);
    Spark.post("/metodologias/crear", MetodologiasController::crear);

    Spark.get("/indicadores", IndicadoresController::listar, engine);
    Spark.post("/indicadores", IndicadoresController::crear);
    Spark.get("/indicadores/ejecutar", IndicadoresController::ejecutar, engine);
    Spark.get("/indicadores/nuevo", IndicadoresController::nuevo, engine);

    Spark.get("/cuentas", CuentasController::listar, engine);
    Spark.get("/cuentas/ejecutar", CuentasController::ejecutar, engine);
  }

}
