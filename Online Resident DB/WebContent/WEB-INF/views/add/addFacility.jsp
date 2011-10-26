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

<title>Piereģistrēt medicīnisko iestādi</title>

</head>
<h1>
	<a href="/resdb/">Rezidentu uzskaites sistēma</a>
</h1>

<hr>
Ievadiet medicīniskās iestādes informāciju
<hr>

<form:form commandName="facility" action="/resdb/view/facilityList.htm" cssClass="bordless" method="post">
	<table class="bordless">
  		<tr>
			<td width="160px">Nosaukums</td>
			<td><form:input path="nosaukums" /></td>
			<td><form:errors path="nosaukums" cssClass="error" /></td>
		</tr>
		<tr>
			<td></td>
			<td>
				<input type="submit" value="Reģistrēt medicīnisko iestādi" />
				<input type="hidden" name="action" value="addFacility">
			</td>
		</tr>
	</table>
</form:form>

</body>
</html>