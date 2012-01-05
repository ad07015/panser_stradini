package lv.lu.events.impl.xml;

import java.util.LinkedList;
import java.util.List;

import lv.lu.events.model.User;

/**
 * Users data holder for Digester XML parser.
 */
public class UsersHolder {

	private List<User> users;

	public UsersHolder(){
		users = new LinkedList<User>();
	}
	
	public void addUser(User user) {
		users.add(user);
	}

	public List<User> getUsers() {
		return users;
	}
}
