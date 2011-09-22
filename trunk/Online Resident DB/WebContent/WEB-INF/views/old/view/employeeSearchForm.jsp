<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link type='text/css' rel='stylesheet' href='/resdb/main.css' />
<title>Employees</title>
</head>

<body>
<h1><a href="/resdb/">Online Employee DB</a></h1>
<c:out value="${message}" escapeXml="false" />

<hr>
Enter the first name and the last name of the employee you wish to find and the application will return a list of employees with names that contain the search parameters you entered.
<hr>

<form class="bordless" name="searchForm" action="/resdb/view/employees.htm" method="post">
<input type="hidden" name="actionType" />
<input type="hidden" name="employeeID" />
<table class="bordless">
	<tr>
		<td width="160px">First Name</td>
		<td width="1px"><input class="text" name="firstName" /></td>
		<td></td>
	</tr>
	<tr>
		<td>Last Name</td>
		<td><input class="text" name="lastName" /></td>
		<td><input class="find" type="submit" name="findByName" value="Find By Name" /></td>
	</tr>
	<tr><td colspan=2>or</td></tr>
	<tr><td>Project Name</td>
		<td>
			<select name="projectID">
	           	<c:forEach var="project" items="${projectList}">
					<option value="${project.ID}">${project.name}</option>
	           	</c:forEach>
	        </select>
		</td>	
		<td><input class="find" type="submit" name="findByProject" value="Find By Project" /></td>
	</tr>
</table>
</form>

<hr>

<%@ include file="employeeSearchResults.jsp" %>
</body>
</html>