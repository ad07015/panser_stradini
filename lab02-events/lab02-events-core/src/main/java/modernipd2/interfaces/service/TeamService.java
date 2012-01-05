/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package modernipd2.interfaces.service;

import modernipd2.model.Team;

/**
 *
 * @author Andrejs Daško ad07015; Dmitrijs Ivanovs di07001
 */
public interface TeamService {
    
    public Team getTeamByName(String teamName);
}
