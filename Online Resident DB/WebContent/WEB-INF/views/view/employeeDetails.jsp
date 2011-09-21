<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link type='text/css' rel='stylesheet' href='/resdb/main.css' />

<script language="javascript" type="text/javascript">
function addSkill()
{
  document.addSkillForm.submit()
}

function deleteEmployeeSkill(eSID)
{
	var con
	con = confirm('Are you sure you want to remove this skill?')
	if (con)
	{
  		document.deleteSkillForm.employeeSkillID.value=eSID
		document.deleteSkillForm.submit()
	}
}

function updateEmployeeSkill(employeeSkillID)
{
	document.updateSkillForm.employeeSkillID.value = employeeSkillID
	document.updateSkillForm.submit()
}

function addProjectRole(projectID)
{
	document.addProjectRoleForm.projectID.value = projectID 
	document.addProjectRoleForm.submit()
}

function deleteProjectRole(projectRoleID)
{
	var con
	con = confirm('Are you sure you want to remove this role?')
	if (con)
	{
  		document.deleteProjectRoleForm.projectRoleID.value=projectRoleID
		document.deleteProjectRoleForm.submit()
	}
}

function updateProjectRole(projectRoleID)
{
	document.updateProjectRoleForm.projectRoleID.value = projectRoleID
	document.updateProjectRoleForm.submit()
}
</script>

<title>Employee Details</title>
</head>

<body>
<form name="addSkillForm" action="/resdb/add/employeeSkill.htm" method="post">
	<input type='hidden' name="employeeID" value="${employee.ID}" >
</form>
<form name="updateSkillForm" action="/resdb/update/employeeSkill.htm" method="post">
	<input type="hidden" name="employeeSkillID" >
</form>
<form name="deleteSkillForm" action="/resdb/view/employeeDetails.htm" method="post">
	<input type="hidden" name="deleteEmployeeSkill" />
	<input type="hidden" name="employeeSkillID" />
	<input type="hidden" name="employeeID" value="${employee.ID}" />
</form>
<form name="addProjectRoleForm" action="/resdb/add/projectRole.htm" method="post">
	<input type="hidden" name="employeeID" value="${employee.ID}" >
	<input type="hidden" name="projectID" >
</form>
<form name="deleteProjectRoleForm" action="/resdb/view/employeeDetails.htm" method="post">
	<input type="hidden" name="deleteProjectRole" />
	<input type="hidden" name="projectRoleID" />
	<input type="hidden" name="employeeID" value="${employee.ID}" />
</form>
<form name="updateProjectRoleForm" action="/resdb/update/projectRole.htm" method="post">
	<input type="hidden" name="projectRoleID" >
	<input type="hidden" name="employeeID" value="${employee.ID}" />
</form>

<h1><a href="/resdb/">Online Employee DB</a></h1>
<c:out value="${message}" escapeXml="false" />

<hr>

<h2>Employee Overview:</h2>
	<c:out value="${employee.firstName}" /> 
	<c:out value="${employee.middleInitial}" />. 
	<c:out value="${employee.lastName}" />
	(<c:out value="${employee.enterpriseID}" />)
	<br />
	<c:out value="${employee.level}" />, <c:out value="${employee.workForce}" />

<hr>

<h2>Project History:</h2>

<c:choose>
<c:when test="${fn:length(projectList) != 0}">
<c:forEach var="projectdetail" items="${projectList}">
	<c:set var="project" value="${projectdetail.project}"/>
	<p>
		<span class="project">${project.name}</span><br />
		${project.description}
	</p>
	
	<c:if test="${fn:length(projectdetail.projectRoles) != 0}">
	<table>
		<tr >
			<th width="40%">Role</th>
			<th width="20%">Start Date</th>
			<th width="20%">End Date</th>
			<th width="20%">Actions</th>
		</tr>	
		<c:forEach var="projectrole" items="${projectdetail.projectRoles}">
			<tr>
			<td><c:out value="${projectrole.role}" /></td>
			<td><c:out value="${projectrole.startDate}" /></td>
			<td><c:out value="${projectrole.endDate}" /></td>
			<td class="buttons">
				<button onClick="updateProjectRole(${projectrole.ID})">Update</button>
				<button onClick="deleteProjectRole(${projectrole.ID})">Delete</button>
			</td>
			</tr>
		</c:forEach>
	</table>
	</c:if>
	
	<button class="belowTable" onClick="addProjectRole(${project.ID})">Add Project Role</button>
	<br />
	<br />
</c:forEach>
</c:when>
<c:otherwise>
This employee has no project history yet.
</c:otherwise>
</c:choose>

<hr>
<h2>Skill Set:</h2>

<c:if test="${fn:length(skillList) != 0}">
<table>
	<tr >
		<th width="20%">Skill</th>
		<th width="40%">Description</th>
		<th width="20%">Rating</th>
		<th width="20%">Actions</th>
	</tr>
	
	<c:forEach var="skill" items="${skillList}">
		<tr>
			<td><c:out value="${skill.name}" /> </td>
			<td><c:out value="${skill.description}" /> </td>
			<td><c:out value="${skill.rating}" /> </td>
			<td class="buttons">
				<button onClick="javascript:updateEmployeeSkill(${skill.ID})">Update</button>
				<button onClick="javascript:deleteEmployeeSkill(${skill.ID})">Delete</button>
			</td>
		</tr>
	</c:forEach>
</table>
</c:if>

<button class="belowTable" onClick="javascript:addSkill()">Add Employee Skill</button>
<br />
<br />

<hr>

<a href="/resdb/view/employees.htm">Back to employees page</a>
</body>
</html>