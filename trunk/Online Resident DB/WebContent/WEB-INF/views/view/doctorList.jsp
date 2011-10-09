<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

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
	
<c:forEach var="doctor" items="${doctorList}">
		<tr>
			<td>
				<c:out value="${doctor.vards}" />
			</td>
			<td>	
				<c:out value="${doctor.uzvards}" /> 	
			</td>
			<td>
				<a href="javascript:viewDoctor(${doctor.doctorPk})"> 	
					<c:out value="${doctor.personasKods}" />
				</a>
			</td>
			<td>	
				<c:out value="${doctor.talrunaNumurs}" /> 	
			</td>
			<td>	
				<c:out value="${doctor.adrese}" /> 	
			</td>
		</tr>
</c:forEach>
</table>

<button class="belowTable" onClick="location.href='/resdb/doctor/addDoctor.htm'">Piereģistrēt ārstu</button>
</body>
</html>
