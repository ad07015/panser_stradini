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
		<title>Piereģistrēt sirsniņu</title>
	</c:when>
	<c:otherwise>
		<title>Rediģēt sirsniņu</title>
	</c:otherwise>
</c:choose>

</head>
<h1>
	<a href="/resdb/">Rezidentu uzskaites sistēma</a>
</h1>

<hr>
Ievadiet sirsniņas informāciju
<hr>

<form:form commandName="heart" action="/resdb/view/residentDetails.htm" cssClass="bordless" method="post">
	<table class="bordless">
		<tr>
			<td width="160px">Tips</td>
			<td>
				<form:select path="tips">
					<form:option value="0" label="Select..." />
					<form:option value="green" label="Green heart" />
					<form:option value="black" label="Black heart" />
<%-- 					<c:forEach items="${typeList}" var="tips">
						<form:option value="${tips}" label="${tips}" />
					</c:forEach> --%>
				</form:select>
			<%-- 
				<select name="tips">
					<c:forEach items="${typeList}" var="type">
						<option>${type}</option>
					</c:forEach>
				</select>
				 --%>
			</td>
			<!-- 
			<td align="left">
				<input type="radio" name="group1" value="green">
				<img src="pictures/green_heart.png" align="middle" width="48" height="48" />
				<input type="radio" name="group1" value="black">
				<img src="pictures/black_heart.png" align="middle" width="48" height="48" />
			</td>
			 -->
			<td><form:errors path="tips" cssClass="error" /></td>
		</tr>
		<tr>
			<td>Komentāri</td>
			<td><form:input path="komentari" /></td>
		</tr>
		<tr>
			<td></td>
			<c:choose>
				<c:when test="${actionType == 'add'}">
					<td><input type="submit" value="Reģistrēt sirsniņu" />
					<td><input type="hidden" name="action" value="addHeart" /></td>
				</c:when>
				<c:otherwise>
					<td><input type="submit" value="Saglabāt izmaiņas" />
					<td><input type="hidden" name="heartID" value="${heart.ID}" /></td>
					<td><input type="hidden" name="action" value="updateHeart" /></td>
				</c:otherwise>
			</c:choose>
			<td><input type="hidden" name="residentID" value = "${heart.residentFK}" /></td>
		</tr>
	</table>
</form:form>

<hr>

<a href="/resdb/view/residentList.htm">Atgriezties uz rezidentu
	sarakstu</a>
</body>
</html>