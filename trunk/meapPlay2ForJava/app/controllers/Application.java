package controllers;

import java.util.LinkedList;
import java.util.List;
import java.lang.StringBuilder;

import com.avaje.ebean.Ebean;

import models.Product;
import models.StockItem;
import models.Warehouse;
import play.*;
import play.mvc.*;
import views.html.*;

public class Application extends Controller {

    public static Result index() {
    	// Warehouse warehouse = new Warehouse();
    	// warehouse.name = "My Warehouse";

     //    Product product = null;
     //    StockItem item;
     //    for (long i = 100L; i < 1500; i += 100L) {
     //        product = new Product();
     //        product.name = "Stainless steel paperclips, small, 600pcs";
     //        product.ean = 1234L;
     //        product.description = "1000 blue paperclips.";

     //        item = new StockItem();
     //        item.quantity = i;
     //        item.product = product;

     //        item.warehouse = warehouse;
     //        warehouse.stockItems.add(item);

     //        List<String> output = new LinkedList<String>();
     //        output.add(String.format("My warehouse is called '%s'", warehouse));
     //        output.add(String.format("It contains %d items", warehouse.stockItems.size()));
     //        output.add(String.format("The first is: %s", warehouse.stockItems.get(0)));

     //    	product.save();
     //    	item.save();
     //        warehouse.save();
            
     //        Logger.info(String.format("Product name: %s", product.name));
     //        Logger.info(String.format("StockItems: %d", product.stockItems.size()));
     //    }

        // Product product2 = Ebean.find(Product.class, 1L);
//        Product product2 = Product.find().byId(1L);
        
//         List<StockItem> items;
//         // items = StockItem.find().findList();
//         items = StockItem.find()
//             .where()
//             .ge("quantity", 300)
//             .orderBy("quantity")
//             .setMaxRows(5)
//             .findList();

//         StringBuilder sb = new StringBuilder();
//         sb.append("StockItem list: ").append("\n");
//         for (StockItem temp : items) {
//             sb.append(temp.toString()).append("\n");
//         }
//         Logger.info(sb.toString());

// //        return ok(items);

//         return ok(index.render("Your new application is ready."));
//     	// return ok(output.toString());
        return redirect(routes.Products.listByWarehouseId(33));
    }

}
