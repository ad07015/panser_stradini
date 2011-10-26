<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link type='text/css' rel='stylesheet' href='/resdb/main.css' />

<script language="javascript" type="text/javascript">
function deleteFacility(fID) {
	var con
	con = confirm('Vai Jūs tiešam gribāt nodzēst šo medicīniskko iestādis?')
	if (con)
	{
		document.deleteFacilityForm.facilityID.value=fID
		document.deleteFacilityForm.submit()
	}
}
</script>

<title>Medicīnisko iestāžu saraksts</title>
</head>
<body>

<form name="deleteFacilityForm" action="/resdb/view/facilityList.htm" method="post">
	<input type="hidden" name="facilityID">
	<input type="hidden" name="action" value="deleteFacility">
</form>

<h1><a href="/resdb/">Rezidentu uzskaites sistēma</a></h1>
<c:choose>
	<c:when test="${status == 'success'}">
		<hr>
		<span><c:out value="${message}"></c:out></span>
	</c:when>
	<c:when test="${status == 'fail'}">
		<hr>
		<span class="error"><c:out value="${message}" escapeXml="false" /></span>
	</c:when>
</c:choose>

<hr>

<h2>Medicīnisko iestāžu saraksts:</h2>
<display:table uid="facility" name="facilityList" defaultsort="1" defaultorder="ascending" requestURI="/resdb/view/facilityList.htm" class="narrow_table">
    <display:column sortable="true" style="width: 45%" title="Nosaukums" property="nosaukums" />
    <display:column style="width: 3%">
   		<a href="javascript:deleteFacility(${facility.facilityPk})"><img src="pictures/red_cross.png" align="middle" width="24" height="24" alt="Nodzēst medicinisko iestadi" /></a>
   	</display:column>
</display:table>

<button class="belowTable" onClick="location.href='/resdb/doctor/addFacility.htm'">Piereģistrēt medicīnisko iestādi</button>

</body>
</html>
