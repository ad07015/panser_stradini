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
function viewDoctor(dID)
{
  document.viewDoctorDetailsForm.doctorID.value=dID
  document.viewDoctorDetailsForm.submit()
}
</script>

<title>Ārstu saraksts</title>
</head>
<body>

<form name="viewDoctorDetailsForm" action="/resdb/view/doctorDetails.htm" method="post">
	<input type="hidden" name="doctorID">
</form>

<h1><a href="/resdb/">Rezidentu uzskaites sistēma</a></h1>
<c:out value="${message}" escapeXml="false" />

<hr>

<h2>Ārstu saraksts:</h2>
<display:table uid="doctor" name="doctorList" defaultsort="1"
    defaultorder="ascending" requestURI="/resdb/view/doctorList.htm">
    <display:column sortable="true"  class="colWidth" maxLength="100" title="Vārds" property="uzvards" />
    <display:column sortable="true"  class="colWidth" maxLength="100" title="Uzvārds" property="uzvards" />
    <display:column sortable="false" class="colWidth" maxLength="100" title="Personas kods">
    	<a href="javascript:viewResident(${doctor.doctorPk})"><c:out value="${doctor.personasKods}" /></a>
    </display:column>
    <display:column sortable="true"  class="colWidth" maxLength="100" title="Specialitāte" property="specialitate" />
    <display:column sortable="true"  class="colWidth" maxLength="100" title="Akadēmiskais grāds" property="akademiskaisGrads" />
</display:table>

<button class="belowTable" onClick="location.href='/resdb/doctor/addDoctor.htm'">Piereģistrēt ārstu</button>
</body>
</html>
