package models;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.ManyToMany;

import play.db.ebean.Model;

import java.util.ArrayList;
import java.util.List;
import static javax.persistence.CascadeType.ALL;

@Entity
public class Warehouse extends Model{
	public String name;

	@Id
	public Long id;

	@OneToOne
	public Address address;

	@OneToMany(mappedBy="warehouse", cascade=ALL)
	public List<StockItem> stockItems = new ArrayList<StockItem>();

	@ManyToMany
	public List<Employee> employees = new ArrayList<Employee>();
	
	public String toString() {
		return String.format("Warehouse | id: %d, name: %s", id, name);
	}
}
