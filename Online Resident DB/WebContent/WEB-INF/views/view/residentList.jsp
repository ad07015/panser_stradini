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

<h2>Rezidentu saraksts:</h2>
<display:table uid="resident" name="residentList" defaultsort="3" defaultorder="ascending" requestURI="/resdb/view/residentList.htm">
    <display:column style="width: 2%">
    	<a href="javascript:viewResident(${resident.residentPk})"><img src="pictures/black_arrow.png" align="middle" width="24" height="24" alt="Rezidenta info" /></a>
    </display:column>
    <display:column sortable="true"  class="colWidth" maxLength="100" title="Vārds" property="vards" />
    <display:column sortable="true"  class="colWidth" maxLength="100" title="Uzvārds" property="uzvards" />
    <display:column sortable="false" class="colWidth" maxLength="100" title="Personas kods" property="personasKods" />
    <display:column sortable="true"  class="colWidth" maxLength="100" title="Specialitāte" property="specialitate" />
    <display:column sortable="true"  class="colWidth" maxLength="100" title="Studiju gads" property="studijuGads" />
</display:table>
<button class="belowTable" onClick="location.href='/resdb/resident/addResident.htm'">Piereģistrēt rezidentu</button>

</body>
</html>
