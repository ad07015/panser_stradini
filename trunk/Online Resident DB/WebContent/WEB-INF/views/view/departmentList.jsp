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
function deleteDepartment(dID) {
	var con
	con = confirm('Vai Jūs tiešam gribāt nodzēst šo nodaļu?')
	if (con)
	{
		document.deleteDepartmentForm.departmentID.value=dID
		document.deleteDepartmentForm.submit()
	}
}
</script>

<title>Nodaļu saraksts</title>
</head>
<body>

<form name="deleteDepartmentForm" action="/resdb/view/departmentList.htm" method="post">
	<input type="hidden" name="departmentID">
	<input type="hidden" name="action" value="deleteDepartment">
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
<display:table uid="department" name="departmentList" defaultsort="1" defaultorder="ascending" requestURI="/resdb/view/facilityList.htm">
    <display:column sortable="true" style="width: 32%" title="Nodaļas nosaukums" property="nosaukums" />
    <display:column sortable="true" style="width: 32%" title="Medicīniskā iestāde" property="facility.nosaukums" />
    <display:column sortable="true" style="width: 32%" title="Vadītājs" property="vaditajs.label" />
    <display:column style="width: 3%">
   		<a href="javascript:deleteDepartment(${department.departmentPk})"><img src="pictures/red_cross.png" align="middle" width="24" height="24" alt="Nodzest nodalu" /></a>
   	</display:column>
</display:table>

<button class="belowTable" onClick="location.href='/resdb/department/addDepartment.htm'">Piereģistrēt nodaļu</button>

</body>
</html>
