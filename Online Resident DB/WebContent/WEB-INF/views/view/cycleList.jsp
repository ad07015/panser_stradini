<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link type='text/css' rel='stylesheet' href='/resdb/main.css' />

<title>Ciklu saraksts</title>
</head>
<body>


<h1><a href="/resdb/">Rezidentu uzskaites sistēma</a></h1>
<c:choose>
	<c:when test="${status == 'success'}">
		<hr>
		<span><c:out value="${message}"></c:out></span>
	</c:when>
	<c:when test="${status == 'fail'}">
		<hr>
		<%-- <span class="error"><c:out value="${message}" escapeXml="false" /></span> --%>
		<span class="error"><c:out value="${message}" escapeXml="false" /></span>
	</c:when>
</c:choose>

<hr>

<h2>Ciklu saraksts:</h2>
<table border="1" width="90%">
	<tr>
		<th width="20%">
			Iestāde un nodaļa
		</th>
		<th width="20%">
			Pasniedzējs
		</th>
		<th width="20%">
			Sakuma datums
		</th>
		<th width="20%">
			Beigu datums
		</th>
	</tr>
	
<c:forEach var="cycle" items="${cycleList}">
		<tr>
			<td>
				<c:out value="${cycle.department.label}" />
			</td>
			<td>	
				<c:out value="${cycle.pasniedzejs.label}" /> 	
			</td>
			<td>
				<c:out value="${cycle.sakumaDatums}" />
			</td>
			<td>	
				<c:out value="${cycle.beiguDatums}" /> 	
			</td>
		</tr>
</c:forEach>
</table>

<button class="belowTable" onClick="location.href='/resdb/cycle/addCycle.htm'">Piereģistrēt ciklu</button>

</body>
</html>
