<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link type='text/css' rel='stylesheet' href='/resdb/main.css' />

<script language="javascript" type="text/javascript">
function viewResident(rID)
{
  document.viewResidentDetailsForm.residentID.value=rID
  document.viewResidentDetailsForm.submit()
}
</script>

<title>Rezidentu saraksts</title>
</head>
<body>

<form name="viewResidentDetailsForm" action="/resdb/view/residentDetails.htm" method="post">
	<input type="hidden" name="residentID">
</form>

<h1><a href="/resdb/">Rezidentu uzskaites sistēma</a></h1>
<c:out value="${message}" escapeXml="false" />
<!--  
<hr>
<c:if test="${message!=''}">
	<c:choose>
		<c:when test="${message=='Success'}">
			Rezidents ir veiksmīgi nodzēsts!
		</c:when>
		<c:otherwise>
			Neizdevās nodzēst rezidentu!
		</c:otherwise>
	</c:choose>
</c:if>-->

<hr>

<h2>Rezidentu saraksts:</h2>
<table border="1" width="90%">
	<tr>
		<th width="20%">
			Vārds
		</th>
		<th width="20%">
			Uzvārds
		</th>
		<th width="20%">
			Personas kods
		</th>
		<th width="20%">
			Talruņa numurs
		</th>
		<th width="20%">
			Adrese
		</th>
	</tr>
	
<c:forEach var="resident" items="${residentList}">
		<tr>
			<td>
				<c:out value="${resident.vards}" />
			</td>
			<td>	
				<c:out value="${resident.uzvards}" /> 	
			</td>
			<td>
				<a href="javascript:viewResident(${resident.ID})"> 	
					<c:out value="${resident.personasKods}" />
				</a> 	
			</td>
			<td>	
				<c:out value="${resident.talrunaNumurs}" /> 	
			</td>
			<td>	
				<c:out value="${resident.adrese}" /> 	
			</td>
		</tr>
</c:forEach>
</table>

<button class="belowTable" onClick="location.href='/resdb/add/addResident.htm'">Piereģistrēt rezidentu</button>

</body>
</html>
