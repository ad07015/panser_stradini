package lv.lu.events.constants;

/**
 * Put names for named JPQL queries here
 */
public final class JPQConst {

    public final class UserJpq {

        public static final String QUERY_GET_ID_BY_USERNAME = "User.getIdByUsername";
        public static final String QUERY_GET_USER_BY_USERNAME = "User.getUserByUsername";
        public static final String QUERY_GET_USER_BY_FACEBOOK_ID = "User.getUserByFacebookId";
        public static final String QUERY_GET_ALL_EXCLUDE_USERNAME = "User.getAllExcludeUsername";
    }

    public final class EventJpq {

        public static final String QUERY_GET_EVENT_BY_FACEBOOK_ID = "Event.getEventByFacebookId";
        public static final String QUERY_GET_EVENTS_BY_OWNER = "Event.getEventsByOwner";
    }

    public final class FriendshipJpq {

        public static final String QUERY_GET_FRIENDSHIP_REQUESTS = "Friendship.getFriendshipRequests";
        public static final String QUERY_GET_FRIENDSHIP_BY_TWO_USERS = "Friendship.getFriendshipByTwoUsers";
    }

    public final class InviteJpq {

        public static final String GET_USER_INVITES_BY_STATUS = "Invite.getUserInvitesByStatus";
        public static final String QUERY_GET_USER_INVITE_BY_EVENT = "Invite.getEventAndUserInvite";
    }

    private JPQConst() {
    }
}
