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
function viewCycleDetails(cID)
{
  document.viewCycleDetailsForm.cycleID.value=cID
  document.viewCycleDetailsForm.submit()
}
</script>

<title>Ciklu saraksts</title>
</head>
<body>

<form name="viewCycleDetailsForm" action="/resdb/view/cycleDetails.htm" method="post">
	<input type="hidden" name="cycleID">
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

<h2>Ciklu saraksts:</h2>
<display:table uid="cycle" name="cycleList" defaultsort="1" defaultorder="ascending" requestURI="/resdb/view/cycleList.htm">
    <display:column sortable="false" style="width: 1%">
    	<a href="javascript:viewCycleDetails(${cycle.cyclePk})"><img src="pictures/black_arrow.png" align="middle" width="32" height="32" alt="Apskatīt papildus informāciju" /></a> 
    </display:column>
    <display:column sortable="true" style="width: 45%" title="Istāde un nodaļa"><c:out value="${cycle.department.label}" /></display:column>
    <display:column sortable="true" style="width: 34%" title="Pasniedzējs"><c:out value="${cycle.pasniedzejs.label}" /></display:column>
    <display:column sortable="true" style="width: 10%" title="Beigu datums" property="sakumaDatums" format="{0,date,dd.MM.yyyy}" />
    <display:column sortable="true" style="width: 10%" title="Beigu datums" property="beiguDatums" format="{0,date,dd.MM.yyyy}" />
    <input type="hidden" name="residentID" value="${resCyc.resident.residentPk}">
</display:table>

<button class="belowTable" onClick="location.href='/resdb/cycle/addCycle.htm'">Piereģistrēt ciklu</button>

</body>
</html>
