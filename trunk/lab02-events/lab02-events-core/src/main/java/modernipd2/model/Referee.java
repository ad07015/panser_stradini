/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package modernipd2.model;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

/**
 *
 * @author Andrejs Daško ad07015; Dmitrijs Ivanovs di07001
 */

@SuppressWarnings("serial")
@NamedQueries({
    @NamedQuery(name = "GetRefereeByFirstNameAndLastName",
		query = "SELECT r FROM Referee r WHERE r.firstName = :firstName AND r.lastName = :lastName")
})
@Entity
public class Referee implements PersistentEntity, Serializable {

    private Long id;
    
    private String firstName;
    private String lastName;

    @Id
    @GeneratedValue
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Override
    public String toString() {
        StringBuffer sb = new StringBuffer();
        sb.append("Vards: ").append(this.firstName);
        sb.append(" Uzvards: ").append(this.lastName);
        return sb.toString();
    }
}
