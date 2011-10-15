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
function addHeart(residentFK)
{
	document.addHeartForm.residentFK.value=residentFK
	document.addHeartForm.submit()
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

function updateHeart(heartID, residentFK)
{
	document.updateHeartForm.heartID.value=heartID
	document.updateHeartForm.residentFK.value=residentFK
	document.updateHeartForm.submit()
}
</script>

<title>Rezidentu informācija</title>
</head>
<body>

<form name="deleteResidentForm" action="/resdb/view/residentList.htm" method="post">
	<input type="hidden" name="deleteResidentID" />
</form>
<form name="updateResidentForm" action="/resdb/resident/updateResident.htm" method="post">
	<input type="hidden" name="updateResidentID" />
</form>
<form name="addHeartForm" action="/resdb/resident/addHeart.htm" method="post">
	<input type="hidden" name="action" value="addHeart" />
	<input type="hidden" name="residentFK" />
</form>
<form name="deleteHeartForm" action="/resdb/view/residentDetails.htm" method="post">
	<input type="hidden" name="deleteHeart" />
	<input type="hidden" name="heartID" />
	<input type="hidden" name="residentID" value="${resident.residentPk}" />
</form>
<form name="updateHeartForm" action="/resdb/resident/updateHeart.htm" method="post">
	<input type="hidden" name="heartID" />
	<input type="hidden" name="residentFK" />
</form>


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


<button class="belowTable" onClick="javascript:updateResident(${resident.residentPk})">Rediģēt rezidenta datus</button>
<button class="belowTable" onClick="javascript:deleteResident(${resident.residentPk})">Nodzēst rezidentu</button>

<hr>

<c:choose>
	<c:when test="${fn:length(heartList) != 0}">
		<h2>Sirsniņas:</h2>
		<display:table uid="heart" name="heartList" defaultsort="1"
		    defaultorder="ascending" requestURI="/resdb/view/residentList.htm">
		    <display:column sortable="false" style="width: 2%" title="Tips">
				<c:choose>
					<c:when test="${heart.tips == 'green'}">
						<img src="pictures/green_heart.png" align="middle" width="48" height="48" alt="Green heart" />
					</c:when>
					<c:otherwise>
						<img src="pictures/black_heart.png" align="middle" width="48" height="48" alt="Black heart" />
					</c:otherwise>
				</c:choose>
		    </display:column>
		    <display:column title="Komentāri" property="komentari" />
		    <display:column style="width: 5%">
		    	<button class="belowTable" onClick="javascript:updateHeart(${heart.ID}, ${resident.residentPk})">Rediģēt</button>
		    </display:column>
		    <display:column style="width: 5%">
				<button class="belowTable" onClick="javascript:deleteHeart(${heart.ID})">Nodzēst</button>
		    </display:column>
		</display:table>
	</c:when>
	<c:otherwise>
		<c:out value="Šīm rezidentam nav piereģistrēto zaļo vai melno sirsniņu" />
		<br>
	</c:otherwise>
</c:choose>

<button class="belowTable" onClick="javascript:addHeart(${resident.residentPk})">Piereģistrēt sirsniņu</button>

<hr>

<a href="/resdb/view/residentList.htm">Atgriezties uz rezidentu sarakstu</a>
</body>
</html>
