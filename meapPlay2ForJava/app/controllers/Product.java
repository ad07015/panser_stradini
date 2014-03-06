package controllers;

public class Product extends Controller {

	public static Result index() {
		return redirect(routes.Product.list(1));
	}

	public static Result list(String page) {
	    return TODO;
	}

	public static Result details(String ean) {
	    return TODO;
	}

	public static Result edit(String ean) {
	    return TODO;
	}

	public static Result save(String ean) {
	    return TODO;
	}
}