package controllers;

import java.util.LinkedList;
import java.util.List;

import com.avaje.ebean.Ebean;

import models.Product;
import models.StockItem;
import models.Warehouse;
import play.*;
import play.mvc.*;
import views.html.*;

public class Application extends Controller {

    public static Result index() {
    	Warehouse warehouse = new Warehouse();
    	warehouse.name = "My Warehouse";

    	Product product = new Product();
    	product.name = "Stainless steel paperclips, small, 1000pcs";
    	product.ean = 1234L;
    	product.description = "1000 blue paperclips.";

    	StockItem item = new StockItem();
    	item.quantity = 15L;
    	item.product = product;

    	item.warehouse = warehouse;
    	warehouse.stockItems.add(item);

    	List<String> output = new LinkedList<String>();
    	output.add(String.format("My warehouse is called '%s'", warehouse));
    	output.add(String.format("It contains %d items", warehouse.stockItems.size()));
    	output.add(String.format("The first is: %s", warehouse.stockItems.get(0)));

    	product.save();
    	item.save();

         Product product2 = Ebean.find(Product.class, 1L);
//        Product product2 = Product.find().byId(1L);
        Logger.info(String.format("Product name: %s", product.name));
        Logger.info(String.format("StockItems: %d", product.stockItems.size()));
        
        List<StockItem> items = StockItem.find().findList();
        
//        return ok(items);

        return ok(index.render("Your new application is ready."));
    	// return ok(output.toString());
    }

}
