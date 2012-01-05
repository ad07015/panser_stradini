package lv.lu.events.impl.facebook;

import java.util.Enumeration;
import java.util.Hashtable;
import java.util.LinkedList;
import java.util.List;

import lv.lu.events.constants.GeneralConst;
import lv.lu.events.interfaces.DataImportProcessor;
import lv.lu.events.interfaces.service.EventService;
import lv.lu.events.interfaces.service.FriendshipService;
import lv.lu.events.interfaces.service.InviteService;
import lv.lu.events.interfaces.service.JSONService;
import lv.lu.events.interfaces.service.UserService;
import lv.lu.events.model.Event;
import lv.lu.events.model.Invite;
import lv.lu.events.model.User;

import org.apache.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class FacebookDataImportProcessor implements DataImportProcessor {
	private Logger log = Logger.getLogger(FacebookDataImportProcessor.class.getSimpleName());
	
	private FriendshipService friendshipService;
	private InviteService inviteService;
	private EventService eventService;
	private JSONService jsonService;
	private UserService userService;
	private String accessToken;
	private User currentUser;
	private boolean importFriendships;
	private boolean importEvents;
	private boolean importInvites;
	
	@Override
	public void importData() {
		currentUser = userService.getUserByAccessToken(accessToken);
		if (currentUser == null) {
			log.error("Invalid or expired access token");
		} else {
			if (importFriendships)
				importFriendships();
			if (importEvents)
				importEvents();
			if (importInvites)
				importInvites();
		}
	}
	
	private void importFriendships() {
		log.info(GeneralConst.LOG_SEPARATOR);
		log.info("Started friendship import");
		JSONArray jsonFriends = jsonService.getFriendsByAccessToken(accessToken);
		
		// Get List of all users except the logged in one
		List<User> otherUsersFromDB = userService.getAllUsersFromDB();
		otherUsersFromDB.remove(currentUser);
		
		// Discard users that are not persisted in events database
		List<String> friendsFacebookIdList = getFriendsFacebookIdList(jsonFriends);
		log.info("Current user has [" + friendsFacebookIdList.size() + "] friend(s) on Facebook");
		List<User> friendList = new LinkedList<User>();
		for (User user : otherUsersFromDB) {
			if (friendsFacebookIdList.contains(user.getFacebookId())) {
				friendList.add(user);
			}
		}
		log.info(friendList.size() + "/" + friendsFacebookIdList.size() + " friends are already persisted in the database");
		log.info("Creating friendships in the database...");
		// Create Friendship relationships between the current user and users,
		// that are found both on facebook and in evetns database
		for (User friend : friendList) {
			friendshipService.addFriend(currentUser, friend);
		}
		log.info("Friendship import completed!");
		log.info(GeneralConst.LOG_SEPARATOR);
	}
	
	private void importEvents() {
		log.info(GeneralConst.LOG_SEPARATOR);
		log.info("Started event import");
		User eventOwner = currentUser;
		JSONArray eventsArray = jsonService.getUserEventsByAccessToken(accessToken);
		int eventsCount = eventsArray.length();
		log.info("Current user [" + eventOwner.getFullName() + "] is the owner of [" + eventsCount + "] event(s) on Facebook");
		int newEventCount = 0;
		for (int i = 0; i < eventsCount; i++) {
			try {
				JSONObject jsonEvent = eventsArray.getJSONObject(i);
				Event newEvent = jsonService.getEventFromJSON(jsonEvent, eventOwner);
				Event existingEvent = eventService.getEventByFacebookId(newEvent.getFacebookId());
				// Check if event already exists
				if (existingEvent == null) {
					newEventCount++;
					eventService.addEvent(newEvent);
				} else if (existingEvent.equals(newEvent)){
					eventService.updateEvent(existingEvent, newEvent);
				}
			} catch (JSONException e) {
				log.error("Failed to get an element from an event JSON array");
				log.error(e);
			}
		}
		if (newEventCount > 0) {
			log.info(new StringBuffer().append("Adding [")
					.append(newEventCount)
					.append("] new event(s) to the database").toString());
		} else {
			log.info("All events for current user [" + eventOwner.getFullName() + "] are already persisted in the database");
		}
		log.info("Events import completed!");
		log.info(GeneralConst.LOG_SEPARATOR);
	}
	
	private void importInvites() {
		log.info(GeneralConst.LOG_SEPARATOR);
		log.info("Started invites import");
		List<Event> eventList = eventService.getAllEventsFromDB();
		if (eventList.size() > 0) {
			log.info("Found [" + eventList.size() + "] events in the database");
		} else {
			log.info("No events found in the database");
		}
		for (Event event : eventList) {
			List<Invite> eventInvitesList = new LinkedList<Invite>();
			JSONObject invites = jsonService.getEventInvites(event.getFacebookId(), accessToken);
			Hashtable<String, String> invited = jsonService.getInvitesFromJSON(invites);
			Enumeration<String> invitedIds = invited.keys();
			String facebookId;
			String status;
			while (invitedIds.hasMoreElements()) {
				facebookId = invitedIds.nextElement();
				status = invited.get(facebookId);

				User invitedUser = null;
				invitedUser = userService.getUserByFacebookId(facebookId);
				if (invitedUser != null) {
					Invite invite = jsonService.getInvite(event, invitedUser, status);
					eventInvitesList.add(invite);
				}
			}
			inviteService.addInvitesToEvent(event, eventInvitesList);
		}
		log.info("Invite import finished");
		log.info(GeneralConst.LOG_SEPARATOR);
	}

	private List<String> getFriendsFacebookIdList(JSONArray jsonFriends) {
		List<String> result = new LinkedList<String>();
		JSONObject friendData = null;
		for (int i = 0; i < jsonFriends.length(); i++) {
			try {
				friendData = jsonFriends.getJSONObject(i);
				result.add(friendData.getString("id"));
			} catch (JSONException e) {
				log.error("Failed to perform JSON operation");
				log.error(e);
			}
		}

		return result;
	}

	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}

	public void setFriendshipService(FriendshipService friendshipService) {
		this.friendshipService = friendshipService;
	}

	public void setInviteService(InviteService inviteService) {
		this.inviteService = inviteService;
	}

	public void setEventService(EventService eventService) {
		this.eventService = eventService;
	}

	public void setJsonService(JSONService jsonService) {
		this.jsonService = jsonService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	public void setImportFriendships(boolean importFriendships) {
		this.importFriendships = importFriendships;
	}

	public void setImportEvents(boolean importEvents) {
		this.importEvents = importEvents;
	}

	public void setImportInvites(boolean importInvites) {
		this.importInvites = importInvites;
	}
}