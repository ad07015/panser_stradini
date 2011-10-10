package lv.stradini.domain.old;

public class Project {
	
	private long ID;
	private String name;
	private String description;
	private String client;
	
	public Project(long id, String name, String description, String client) {
		setID(id);
		setName(name);
		setDescription(description);
		setClient(client);
	}
	
	public long getID() {
		return ID;
	}
	
	public void setID(long id) {
		this.ID = id;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	
	public String getClient() {
		return client;
	}
	
	public void setClient(String client) {
		this.client = client;
	}
}