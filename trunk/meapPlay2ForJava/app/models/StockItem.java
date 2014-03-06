package models;

import javax.persistence.Id;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;

import play.db.ebean.Model;

@Entity
public class StockItem extends Model {

	@Id
	public Long id;

	@ManyToOne
	public Warehouse warehouse;

	@ManyToOne
	public Product product;

	public Long quantity;

	public static Finder<Long, StockItem> find() {
		return new Finder<Long, StockItem>(Long.class, StockItem.class);
	}

	public String toString() {
		return String.format("StockItem %d - %dx; product %s; warehouse %s", id, quantity,
				product == null ? null : product, warehouse);
	}
}
