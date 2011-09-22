package lv.stradini.domain.old;


public class Role {
	
	private long ID;
	private String role;
	
	public Role(long id, String role) {
		setID(id);
		setRole(role);
	}
	
	public long getID() {
		return ID;
	}
	
	public void setID(long id) {
		this.ID = id;
	}
	
	public String getRole() {
		return role;
	}
	
	public void setRole(String role) {
		this.role = role;
	}
}
