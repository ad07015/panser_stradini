<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link type='text/css' rel='stylesheet' href='/resdb/main.css' />

<script language="javascript" type="text/javascript">
function viewProject(pID)
{
  document.viewProjectForm.projectID.value=pID
  document.viewProjectForm.submit()
}

function updateProject(pID)
{
  document.updateProjectForm.projectID.value=pID
  document.updateProjectForm.submit()
}

function deleteProject(pID,pName)
{
	var con
	con = confirm('Are you sure you want to remove this project?')
	if (con)
	{
  		document.deleteProjectForm.projectID.value=pID
  		document.deleteProjectForm.projectToDelete.value=pName
		document.deleteProjectForm.submit()
	}
}
</script> 

<title>Projects</title>
</head>

<body>
<form name="viewProjectForm" action="/resdb/view/projectDetails.htm" method="post">
	<input type="hidden" name="projectID">
</form>
<form name="updateProjectForm" action="/resdb/update/project.htm" method="post">
	<input type='hidden' name="projectID">
</form>
<form name="deleteProjectForm" method="post">
	<input type="hidden" name="projectID">
	<input type="hidden" name="projectToDelete">
</form>

<h1><a href="/resdb/">Online Employee DB</a></h1>

<hr>
Displaying all ${fn:length(projectList)} project<c:if test="${fn:length(projectList) != 1}">s</c:if>:
<hr>

	<table>
		<tr>
			<th width="20%">
				Name
			</th>
			<th width="40%">
				Description
			</th>
			<th width="20%">
				Client
			</th>
			<th width="20%">
				Actions
			</th>
		</tr>
<c:forEach var="project" items="${projectList}">
		<tr>
			<td>
				<a href="javascript:viewProject(${project.ID})"> 
					<c:out value="${project.name}" /> 
				</a>
			</td>
			<td>	
				<c:out value="${project.description}" /> 	
			</td>
			<td>	
				<c:out value="${project.client}" /> 	
			</td>
			<td class="buttons">
				<button onClick="javascript:updateProject(${project.ID})">Update</button>
				<button onClick="javascript:deleteProject(${project.ID},'${project.name}')">Delete</button>
			</td>
		</tr>
</c:forEach>
</table>
<button class="belowTable" onClick="location.href='/resdb/add/project.htm'">Add Project</button>
</body>
</html>
