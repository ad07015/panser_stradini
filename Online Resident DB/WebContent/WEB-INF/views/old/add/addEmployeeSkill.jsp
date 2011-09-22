<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link type='text/css' rel='stylesheet' href='/resdb/main.css' />

<script type="text/javascript" src="/resdb/js/validators/constants.js"></script>
<script type="text/javascript" src="/resdb/js/validators/employeeSkill.js"></script>

<title>Add Employee Skill</title>
</head>

<body onload="addListeners()">
<h1><a href="/resdb/">Online Employee DB</a></h1>
<c:out value="${message}" escapeXml="false" />

<hr>
Enter details of a skill you wish to add for this employee. The name and rating fields are obligatory.
<hr>

<form class="bordless" name="addSkillForm" method="post" onsubmit="return valEmployeeSkill()">
<input type="hidden" name="employeeID" value="${employeeID}" />
<table class="bordless">
	<tr>
		<td width="160px">Skill Name</td>
		<td width="1px"><input id="name" class="text" name="name" maxlength="45" /></td>
		<td><span id="errorName" class="error"></span></td>
	</tr>
	<tr>
		<td>Skill Description</td>
		<td colspan="2"><input class="text" name="description" maxlength="255" /></td>
	</tr>
	<tr>
		<td>Skill Rating</td>
		<td><input id="rating" class="text" name="rating" maxlength="9" /></td>
		<td><span id="errorRating" class="error"></span></td>
	</tr>
	<tr>
		<td></td>
		<td><input class="submit" type="submit" name="addSkill" value="Submit" /></td>
	</tr>
</table>
</form>

<hr>

<a href="/resdb/view/employeeDetails.htm?employeeID=${employeeID}">Back to details page</a>
</body>
</html>
