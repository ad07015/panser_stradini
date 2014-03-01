package models;

import java.util.ArrayList;
import java.util.List;

public class Warehouse {
	public String name;
	public List<StockItem> stockItems = new ArrayList<StockItem>();
	
	public String toString() {
		return name;
	}
}
