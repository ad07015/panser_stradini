<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link type='text/css' rel='stylesheet' href='/resdb/main.css' />

<script type="text/javascript" src="/resdb/js/validators/constants.js"></script>
<script type="text/javascript" src="/resdb/js/validators/employee.js"></script>

<title>Update Employee</title>
</head>

<body onload="addListeners()">
<h1><a href="/resdb/">Online Employee DB</a></h1>
<c:out value="${message}" escapeXml="false" />

<hr>
Enter the updated details of this employee. The first name and last name fields are obligatory.
<hr>

<form class="bordless" name="updateEmployeeForm" method="post" onsubmit="return valEmployee()">
<input type="hidden" name="employeeID" value="${employee.ID}">
<table class="bordless">
	<tr>
		<td width="160px">First Name</td>
		<td width="1px"><input id="firstName" class="text" name="firstName" value="${employee.firstName}" maxlength="45" /></td>
		<td><span id="errorFirstName" class="error"></span></td>
	</tr>
	<tr>
		<td>Last Name</td>
		<td><input id="lastName" class="text" name="lastName" value="${employee.lastName}" maxlength="45" /></td>
		<td><span id="errorLastName" class="error"></span></td>
	</tr>
	<tr>
		<td>Middle Initial</td>
		<td><input id="middleInitial" class="text" name="middleInitial" value="${employee.middleInitial}" maxlength="1" /></td>
		<td><span id="errorMiddleInitial" class="error"></span></td>
	</tr>
	<tr>
		<td>Level</td>
		<td colspan="2"><input class="text" name="level" value="${employee.level}" maxlength="45" /></td>
	</tr>
	<tr>
		<td>Work Force</td>
		<td colspan="2"><input class="text" name="workForce" value="${employee.workForce}" maxlength="45" /></td>
	</tr>
	<tr>
		<td>Enterprise ID</td>
		<td><input class="text" name="enterpriseID" value="${employee.enterpriseID}" maxlength="45" /></td>
	</tr>
	<tr>
		<td></td>
		<td><input class="submit" type="submit" name="updateEmployee" value="Submit" /></td>
	</tr>
</table>
</form>

<hr>

<a href="/resdb/view/employees.htm">Back to search page</a>
</body>
</html>