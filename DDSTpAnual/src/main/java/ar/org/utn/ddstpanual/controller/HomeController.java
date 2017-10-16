package ar.org.utn.ddstpanual.controller;

import spark.ModelAndView;
import spark.Request;
import spark.Response;

public class HomeController {
	public static ModelAndView home(Request req, Response res){
		return new ModelAndView(null, "home/home.hbs");
	}
	
}
