package ar.org.utn.ddstpanual.controller;

import java.util.HashMap;

import ar.org.utn.ddstpanual.model.Usuario;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

public class HomeController {
	public static ModelAndView home(Request req, Response res){
	    HashMap<String, Object> model = new HashMap<>();
	    Usuario usuarioLoggeado = req.session().attribute("currentUser");
	    if(usuarioLoggeado == null){
	      return new ModelAndView(model, "login/login.hbs");
	    }
	    model.put("usuario", usuarioLoggeado);
		return new ModelAndView(model, "home/home.hbs");
	}
	
}
