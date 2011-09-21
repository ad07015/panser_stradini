<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link type='text/css' rel='stylesheet' href='/resdb/main.css' />

<title>Piereģistrēt rezidentu</title>
</head>

<h1><a href="/resdb/">Rezidentu uzskaites sistēma</a></h1>
<c:choose>
	<c:when test="${message == 'Success'}">
		<c:out value="Successfully" escapeXml="false" />
	</c:when>
	<c:when test="${message == 'Fail'}">
		<c:out value="Unsuccessfully" escapeXml="false" />
	</c:when>
</c:choose>

<hr>
Ievadiet rezidenta informāciju
<hr>


<form:form commandName="resident" cssClass="bordless" method="post">
<table class="bordless">
	<tr>
		<td width="160px">Vārds</td>
		<td width="1px"><form:input path="vards" /></td>
		<td><form:errors path="vards" cssClass="error"/></td>
	</tr>
	<tr>
		<td>Uzvārds</td>
		<td><form:input path="uzvards" /></td>
		<td><form:errors path="uzvards" cssClass="error"/></td>
	<tr>
		<td>Personas kods</td>
		<td><form:input path="personasKods" /></td>
		<td><form:errors path="personasKods" cssClass="error"/></td>
	</tr>
	<tr>
		<td>Darba līgums</td>
		<td><form:input path="darbaLigums" /></td>
		<td><form:errors path="darbaLigums" cssClass="error"/></td>
	</tr>
	<tr>
		<td>Specialitāte</td>
		<td><form:input path="specialitate" /></td>
		<td><form:errors path="specialitate" cssClass="error"/></td>
	</tr>
	<tr>
		<td>Universitāte</td>
		<td><form:input path="universitate" /></td>
		<td><form:errors path="universitate" cssClass="error"/></td>
	</tr>
	<tr>
		<td>Studiju gads</td>
		<td><form:input path="studijuGads" /></td>
		<td><form:errors path="studijuGads" cssClass="error"/></td>
	</tr>
	<tr>
		<td>Adrese</td>
		<td><form:input path="adrese" /></td>
		<td><form:errors path="adrese" cssClass="error"/></td>
	</tr>
	<tr>
		<td>Talruņa numurs</td>
		<td><form:input path="talrunaNumurs" /></td>
		<td><form:errors path="talrunaNumurs" cssClass="error"/></td>
	</tr>
	<tr>
		<td>E-pasta adrese</td>
		<td><form:input path="epasts" /></td>
		<td><form:errors path="epasts" cssClass="error"/></td>
	</tr>
	<tr>
		<td>Komentāri</td>
		<td><form:input path="komentari" /></td>
	</tr>
	<tr>
		<td></td>
		<td><input type="submit" value="Reģistrēt rezidentu" /></td>
	</tr>
</table>
</form:form>

<hr>

<a href="/resdb/view/residentList.htm">Atgriezties uz rezidentu sarakstu</a>
</body>
</html>