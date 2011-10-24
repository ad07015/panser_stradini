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
		<title>Piereģistrēt ciklu</title>
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
Ievadiet cikla informāciju
<hr>

<form:form commandName="cycle" action="/resdb/view/cycleList.htm" cssClass="bordless" method="post">
	<table class="bordless">
  		<tr>
			<td width="160px">Nodaļa</td>
			<td width="160px">
				<form:select path="departmentFk">
					<form:option value="0" label="Select..." />
					<form:options items="${departmentList}" itemLabel="label" itemValue="departmentPk" />
				</form:select>
			</td>
			<td><form:errors path="departmentFk" cssClass="error" /></td>
		</tr>
  		<tr>
			<td>Pasniedzējs</td>
			<td>
				<form:select path="pasniedzejsFk">
					<form:option value="0" label="Select..." />
					<form:options items="${doctorList}" itemLabel="label" itemValue="doctorPk" />
				</form:select>
			</td>
			<td><form:errors path="pasniedzejsFk" cssClass="error" /></td>
		</tr>

<%-- 		<tr>
			<td>Nodaļa</td>
			<td>
				<form:select path="department">
					<form:option value="Select..." label="Select..." />
					<c:forEach items="${departmentList}" var="department">
						<form:option value="${department}" label="${department.facility.nosaukums} - ${department.nosaukums}" />
					</c:forEach>
				</form:select>
			</td>
		</tr> --%>
<%-- 		<tr>
			<td>Nodaļa</td>
			<td>
				<form:select path="departmentFk">
					<form:option value="Select..." label="Select..." />
					<c:forEach items="${departmentList}" var="department">
						<form:option value="department.departmentPk" label="${department.facility.nosaukums} - ${department.nosaukums}" />
					</c:forEach>
				</form:select>
			</td>
		</tr> --%>
		<tr>
			<td>Sākuma datums</td>
			<td><form:input path="sakumaDatums" /></td>
			<td><form:errors path="sakumaDatums" cssClass="error" /></td>
		</tr>
		<tr>
			<td>Beigu datums</td>
			<td><form:input path="beiguDatums" /></td>
			<td><form:errors path="beiguDatums" cssClass="error" /></td>
		</tr>
		<tr>
			<td></td>
			<td>
				<c:choose>
					<c:when test="${actionType == 'add'}">
						<input type="submit" value="Reģistrēt ciklu" />
						<input type="hidden" name="action" value="addCycle">
					</c:when>
					<c:otherwise>
						<input type="submit" value="Saglabāt izmaiņas" />
						<input type="hidden" name="action" value="updateCycle">
						<input type="hidden" name="residentID" value="${resident.residentPk}">
					</c:otherwise>
				</c:choose>
				<input type="hidden" name="actionType" value="${actionType}">
			</td>
		</tr>
	</table>
</form:form>

<hr>

<a href="/resdb/view/cycleList.htm">Atgriezties uz ciklu
	sarakstu</a>
</body>
</html>