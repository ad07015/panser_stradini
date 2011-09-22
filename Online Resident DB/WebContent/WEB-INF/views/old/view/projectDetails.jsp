<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link type='text/css' rel='stylesheet' href='/resdb/main.css' />

<script language="javascript" type="text/javascript">
function viewEmployee(eID)
{
  document.viewEmployeeForm.employeeID.value=eID
  document.viewEmployeeForm.submit()
}

function addEmployeeToProject(pID)
{
  document.addToProjectForm.projectID.value=pID
  document.addToProjectForm.submit()
}

function deleteEmployeeFromProject(eID,pID)
{
	var con
	con = confirm('Are you sure you want to remove this employee from the project?')
	if (con)
	{
  		document.deleteFromProjectForm.employeeID.value=eID
  		document.deleteFromProjectForm.projectID.value=pID
 		document.deleteFromProjectForm.submit()
	}
}
</script>

<title>Project Details</title>
</head>
<body>

<form name="viewEmployeeForm" action="/resdb/view/employeeDetails.htm" method="post">
	<input type="hidden" name="employeeID">
</form>
<form name="addToProjectForm" action="/resdb/add/employeeToProject.htm" method="post">
	<input type="hidden" name="projectID">
</form>
<form name="deleteFromProjectForm" method="post">
	<input type="hidden" name="deleteEmployeeFromProject">		
	<input type="hidden" name="employeeID">	
	<input type="hidden" name="projectID">
</form>

<h1><a href="/resdb/">Online Employee DB</a></h1>
<c:out value="${message}" escapeXml="false" />

<hr>

<h2>Project Overview:</h2>
	<c:out value="${project.name}" />
	(<c:out value="${project.client}" />)
	<br />
	<c:out value="${project.description}" />

<hr>

<h2>Employee List:</h2>
<table border="1" width="90%">
	<tr>
		<th width="20%">
			Employee Name
		</th>
		<th width="20%">
			Enterprise ID
		</th>
		<th width="20%">
			Level
		</th>
		<th width="20%">
			Workforce
		</th>
		<th width="20%">
			Actions
		</th>
	</tr>
	
<c:forEach var="employee" items="${employeeList}">
		<tr>
			<td>
				<a href="javascript:viewEmployee(${employee.ID})">
					<c:out value="${employee.firstName}" />
					<c:out value="${employee.middleInitial}" />.
					<c:out value="${employee.lastName}" />
				</a>
			</td>
			<td>	
				<c:out value="${employee.enterpriseID}" /> 	
			</td>
			<td>	
				<c:out value="${employee.level}" /> 	
			</td>
			<td>	
				<c:out value="${employee.workForce}" /> 	
			</td>
			<td class="buttons">
				<button onClick="javascript:deleteEmployeeFromProject(${employee.ID},${project.ID})">Remove From Project</button>
			</td>
		</tr>
</c:forEach>
</table>
<button class="belowTable" onClick="javascript:addEmployeeToProject(${project.ID})">Assign Or Remove Employees</button>
<br />
<br />

<hr>

<a href="/resdb/view/projects.htm">Back to projects page</a>
</body>
</html>
