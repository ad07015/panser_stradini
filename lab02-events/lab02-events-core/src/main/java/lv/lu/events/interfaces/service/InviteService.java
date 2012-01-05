package lv.lu.events.interfaces.service;

import java.util.List;

import lv.lu.events.model.Event;
import lv.lu.events.model.Invite;
import lv.lu.events.model.InviteStatus;
import lv.lu.events.model.User;

public interface InviteService {

    public void addInvitesToEvent(Event event, List<Invite> inviteList);
    
    public List<Invite> getUserAttendingInvites(User user);
    
    public List<Invite> getUserNotRepliedInvites(User user);
    
    public List<Invite> getUserUnsureInvites(User user);
    
    public List<Invite> getUserDeclinedInvites(User user);
    
    public Invite getUserInviteByEvent(Event event, User user);
    
    public Boolean updateInviteStatus(Invite invite, InviteStatus newStatus);
}
