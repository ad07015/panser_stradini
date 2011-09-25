<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link type='text/css' rel='stylesheet' href='/resdb/main.css' />

<script language="javascript" type="text/javascript">
function deleteResident(rID)
{
	var con
	con = confirm('Vai Jūs tiešam gribāt nodzēst šo rezidentu?')
	if (con)
	{
		document.deleteResidentForm.deleteResidentID.value=rID
		document.deleteResidentForm.submit()
	}
}
function updateResident(rID)
{
  document.updateResidentForm.updateResidentID.value=rID
  document.updateResidentForm.submit()
}
function deleteHeart(heartID)
{
	var con
	con = confirm('Vai Jūs tiešam gribāt nodzēst šo "Sirsniņu?')
	if (con)
	{
  		document.deleteHeartForm.heartID.value=heartID
		document.deleteHeartForm.submit()
	}
}

function updateHeart(heartID)
{
	document.updateHeartForm.heartID.value=heartID
	document.updateHeartForm.submit()
}
</script>

<title>Rezidentu informācija</title>
</head>
<body>

<form name="deleteResidentForm" action="/resdb/view/residentList.htm" method="post">
	<input type="hidden" name="deleteResidentID" >
</form>
<form name="updateResidentForm" action="/resdb/resident/updateResident.htm" method="post">
	<input type="hidden" name="updateResidentID" >
</form>
<form name="deleteHeartForm" action="/resdb/view/residentDetails.htm" method="post">
	<input type="hidden" name="deleteHeart" />
	<input type="hidden" name="heartID" />
	<input type="hidden" name="residentID" value="${resident.ID}" />
</form>
<form name="updateHeartForm" action="/resdb/resident/updateHeart.htm" method="post">
	<input type="hidden" name="heartID" />
</form>


<h1><a href="/resdb/">Rezidentu uzskaites sistēma</a></h1>

<c:out value="${message}" escapeXml="false" />

<hr>

<h2>Rezidentu informācija:</h2>
<table class="narrow_table">
	<tr>
		<th width="30%">Personas dati</th>
		<th width="70%">Vērtība</th>
	</tr>
	<tr>
		<td>Vārds</td>
		<td><c:out value="${resident.vards}" /></td>
	</tr>
	<tr>	
		<td>Uzvārds</td>
		<td><c:out value="${resident.uzvards}" /></td>		
	</tr>
	<tr>		
		<td>Personas kods</td>
		<td><c:out value="${resident.personasKods}" /></td>
	</tr>
	<tr>		
		<td>Darba līgums</td>
		<td><c:out value="${resident.darbaLigums}" /></td>
	</tr>
	<tr>		
		<td>Specialitāte</td>
		<td><c:out value="${resident.specialitate}" /></td>
	</tr>
	<tr>		
		<td>Universitāte</td>
		<td><c:out value="${resident.universitate}" /></td>
	</tr>
	<tr>		
		<td>Studiju gads</td>
		<td><c:out value="${resident.studijuGads}" /></td>
	</tr>
	<tr>		
		<td>Adrese</td>
		<td><c:out value="${resident.adrese}" /></td>
	</tr>
	<tr>		
		<td>Talruņa numurs</td>
		<td><c:out value="${resident.talrunaNumurs}" /></td>
	</tr>
	<tr>		
		<td>e-pasta adrese</td>
		<td><c:out value="${resident.epasts}" /></td>
	</tr>
	<tr>		
		<td>Komentāri</td>
		<td><c:out value="${resident.komentari}" /></td>
	</tr>
</table>


<button class="belowTable" onClick="javascript:updateResident(${resident.ID})">Rediģēt rezidenta datus</button>
<button class="belowTable" onClick="javascript:deleteResident(${resident.ID})">Nodzēst rezidentu</button>

<hr>

<c:choose>
	<c:when test="${fn:length(resident.heartList) != 0}">
		<h2>Sirsniņas:</h2>
		<table>
			<tr>
				<th width="4%">
					Tips
				</th>
				<th width="70%">
					Komentāri
				</th>
				<th width="15%">
					Darbība
				</th>
			</tr>
			<c:forEach var="heart" items="${resident.heartList}">
				<tr>
					<td>
						<c:choose>
							<c:when test="${heart.tips == 'green'}">
								<img src="pictures/green_heart.png" align="middle" width="48" height="48" alt="Green heart" />
							</c:when>
							<c:otherwise>
								<img src="pictures/black_heart.png" align="middle" width="48" height="48" alt="Black heart" />
							</c:otherwise>
						</c:choose>
					</td>
					<td><c:out value="${heart.komentari}" /></td>
					<td>
						<button class="belowTable" onClick="javascript:updateHeart(${heart.ID})">Rediģēt</button>
						<button class="belowTable" onClick="javascript:deleteHeart(${heart.ID})">Nodzēst</button>
					</td>
				</tr>
			</c:forEach>
		</table>
	</c:when>
	<c:otherwise>
		<c:out value="Šīm rezidentam nav piereģistrēto zaļo vai melno sirsniņu" />
	</c:otherwise>
</c:choose>

</body>
</html>
