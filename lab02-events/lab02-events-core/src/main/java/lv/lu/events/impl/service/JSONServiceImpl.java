package lv.lu.events.impl.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.Charset;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Hashtable;

import lv.lu.events.impl.HTTPClientImpl;
import lv.lu.events.interfaces.HTTPClient;
import lv.lu.events.interfaces.service.JSONService;
import lv.lu.events.model.Event;
import lv.lu.events.model.Invite;
import lv.lu.events.model.InviteStatus;
import lv.lu.events.model.PrivateEvent;
import lv.lu.events.model.PublicEvent;
import lv.lu.events.model.User;

import org.apache.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class JSONServiceImpl implements JSONService {

    private static Logger log = Logger.getLogger(JSONServiceImpl.class.getSimpleName());

    @Override
    public JSONObject getUserByAccessToken(String accessToken) {
        JSONObject json = null;
        try {
            json = readJsonFromUrl("https://graph.facebook.com/me?access_token=" + accessToken);
        } catch (IOException e) {
            log.error(e);
        } catch (JSONException e) {
            log.error(e);
        }
        return json;
    }

    @Override
    public JSONArray getFriendsByAccessToken(String accessToken) {
        JSONObject json = null;
        JSONArray result = null;
        try {
            json = readJsonFromUrl("https://graph.facebook.com/me/friends?access_token=" + accessToken);
            result = json.getJSONArray("data");
        } catch (IOException e) {
            log.error(e);
        } catch (JSONException e) {
            log.error(e);
        }
        return result;
    }

    @Override
    public JSONArray getUserEventsByAccessToken(String accessToken) {
        JSONObject json = null;
        JSONArray result = null;
        try {
            json = readJsonFromUrl("https://graph.facebook.com/me/events?access_token=" + accessToken);
            result = json.getJSONArray("data");
        } catch (IOException e) {
            log.error(e);
        } catch (JSONException e) {
            log.error(e);
        }
        return result;
    }

    @Override
    public JSONObject getEventInvites(String facebookId, String accessToken) {
        JSONObject json = null;
        try {
            json = readJsonFromUrl("https://graph.facebook.com/" + facebookId + "/INVITED/?access_token=" + accessToken);
        } catch (IOException e) {
            log.error(e);
        } catch (JSONException e) {
            log.error(e);
        }
        return json;
    }

    @Override
    public Invite getInvite(Event event, User invitedUser, String status) {
        InviteStatus currentStatus = null;
        if (status.equals("not_replied")) {
            currentStatus = InviteStatus.NOT_REPLIED;
        } else if (status.equals("attending")) {
            currentStatus = InviteStatus.ATTENDING;
        } else if (status.equals("declined")) {
            currentStatus = InviteStatus.DECLINED;
        } else if (status.equals("unsure")) {
            currentStatus = InviteStatus.UNSURE;
        }

        Date currentDate = new Date(System.currentTimeMillis());

        Invite invite = new Invite();
        invite.setCreatedDate(currentDate);
        invite.setCreator(event.getOwner());
        invite.setEvent(event);
        invite.setStatus(currentStatus);
        invite.setStatusUpdateDate(currentDate);
        invite.setInvited(invitedUser);

        return invite;
    }

    @Override
    public Event getEventFromJSON(JSONObject jsonEvent, User eventOwner) {
        Event event;
        String privacy;
        String facebookId = null;
        String name = null;
        String description = null;
        Date startTime = null;
        Date endTime = null;
        String location = null;
        try {
            privacy = jsonEvent.getString("privacy");
        } catch (JSONException e) {
            privacy = "OPEN";
        }
        if (privacy.equals("SECRET")) {
            event = new PrivateEvent();
            // TODO: get access code
        } else {
            event = new PublicEvent();
        }
        try {
            facebookId = jsonEvent.getString("id");
            name = jsonEvent.getString("name");
            description = getJSONStringProperty(jsonEvent, "description");
            location = getJSONStringProperty(jsonEvent, "location");
            startTime = getDateFromFacebookString(jsonEvent.getString("start_time"));
            endTime = getDateFromFacebookString(jsonEvent.getString("end_time"));
        } catch (JSONException e1) {
            log.error("Failed to parse get event property from JSON object", e1);
        }
        event.setFacebookId(facebookId);
        event.setName(name);
        event.setLocation(location);
        event.setEndTime(endTime);
        event.setStartTime(startTime);
        event.setDescription(description);
        // TODO: contact phone
        //			newEvent.setContactPhone(contactPhone);
        event.setOwner(eventOwner);
        return event;
    }

    private String getJSONStringProperty(JSONObject jsonObject, String propertyName) {
        String result = null;
        try {
            result = jsonObject.getString(propertyName);
        } catch (JSONException e1) {
        }
        return result;
    }

    private Date getDateFromFacebookString(String dateString) {
        Date result = null;
        DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
        try {
            result = (Date) formatter.parse(dateString);
        } catch (ParseException e) {
            log.error("Failed to parse date from String", e);
        }
        return result;
    }

    private String readAll(Reader rd) throws IOException {
        StringBuilder sb = new StringBuilder();
        int cp;
        while ((cp = rd.read()) != -1) {
            sb.append((char) cp);
        }
        return sb.toString();
    }

    private JSONObject readJsonFromUrl(String url) throws IOException, JSONException {
        HTTPClient httpClient = new HTTPClientImpl();
        InputStream is = httpClient.download(url);
        try {
            BufferedReader rd = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
            String jsonText = readAll(rd);
            JSONObject json = new JSONObject(jsonText);
            return json;
        } finally {
            is.close();
        }
    }

    @Override
    public Hashtable<String, String> getInvitesFromJSON(JSONObject invites) {
        Hashtable<String, String> invited = null;
        try {
            invited = new Hashtable<String, String>();
            JSONArray invitesData = invites.getJSONArray("data");
            int invitedFriendsCount = invitesData.length();
            for (int i = 0; i < invitedFriendsCount; i++) {
                JSONObject inviteData = invitesData.getJSONObject(i);
                invited.put(inviteData.getString("id"), inviteData.getString("rsvp_status"));
            }
        } catch (JSONException e) {
            log.error("Failed to get user facebookId to invite status map from JSON object", e);
        }
        return invited;
    }
}
