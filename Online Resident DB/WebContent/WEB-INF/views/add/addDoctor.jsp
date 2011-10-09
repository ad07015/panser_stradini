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

<c:choose>
	<c:when test="${actionType == 'add'}">
		<title>Piereģistrēt ārstu</title>
	</c:when>
	<c:otherwise>
		<title>Rediģēt ārsta datus</title>
	</c:otherwise>
</c:choose>

</head>
<h1>
	<a href="/resdb/">Rezidentu uzskaites sistēma</a>
</h1>

<hr>
Ievadiet ārsta informāciju
<hr>

<form:form commandName="doctor" action="/resdb/view/doctorList.htm" cssClass="bordless" method="post">
	<table class="bordless">
		<tr>
			<td width="160px">Vārds</td>
			<td width="160px"><form:input path="vards" /></td>
			<td><form:errors path="vards" cssClass="error" /></td>
		</tr>
		<tr>
			<td>Uzvārds</td>
			<td><form:input path="uzvards" /></td>
			<td><form:errors path="uzvards" cssClass="error" /></td>
		<tr>
			<td>Personas kods</td>
			<td><form:input path="personasKods" /></td>
			<td><form:errors path="personasKods" cssClass="error" /></td>
		</tr>
		<tr>
			<td>Akadēmiskais grāds</td>
			<td><form:input path="akademiskaisGrads" /></td>
			<td><form:errors path="akademiskaisGrads" cssClass="error" /></td>
		</tr>
		<tr>
			<td>Darbavieta</td>
			<td><form:input path="darbaVieta" /></td>
			<td><form:errors path="darbaVieta" cssClass="error" /></td>
		</tr>
		<tr>
			<td>Specialitāte</td>
			<td><form:input path="specialitate" /></td>
			<td><form:errors path="specialitate" cssClass="error" /></td>
		</tr>
		<tr>
			<td>Adrese</td>
			<td><form:input path="adrese" /></td>
			<td><form:errors path="adrese" cssClass="error" /></td>
		</tr>
		<tr>
			<td>Talruņa numurs</td>
			<td><form:input path="talrunaNumurs" /></td>
			<td><form:errors path="talrunaNumurs" cssClass="error" /></td>
		</tr>
		<tr>
			<td>E-pasta adrese</td>
			<td><form:input path="epasts" /></td>
			<td><form:errors path="epasts" cssClass="error" /></td>
		</tr>
		<tr>
			<td>Komentāri</td>
			<td><form:input path="komentari" /></td>
		</tr>
		<tr>
			<td></td>
			<td>
				<c:choose>
					<c:when test="${actionType == 'add'}">
						<input type="submit" value="Reģistrēt arstu" />
						<input type="hidden" name="action" value="addDoctor" />
						<input type="hidden" name="actionType" value="${actionType}" />
					</c:when>
					<c:otherwise>
						<input type="submit" value="Saglabāt izmaiņas" />
						<input type="hidden" name="action" value="updateDoctor" />
						<input type="hidden" name="doctorID" value="${doctor.doctorPk}" />
						<input type="hidden" name="actionType" value="${actionType}" />
					</c:otherwise>
				</c:choose>
			</td>
		</tr>
	</table>
</form:form>

<hr>

<a href="/resdb/view/doctorList.htm">Atgriezties uz ārstu sarakstu</a>
</body>
</html>