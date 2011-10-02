package lv.stradini.hibernate.chapter1;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.TableGenerator;

@Entity
public class Employee {

	private int epmId;
	private String empName;

	@Id //Now a primary key
	@Column(name="EMPLOYEE_PK")
	@TableGenerator(name="emppk", table="emppktb", pkColumnName="empkey",
		pkColumnValue="empvalue", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.TABLE, generator="emppk")
	public int getEpmId() {
		return epmId;
	}
	public void setEpmId(int epmId) {
		this.epmId = epmId;
	}
	public String getEmpName() {
		return empName;
	}
	public void setEmpName(String empName) {
		this.empName = empName;
	}
}
