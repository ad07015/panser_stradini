package lv.stradini.domain;

import java.util.List;

public class EmployeeProjectDetail {
	
	private Project project;
	private List<ProjectRole> projectRoles;
	
	public EmployeeProjectDetail(Project project, List<ProjectRole> projectRoles) {
		setProject(project);
		setProjectRoles(projectRoles);
	}
	
	public Project getProject() {
		return project;
	}
	
	public void setProject(Project project) {
		this.project = project;
	}
	
	public List<ProjectRole> getProjectRoles() {
		return projectRoles;
	}
	
	public void setProjectRoles(List<ProjectRole> projectRoles) {
		this.projectRoles = projectRoles;
	}
}
