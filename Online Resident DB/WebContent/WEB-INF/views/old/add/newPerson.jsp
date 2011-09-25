<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link type='text/css' rel='stylesheet' href='/resdb/main.css' />

<title>Piereģistrēt rezidentu</title>
</head>

<h1>
	<a href="/resdb/">Rezidentu uzskaites sistēma</a>
</h1>
<c:out value="${message}" escapeXml="false" />

<hr>
Ievadiet rezidenta informāciju
<hr>


<form:form commandName="person" cssClass="bordless" method="post">
	<table class="bordless">
		<tr>
			<td>First Name:</td>
			<td><form:input path="name.first"/></td>
			<td><form:errors path="name.first" cssClass="error"/></td>
		</tr>
		<tr>
			<td>Last Name:</td>
			<td><form:input path="name.last"/></td>
			<td><form:errors path="name.last" cssClass="error"/></td>
		</tr>
		<tr>
			<td>Born On:</td>
			<td><form:input path="bornOn" /></td>
			<td><form:errors path="bornOn" cssClass="error"/></td>
		</tr>
		<tr>
			<td>Favorite Programming Language:</td>
			<td>
				<select name="favoriteProgrammingLanguage">
					<c:forEach items="${languages}" var="language">
						<option>${language}</option>
					</c:forEach>
				</select>
			</td>
		</tr>
		<tr>
			<td />
			<td><input type="submit" /></td>
		</tr>
	</table>
</form:form>

<hr>

<a href="/resdb/view/employees.htm">Atgriezties uz rezidentu sarakstu</a>
</body>
</html>