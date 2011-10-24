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
function deleteDoctor(dID)
{
	var con
	con = confirm('Vai Jūs tiešam gribāt nodzēst šo ārstu?')
	if (con)
	{
		document.deleteDoctorForm.doctorID.value=dID
		document.deleteDoctorForm.submit()
	}
}
function updateDoctor(dID)
{
  document.updateDoctorForm.updateDoctorID.value=dID
  document.updateDoctorForm.submit()
}
function viewCycleDetails(cID)
{
  document.viewCycleDetailsForm.cycleID.value=cID
  document.viewCycleDetailsForm.submit()
}
</script>

<title>Rezidentu informācija</title>
</head>
<body>

<form name="deleteDoctorForm" action="/resdb/view/doctorList.htm" method="post">
	<input type="hidden" name="action" value="deleteDoctor" />
	<input type="hidden" name="doctorID" />
</form>
<form name="updateDoctorForm" action="/resdb/doctor/updateDoctor.htm" method="post">
	<input type="hidden" name="action" value="updateDoctor" />
	<input type="hidden" name="updateDoctorID" />
</form>
<form name="viewCycleDetailsForm" action="/resdb/view/cycleDetails.htm" method="post">
	<input type="hidden" name="cycleID">
</form>

<h1><a href="/resdb/">Rezidentu uzskaites sistēma</a></h1>
<c:out value="${message}" escapeXml="false" />

<hr>

<h2>Ārstu informācija:</h2>
<table class="narrow_table">
	<tr>
		<th width="30%">
			Personas dati
		</th>
		<th width="70%">
			Vērtība
		</th>
	</tr>
	<tr>
		<td>
			Vārds
		</td>
		<td>
			<c:out value="${doctor.vards}" />
		</td>
	</tr>
	<tr>	
		<td>
			Uzvārds
		</td>
		<td>
			<c:out value="${doctor.uzvards}" />
		</td>		
	</tr>
	<tr>		
		<td>
			Personas kods
		</td>
		<td>
			<c:out value="${doctor.personasKods}" />
		</td>
	</tr>
	<tr>		
		<td>
			Adrese
		</td>
		<td>
			<c:out value="${doctor.adrese}" />
		</td>
	</tr>
	<tr>		
		<td>
			Darba vieta
		</td>
		<td>
			<c:out value="${doctor.darbaVieta}" />
		</td>
	</tr>
	<tr>		
		<td>
			Akadēmiskais grāds
		</td>
		<td>
			<c:out value="${doctor.akademiskaisGrads}" />
		</td>
	</tr>
	<tr>		
		<td>
			Specialitāte
		</td>
		<td>
			<c:out value="${doctor.specialitate}" />
		</td>
	</tr>
	<tr>		
		<td>
			Talruņa numurs
		</td>
		<td>
			<c:out value="${doctor.talrunaNumurs}" />
		</td>
	</tr>
	<tr>		
		<td>
			E-pasta adrese
		</td>
		<td>
			<c:out value="${doctor.epasts}" />
		</td>
	</tr>
	<tr>		
		<td>
			Komentāri
		</td>
		<td>
			<c:out value="${doctor.komentari}" />
		</td>
	</tr>
</table>

<button class="belowTable" onClick="javascript:updateDoctor(${doctor.doctorPk})">Rediģēt ārsta datus</button>
<button class="belowTable" onClick="javascript:deleteDoctor(${doctor.doctorPk})">Nodzēst ārstu</button>
<hr>

<c:choose>
	<c:when test="${fn:length(doctor.cycleList) != 0}">
		<h2>Cikli, kurus pasniedz šīs ārsts:</h2>
		<display:table uid="cycle" name="cycleList" defaultsort="1" defaultorder="ascending" requestURI="/resdb/view/residentDetails.htm">
		    <display:column sortable="false" style="width: 1%">
		    	<a href="javascript:viewCycleDetails(${cycle.cyclePk})"><img src="pictures/black_arrow.png" align="middle" width="32" height="32" alt="Apskatīt papildus informāciju" /></a> 
		    </display:column>
		    <display:column sortable="true" style="width: 45%" title="Istāde un nodaļa"><c:out value="${cycle.department.label}" /></display:column>
		    <display:column sortable="true" style="width: 34%" title="Pasniedzējs"><c:out value="${cycle.pasniedzejs.label}" /></display:column>
		    <display:column sortable="true" style="width: 10%" title="Beigu datums" property="sakumaDatums" format="{0,date,dd.MM.yyyy}" />
		    <display:column sortable="true" style="width: 10%" title="Beigu datums" property="beiguDatums" format="{0,date,dd.MM.yyyy}" />
		</display:table>
	</c:when>
	<c:otherwise>
		<c:out value="Šīs ārsts pašlaik nepasniedz nevienu ciklu" />
		<br>
	</c:otherwise>
</c:choose>
<hr>

<c:choose>
	<c:when test="${fn:length(doctor.departmentList) != 0}">
		<h2>Nodaļas, kurus vada šīs ārsts:</h2>
		<table class="narrow_table">
			<tr>
				<th width="20%">
					Medicīniskā iestāde
				</th>
				<th width="20%">
					Nodaļa
				</th>
			</tr>
			<c:forEach var="department" items="${doctor.departmentList}">
				<tr>
					<td><c:out value="${department.facility.nosaukums}" /></td>
					<td><c:out value="${department.nosaukums}" /></td>
				</tr>
			</c:forEach>
		</table>
	</c:when>
	<c:otherwise>
		<c:out value="Šīs ārsts pašlaik nevāda nevienu nodaļu" />
		<br>
	</c:otherwise>
</c:choose>

<hr>

<a href="/resdb/view/doctorList.htm">Atgriezties uz ārstu sarakstu</a>

</body>
</html>
