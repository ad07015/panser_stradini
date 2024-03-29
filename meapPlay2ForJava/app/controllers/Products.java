package controllers;


import play.mvc.*;
import play.data.*;
import static play.data.Form.*;
import views.html.products.*;
import views.html.*;

import java.util.*;

import org.apache.commons.lang3.StringUtils;

import models.Product;
import models.StockItem;

public class Products extends Controller {

	private static final Form<Product> productForm = form(Product.class);

	public static Result list() {
		Set<Product> products = Product.findAll();
		return ok(productList.render(products));
	}

	public static Result listByWarehouseId(long warehouseId) {
//		List<StockItem> stockItems = StockItem.findByWarehouseId(warehouseId);
		List<StockItem> stockItems = StockItem.find()
			.where()
				.eq("warehouse.id", warehouseId)
			.findList();
		if (stockItems == null || stockItems.size() == 0) {
			return notFound(String.format("Warehouse with id %d not found", warehouseId));
		}
		// if (request().accept("text/plain")) {
		// 	return ok(StringUtils.join(stockItems, "\n"));
		// }
		return ok(product.render(stockItems));
	}

	public static Result showBlank() {
		return ok(show.render(productForm));
	}

	public static Result show(Long ean) {
		Product product = Product.findByEad(ean);
		if (product == null) {
			return notFound(String.format("Product with %s does not exist.", ean));
		} 
		return ok(show.render(productForm.fill(product)));
	}

	public static Result save() {
		// Form<Product> boundForm = productForm.bindFromRequest();
		// Product product = boundForm.get();
		// Product.add(product);
		// return ok(String.format("Saved product %s", product));

		// DynamicForm boundForm = form().bindFromRequest();
		// Product product = new Product(Long.parseLong(boundForm.get("ean")), boundForm.get("name"), boundForm.get("description"));
		// Product.add(product);
		// return ok(String.format("Saved product %s", product));

		Form<Product> boundForm = productForm.bindFromRequest();
		if(boundForm.hasErrors()) {
			flash("error", "Please correct the form below.");
			return badRequest(show.render(boundForm));
		}
		Product product = boundForm.get();
		Product.add(product);
		flash("success",
			String.format("Successfully added product %s", product));
		return redirect(routes.Products.list());
	}
}