package models;

import java.util.Set;
import java.util.HashSet;

public class Product {

    public interface All {}

	public Long ean;
	public String name;
	public String description;

	private static Set<Product> products;
	static {
		products = new HashSet<Product>();
		products.add(new Product(1111111111111L, "Paperclips 1",
			"Paperclips description 1"));
		products.add(new Product(2222222222222L, "Paperclips 2",
			"Paperclips description "));
		products.add(new Product(3333333333333L, "Paperclips 3",
			"Paperclips description 3"));
		products.add(new Product(4444444444444L, "Paperclips 4",
			"Paperclips description 4"));
		products.add(new Product(5555555555555L, "Paperclips 5",
			"Paperclips description 5"));
	}

	public Product() {}

	public Product(Long ean, String name, String description) {
		this.ean = ean;
		this.name = name;
		this.description = description;
	}

	public static Set<Product> findAll() {
		return new HashSet<Product>(products);
	}

	public static Product findByEad(Long ean) {
		for (Product candidate : products) {
			if (candidate.ean.equals(ean)) {
				return candidate;
			}
		}
		return null;
	}

	public static Set<Product> findByName(String term) {
		final Set<Product> results = new HashSet<Product>();
		for (Product candidate : products) {
			if (candidate.name.toLowerCase().contains(term.toLowerCase())) {
				results.add(candidate);
			}
		}
		return results;
	}

	public static boolean remove(Product product) {
		return products.remove(product);
	}

	public static void add(Product product) {
		products.add(product);
	}

	public String toString() {
		return String.format("%s - %s", ean, name);
	}
}