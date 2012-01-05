package lv.lu.events.impl.service;

import java.sql.Date;
import java.util.List;

import javax.persistence.NoResultException;
import javax.persistence.Query;
import lv.lu.events.constants.GeneralConst;
import lv.lu.events.constants.JPQConst;
import lv.lu.events.interfaces.CommonDAO;
import lv.lu.events.interfaces.service.InviteService;
import lv.lu.events.model.Event;
import lv.lu.events.model.Invite;

import lv.lu.events.model.InviteStatus;
import lv.lu.events.model.User;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

public class InviteServiceImpl implements InviteService {

    private Logger log = Logger.getLogger(InviteServiceImpl.class.getSimpleName());
    protected CommonDAO commonDAO;

    public InviteServiceImpl() {
    }

    @Autowired
    public InviteServiceImpl(CommonDAO commonDAO) {
        this.commonDAO = commonDAO;
    }

    @Override
    public void addInvitesToEvent(Event event, List<Invite> inviteList) {
        log.info(GeneralConst.LOG_SEPARATOR);
        log.info("Importing invites for event with facebookId " + event.getFacebookId());
        List<Invite> existingInviteList = event.getInvites();
        int invitesFoundOnFacebookCount = inviteList.size();
        int updatedCount = 0;
        int unchangedInviteCount = 0;
        for (Invite existingInvite : existingInviteList) {
            for (Invite newInvite : inviteList) {
                if (existingInvite.equals(newInvite)) {
                    if (!existingInvite.getStatus().equals(newInvite.getStatus())) {
                        updatedCount++;
                        existingInvite.setStatus(newInvite.getStatus());
                        existingInvite.setStatusUpdateDate(new Date(System.currentTimeMillis()));
                    } else {
                        unchangedInviteCount++;
                    }
                    inviteList.remove(newInvite);
                    break;
                }
            }
        }
        if (unchangedInviteCount == invitesFoundOnFacebookCount) {
            log.info("No new or updated invites found on Facebook for this event");
        }
        if (inviteList.size() > 0) {
            log.info(new StringBuffer().append("Adding [").append(inviteList.size()).append("] new invite(s) to the database").toString());
        }
        if (updatedCount > 0) {
            log.info(new StringBuffer().append("Updated [").append(updatedCount).append("] existing invite(s)").toString());
        }
        existingInviteList.addAll(inviteList);
        commonDAO.save(event);
    }

    public void setCommonDAO(CommonDAO commonDAO) {
        this.commonDAO = commonDAO;
    }

    private List<Invite> getUserInvitesByStatus(User user, InviteStatus status) {
        List<Invite> result = null;
        Query queryGetAttendingInvites = commonDAO.getEntityManager().createNamedQuery(JPQConst.InviteJpq.GET_USER_INVITES_BY_STATUS);
        queryGetAttendingInvites.setParameter("invited", user);
        queryGetAttendingInvites.setParameter("status", status);
        try {
            result = (List<Invite>) queryGetAttendingInvites.getResultList();
        } catch (NoResultException e) {
        }
        return result;
    }
    
    @Override
    public List<Invite> getUserAttendingInvites(User user) {
        return getUserInvitesByStatus(user, InviteStatus.ATTENDING);
    }

    @Override
    public List<Invite> getUserNotRepliedInvites(User user) {
        return getUserInvitesByStatus(user, InviteStatus.NOT_REPLIED);
    }

    @Override
    public List<Invite> getUserUnsureInvites(User user) {
        return getUserInvitesByStatus(user, InviteStatus.UNSURE);
    }

    @Override
    public List<Invite> getUserDeclinedInvites(User user) {
        return getUserInvitesByStatus(user, InviteStatus.DECLINED);
    }
    
    @Override
    public Invite getUserInviteByEvent(Event event, User user) {
        Invite result = null;
        Query queryGetAttendingInvites = commonDAO.getEntityManager().createNamedQuery(JPQConst.InviteJpq.QUERY_GET_USER_INVITE_BY_EVENT);
        queryGetAttendingInvites.setParameter("invited", user);
        queryGetAttendingInvites.setParameter("event", event);
        try {
            result = (Invite) queryGetAttendingInvites.getSingleResult();
        } catch (NoResultException e) {
        }
        return result;
    }

    @Override
    public Boolean updateInviteStatus(Invite invite, InviteStatus newStatus) {
        invite.setStatus(newStatus);
        commonDAO.save(invite);
        return Boolean.TRUE;
    }
}
