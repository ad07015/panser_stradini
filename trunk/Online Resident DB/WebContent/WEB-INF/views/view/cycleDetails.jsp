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
function viewResident(rID)
{
  document.viewResidentDetailsForm.residentID.value=rID
  document.viewResidentDetailsForm.submit()
}

function addResidentToCycle(cID)
{
	document.addResidentToCycleForm.cycleID.value=cID
	document.addResidentToCycleForm.submit()
}

function submitPassedChange(rID, cb)
{
	document.submitPassedChangeForm.residentID.value=rID
	document.submitPassedChangeForm.passedNew.value=cb.checked
	document.submitPassedChangeForm.submit()
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

</script>

<title>Cikla informācija</title>
</head>
<body>

<form name="viewResidentDetailsForm" action="/resdb/view/residentDetails.htm" method="post">
	<input type="hidden" name="residentID">
</form>
<form name="addResidentToCycleForm" action="/resdb/cycle/addResidentToCycle.htm" method="post">
	<input type="hidden" name="cycleID">
</form>
<form name="unregisterResidentFromCycleForm" action="/resdb/view/cycleDetails.htm" method="post">
	<input type="hidden" name="residentID">
	<input type="hidden" name="cycleID">
	<input type="hidden" name="action" value="unregisterResidentFromCycle">
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

<h2>Cikla informācija:</h2>
<table class="narrow_table">
	<tr>
		<td class="th">Medicīniskā iestāde</td>
		<td><c:out value="${cycle.department.facility.nosaukums}" /></td>
	</tr>
	<tr>	
		<td class="th">Nodaļa</td>
		<td><c:out value="${cycle.department.nosaukums}" /></td>		
	</tr>
	<tr>	
		<td class="th">Vadītajs</td>
		<td><c:out value="${cycle.department.vaditajs.label}" /></td>		
	</tr>
	<tr>	
		<td class="th">Sakuma datums</td>
		<td><fmt:formatDate pattern="dd.MM.yyyy" value="${cycle.sakumaDatums}" /></td>		
	</tr>
	<tr>	
		<td class="th">Beigu datums</td>
		<td><fmt:formatDate pattern="dd.MM.yyyy" value="${cycle.beiguDatums}" /></td>
	</tr>
</table>

<hr>

<h2>Rezidenti, kuri apmeklē šo ciklu:</h2>
<form name="submitPassedChangeForm" action="/resdb/view/cycleDetails.htm" class="bordless" method="post">
	<display:table uid="resCyc" name="residentCycleList" defaultsort="2" keepStatus="true"
	    defaultorder="ascending" requestURI="/resdb/view/cycleDetails.htm">
		<display:column style="width: 2%">
	    	<a href="javascript:viewResident(${resCyc.resident.residentPk})"><img src="pictures/black_arrow.png" align="middle" width="24" height="24" alt="Rezidenta info" /></a>
	    </display:column>
	    <display:column sortable="true" style="width: 20%" title="Vārds"><c:out value="${resCyc.resident.vards}" /></display:column>
	    <display:column sortable="true" style="width: 20%" title="Uzvārds"><c:out value="${resCyc.resident.uzvards}" /></display:column>
	    <display:column sortable="false" style="width: 15%" title="Personas kods"><c:out value="${resCyc.resident.personasKods}" /></display:column>
	    <display:column sortable="true" style="width: 20%" title="Specialitāte"><c:out value="${resCyc.resident.specialitate}" /></display:column>
	    <display:column sortable="true" style="width: 5%" title="Studiju gads"><c:out value="${resCyc.resident.studijuGads}" /></display:column>
	    <display:column sortable="true" title="Nolikts" style="width: 2%">
			<c:choose>
				<c:when test="${resCyc.passed == true}">
					<input type="checkbox" name="passed" value="${resCyc.resident.residentPk}" checked  
							onchange="javascript:submitPassedChange(${resCyc.resident.residentPk}, this)" />
				</c:when>
				<c:when test="${resCyc.passed == false}">
					<input type="checkbox" name="passed" value="${resCyc.resident.residentPk}" 
							onchange="javascript:submitPassedChange(${resCyc.resident.residentPk}, this)" />
				</c:when>
			</c:choose>
	    </display:column>
	    <display:column style="width: 2%">
	    	<a href="javascript:unregisterResidentFromCycle(${resCyc.resident.residentPk}, ${cycle.cyclePk})"><img src="pictures/red_cross.png" align="middle" width="24" height="24" alt="Noreģistrēt rezidentu no cikla" /></a>
	    </display:column>
	</display:table>
	<input type="hidden" name="cycleID" value="${cycle.cyclePk}">
	<input type="hidden" name="residentID">
	<input type="hidden" name="passedNew">
	<input type="hidden" name="action" value="submitPassedChangeForm">
</form>

<hr>

<c:choose>
	<c:when test="${fn:length(residentList) != 0}">
		<form:form commandName="resident" action="/resdb/view/cycleDetails.htm" cssClass="bordless" method="post">
			<form:select path="residentPk" cssStyle="width:350px">
				<form:option value="0" label="Select..." />
				<form:options items="${residentList}" itemLabel="label" itemValue="residentPk" />
			</form:select>
			<input type="hidden" name="cycleID" value="${cycle.cyclePk}">
			<input type="hidden" name="action" value="addResidentToCycle">
			<input type="submit" value="Piereģistrēt rezidentu šīm ciklam" />
		</form:form>
		<hr>
	</c:when>
</c:choose>

<a href="/resdb/view/cycleList.htm">Atgriezties uz ciklu sarakstu</a>

</body>
</html>
