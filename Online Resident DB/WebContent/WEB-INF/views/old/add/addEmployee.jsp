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

<title>Add Employee</title>
</head>

<body onload="addListeners()">
<h1><a href="/resdb/">Online Employee DB</a></h1>

<hr>
<c:choose>
	<c:when test="${message != ''}">
		<c:when test="${message=='Success'}">
			Rezidents ir veiksmīgi nodzēsts!
		</c:when>
		<c:otherwise>
			Neizdevās nodzēst rezidentu!
		</c:otherwise>
	</c:when>
</c:choose>

<hr>
Enter details of an employee you wish to add. The first name and last name fields are obligatory.
<hr>

<form class="bordless" name="addEmployeeForm" method="POST"">
<table class="bordless">
	<tr>
		<td width="160px">First Name</td>
		<td width="1px"><input id="firstName" class="text" name="firstName" maxlength="45" /></td>
		<td><span id="errorFirstName" class="error"></span></td>
	</tr>
	<tr>
		<td>Last Name</td>
		<td><input id="lastName" class="text" name="lastName" maxlength="45" /></td>
		<td><span id="errorLastName" class="error"></span></td>
	</tr>
	<tr>
		<td>Middle Initial</td>
		<td><input id="middleInitial" class="text" name="middleInitial" maxlength="1" /></td>
		<td><span id="errorMiddleInitial" class="error"></span></td>
	</tr>
	<tr>
		<td>Level</td>
		<td colspan="2"><input class="text" name="level" maxlength="45" /></td>
	</tr>
	<tr>
		<td>Work Force</td>
		<td colspan="2"><input class="text" name="workForce" maxlength="45" /></td>
	</tr>
	<tr>
		<td>Enterprise ID</td>
		<td colspan="2"><input class="text" name="enterpriseID" maxlength="45" /></td>
	</tr>
	<tr>
		<td></td>
		<td colspan="2"><input class="submit" type="submit" name="addEmployee" value="Submit" /></td>
	</tr>
</table>
</form>

<hr>

<a href="/resdb/view/residentList.htm">Atgriezties uz rezidentu sarakstu</a>
</body>
</html>