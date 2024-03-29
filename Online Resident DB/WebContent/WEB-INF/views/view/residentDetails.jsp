<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
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
function viewCycleDetails(cID)
{
  document.viewCycleDetailsForm.cycleID.value=cID
  document.viewCycleDetailsForm.submit()
}
function unregisterResidentFromCycle(rID, cID)
{
	var con
	con = confirm('Vai Jūs tiešam gribāt noreģistrēt šo rezidentu no šī kursa?')
	if (con)
	{
		document.unregisterResidentFromCycleForm.residentID.value=rID
		document.unregisterResidentFromCycleForm.cycleID.value=cID
		document.unregisterResidentFromCycleForm.submit()
	}
}
function deleteCyclePlanEntry(cpeID, rID) {
	var con
	con = confirm('Vai Jūs tiešam gribāt nodzēst šo plāna ierakstu?')
	if (con)
	{
		document.deleteCyclePlanEntryForm.cyclePlanEntryID.value=cpeID
		document.deleteCyclePlanEntryForm.residentID.value=rID
		document.deleteCyclePlanEntryForm.submit()
	}
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
<form name="viewCycleDetailsForm" action="/resdb/view/cycleDetails.htm" method="post">
	<input type="hidden" name="cycleID">
</form>
<form name="unregisterResidentFromCycleForm" action="/resdb/view/residentDetails.htm" method="post">
	<input type="hidden" name="residentID">
	<input type="hidden" name="cycleID">
	<input type="hidden" name="action" value="unregisterResidentFromCycle">
</form>
<form name="deleteCyclePlanEntryForm" action="/resdb/view/residentDetails.htm" method="post">
	<input type="hidden" name="residentID">
	<input type="hidden" name="cyclePlanEntryID">
	<input type="hidden" name="action" value="deleteCyclePlanEntry">
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

<h2>Stūdiju plans:</h2>
<display:table uid="cpe" name="cyclePlanEntryList" defaultsort="1" defaultorder="ascending" requestURI="/resdb/view/residentDetails.htm">
    <display:column sortable="true" style="width: 47%" title="Cikla nosaukums" property="nosaukums"/>
    <display:column sortable="true" style="width: 47%" title="Komentāri" property="komentari"/>
    <display:column sortable="true" style="width: 3%" title="Kurss" property="kurss"/>
    <display:column style="width: 3%">
   		<a href="javascript:deleteCyclePlanEntry(${cpe.cyclePlanEntryPk}, ${resident.residentPk})"><img src="pictures/red_cross.png" align="middle" width="24" height="24" alt="Nodzēst plana ierakstu" /></a>
   	</display:column>
   	<display:setProperty name="basic.msg.empty_list_row" value="Šīm rezidentam nav sastadīts stūdiju plāns" />
</display:table>

<form action="/resdb/view/residentDetails.htm" method="post">
	<table>
		<tr>
			<td width="47%"><input name="nosaukums" /></td>
			<td width="47%"><input name="komentari" /></td>
			<td width="3%"><input class="inputWidth" name="kurss" /></td>
			<td width="3%"><img src="pictures/red_cross.png" align="middle" width="24" height="24" alt="Nodzēst plana ierakstu" /></td>
		</tr>
	</table>
	<input type="hidden" name="residentID" value="${resident.residentPk}" />
	<input type="hidden" name="action" value="addCyclePlanEntry" />
	<input type="submit" value="Pievienot plāna ierakstu" />
</form>
<hr>

<c:choose>
	<c:when test="${fn:length(residentCycleList) != 0}">
		<h2>Cikli, kurus apmeklē šīs rezidents:</h2>
		<display:table uid="resCyc" name="residentCycleList" defaultsort="1" defaultorder="ascending" requestURI="/resdb/view/residentDetails.htm">
		    <display:column sortable="false" style="width: 1%">
		    	<a href="javascript:viewCycleDetails(${resCyc.cycle.cyclePk})"><img src="pictures/black_arrow.png" align="middle" width="32" height="32" alt="Apskatīt papildus informāciju" /></a> 
		    </display:column>
		    <display:column sortable="true" style="width: 45%" title="Istāde un nodaļa"><c:out value="${resCyc.cycle.department.label}" /></display:column>
		    <display:column sortable="true" style="width: 34%" title="Pasniedzējs"><c:out value="${resCyc.cycle.pasniedzejs.label}" /></display:column>
		    <display:column sortable="true" style="width: 10%" title="Beigu datums" property="sakumaDatums" format="{0,date,dd.MM.yyyy}" />
		    <display:column sortable="true" style="width: 10%" title="Beigu datums" property="beiguDatums" format="{0,date,dd.MM.yyyy}" />
		   	<display:column style="width: 2%">
	    		<a href="javascript:unregisterResidentFromCycle(${resident.residentPk}, ${resCyc.cycle.cyclePk})"><img src="pictures/red_cross.png" align="middle" width="24" height="24" alt="Noreģistrēt rezidentu no cikla" /></a>
	    	</display:column>
		    <input type="hidden" name="residentID" value="${resident.residentPk}">
		</display:table>
	</c:when>
	<c:otherwise>
		<c:out value="Šīs rezidents nav piereģistrēts nevienam kursam" />
		<br>
	</c:otherwise>
</c:choose>

<c:choose>
	<c:when test="${fn:length(cycleList) != 0}">
		<form:form commandName="cycle" action="/resdb/view/residentDetails.htm" cssClass="bordless" method="post">
			<form:select path="cyclePk" cssStyle="width:350px">
				<form:option value="0" label="Select..." />
				<form:options items="${cycleList}" itemLabel="label" itemValue="cyclePk" />
			</form:select>
			<input type="hidden" name="residentID" value="${resident.residentPk}">
			<input type="hidden" name="action" value="addCycleToResident">
			<input type="submit" value="Piereģistrēt rezidentu šīm ciklam" />
		</form:form>
		<hr>
	</c:when>
</c:choose>
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
