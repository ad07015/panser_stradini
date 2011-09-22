<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link type='text/css' rel='stylesheet' href='/resdb/main.css' />

<script type="text/javascript" src="/resdb/js/validators/addEmployeeToProject.js"></script>
<script language="javascript" type="text/javascript">
function backToDetails(pID)
{
  document.backToDetailsForm.projectID.value=pID
  document.backToDetailsForm.submit()
}
</script>

<title>Assign Or Remove Employees</title>
</head>

<body onload="addListeners()">
<h1><a href="/resdb/">Online Employee DB</a></h1>
<c:out value="${message}" escapeXml="false" />

<hr>
Select employees to assign or remove from this project. You can select multiple employees at a time.
<hr>

<form name="backToDetailsForm" action="/resdb/view/projectDetails.htm" method="post">
	<input type="hidden" name="projectID" />
</form>

<form class="bordless" name="addEmployeeToProjectForm" method="post">
<input type="hidden" name="projectID" value="${projectID}" />
<table class="bordless">
	<tr>
		<td width="1px">
			<p>Unassigned</p>
			<select id="toProject" class="toFromProject" name="toProject" multiple>
				<c:forEach var="employee" items="${notInProjectList}">
					<option value="${employee.ID}">${employee.firstName} ${employee.lastName}</option>
				</c:forEach>
			</select>
		</td>
		<td width="160px" class="buttons">
			<input id="addToProject" class="assignRemove" type="submit" name="addToProject" value="Assign &gt;" disabled /><br />
			<input id="removeFromProject" class="assignRemove" type="submit" name="removeFromProject" value="&lt; Remove" disabled />
		</td>
		<td>
			<p>Assigned</p>
			<select id="fromProject" class="toFromProject" name="fromProject" multiple>
				<c:forEach var="employee" items="${inProjectList}">
					<option value="${employee.ID}">${employee.firstName} ${employee.lastName}</option>
				</c:forEach>
			</select>
		</td>
	</tr>
</table>
</form>

<hr>

<a href="javascript:backToDetails(${projectID})">Back to details page</a>
</body>
</html>
