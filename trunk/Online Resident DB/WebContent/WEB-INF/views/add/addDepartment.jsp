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
		<title>Piereģistrēt nodaļu</title>
	</c:when>
	<c:otherwise>
		<title>Rediģēt ciklu</title>
	</c:otherwise>
</c:choose>

</head>
<h1>
	<a href="/resdb/">Rezidentu uzskaites sistēma</a>
</h1>

<hr>
Ievadiet nodaļas informāciju
<hr>

<form:form commandName="department" action="/resdb/view/departmentList.htm" cssClass="bordless" method="post">
	<table class="bordless">
  		<tr>
			<td width="160px">Medicīniskā iestāde</td>
			<td width="160px">
				<form:select path="facilityFk">
					<form:option value="0" label="Select..." />
					<form:options items="${facilityList}" itemLabel="nosaukums" itemValue="facilityPk" />
				</form:select>
			</td>
		</tr>
  		<tr>
			<td>Vadītājs</td>
			<td>
				<form:select path="vaditajsFk">
					<form:option value="0" label="Select..." />
					<form:options items="${doctorList}" itemLabel="label" itemValue="doctorPk" />
				</form:select>
			</td>
		</tr>
  		<tr>
			<td>Nosaukums</td>
			<td><form:input path="nosaukums" /></td>
			<td><form:errors path="nosaukums" cssClass="error" /></td>
		</tr>
		<tr>
			<td></td>
			<td>
				<input type="submit" value="Reģistrēt nodaļu" />
				<input type="hidden" name="action" value="addDepartment">
			</td>
		</tr>
	</table>
</form:form>

<hr>

<a href="/resdb/view/cycleList.htm">Atgriezties uz  sarakstu</a>
</body>
</html>