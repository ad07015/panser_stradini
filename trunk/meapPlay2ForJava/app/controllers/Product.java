package controllers;

import play.mvc.Controller;
import play.mvc.Result;
import views.html.*;

public class Product extends Controller {

	public static Result index() {
		return redirect(routes.Product.list(1));
	}

	public static Result list(Long page) {
	    return TODO;
	}

	public static Result details(Long ean) {
	    return TODO;
	}

	public static Result edit(Long ean) {
	    return TODO;
	}

	public static Result save(Long ean) {
	    return TODO;
	}
}