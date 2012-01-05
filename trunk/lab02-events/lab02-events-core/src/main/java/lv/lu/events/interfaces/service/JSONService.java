package lv.lu.events.interfaces.service;

import java.util.Hashtable;

import lv.lu.events.model.Event;
import lv.lu.events.model.Invite;
import lv.lu.events.model.User;

import org.json.JSONArray;
import org.json.JSONObject;

public interface JSONService {
	
	public Event getEventFromJSON(JSONObject jsonEvent, User eventOwner);
	public Invite getInvite(Event event, User invitedUser, String status);

	public JSONObject getEventInvites(String facebookId, String accessToken);
	public JSONArray getUserEventsByAccessToken(String accessToken);
	public JSONArray getFriendsByAccessToken(String accessToken);
	public JSONObject getUserByAccessToken(String accessToken);
	public Hashtable<String, String> getInvitesFromJSON(JSONObject invites);
}
