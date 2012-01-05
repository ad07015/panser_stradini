package lv.lu.events.model;

import javax.persistence.Entity;

@SuppressWarnings("serial")
@Entity

public class PrivateEvent extends Event {

	private String accessCode;

	public String getAccessCode() {
		return accessCode;
	}

	public void setAccessCode(String accessCode) {
		this.accessCode = accessCode;
	}
}
