/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package modernipd2.impl.service;

import javax.persistence.Query;
import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import modernipd2.interfaces.service.TeamService;
import modernipd2.model.Team;
import modernipd2.persistance.AbstractDAOImpl;
import modernipd2.persistance.CommonDAO;

/**
 *
 * @author Andrejs Daško ad07015; Dmitrijs Ivanovs di07001
 */
public class TeamServiceImpl extends AbstractDAOImpl implements TeamService {

    protected CommonDAO commonDAO;

    @Override
    public Team getTeamByName(String teamName) {
        if (teamName == null) {
            return null;
        }

        Team team = null;
        try {
            Query query = getEntityManager().createNamedQuery("GetTeamByName");
            query.setParameter("teamName", teamName);
            team = (Team) query.getSingleResult();
        } catch (NoResultException nre) {
            // no such team
            System.out.println("Team with teamname '" + teamName + "' does not exist");
        } catch (NonUniqueResultException nure) {
            // multiple users with such name
            System.out.println("There are multiple teams with teamname '" + teamName + "' in database");
        }
        return team;
    }

    public void setCommonDAO(CommonDAO commonDAO) {
        this.commonDAO = commonDAO;
    }
}
