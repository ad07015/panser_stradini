<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link type='text/css' rel='stylesheet' href='/resdb/main.css' />

<script type="text/javascript" src="/resdb/js/validators/constants.js"></script>
<script type="text/javascript" src="/resdb/js/validators/project.js"></script>

<title>Update Project</title>
</head>

<body onload="addListeners()">
<h1><a href="/resdb/">Online Employee DB</a></h1>
<c:out value="${message}" escapeXml="false" />

<hr>
Enter the updated details of this project. The name and client fields are obligatory.
<hr>

<form class="bordless" name="addProjectForm" method="post" onsubmit="return valProject()">
<input type="hidden" name="projectID" value="${project.ID}" />
<table class="bordless">
	<tr>
		<td width="160px">Project Name</td>
		<td width="1px"><input id="name" class="text" name="name" value="${project.name}" maxlength="45" /></td>
		<td><span id="errorName" class="error"></span></td>
	</tr>
	<tr>
		<td>Description</td>
		<td colspan="2"><input class="text" name="description" value="${project.description}" maxlength="255" /></td>
	</tr>
	<tr>
		<td>Client</td>
		<td colspan="2">
			<input id="client" class="text" name="client" value="${project.client}" maxlength="45" />
			<span id="errorClient" class="error"></span>
		</td>
	</tr>
	<tr>
		<td></td>
		<td><input class="submit" type="submit" name="updateProject" value="Submit" /></td>
	</tr>
</table>
</form>

<hr>

<a href="/resdb/view/projects.htm">Back to projects page</a>
</body>
</html>
