<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

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

<h1><a href="/resdb/">Rezidentu uzskaites sistēma</a></h1>
<c:out value="${message}" escapeXml="false" />

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
		<td><c:out value="${cycle.sakumaDatums}" /></td>		
	</tr>
	<tr>	
		<td class="th">Beigu datums</td>
		<td><c:out value="${cycle.beiguDatums}" /></td>		
	</tr>
</table>

<hr>

<h2>Rezidenti, kuri apmeklē šo kursu:</h2>


<form name="submitPassedChangeForm" action="/resdb/view/cycleDetails.htm" class="bordless" method="post">
	<table border="1" width="90%">
		<tr>
			<th width="20%">
				Vārds
			</th>
			<th width="20%">
				Uzvārds
			</th>
			<th width="18%">
				Personas kods
			</th>
			<th width="20%">
				Talruņa numurs
			</th>
			<th width="20%">
				Adrese
			</th>
			<th width="2%">
				Nokartots
				<input type="hidden" name="cycleID" value="${cycle.cyclePk}">
				<input type="hidden" name="residentID">
				<input type="hidden" name="passedNew">
				<input type="hidden" name="action" value="submitPassedChangeForm">
			</th>
			
		</tr>
		
	<c:forEach var="resCyc" items="${cycle.residentCycleList}">
			<tr>
				<td>
					<c:out value="${resCyc.resident.vards}" />
				</td>
				<td>	
					<c:out value="${resCyc.resident.uzvards}" /> 	
				</td>
				<td>
					<a href="javascript:viewResident(${resCyc.resident.residentPk})"> 	
						<c:out value="${resCyc.resident.personasKods}" />
					</a> 	
				</td>
				<td>	
					<c:out value="${resCyc.resident.talrunaNumurs}" /> 	
				</td>
				<td>	
					<c:out value="${resCyc.resident.adrese}" /> 	
				</td>
				<td>
					<c:choose>
						<c:when test="${resCyc.passed == true}">
							<input type="checkbox" name="passed" value="${resCyc.resident.residentPk}"
									checked  
									onchange="javascript:submitPassedChange(${resCyc.resident.residentPk}, this)" />
						</c:when>
						<c:when test="${resCyc.passed == false}">
							<input type="checkbox" name="passed" value="${resCyc.resident.residentPk}" 
									onchange="javascript:submitPassedChange(${resCyc.resident.residentPk}, this)" />
						</c:when>
					</c:choose>
				</td>
			</tr>
	</c:forEach>
	</table>
</form>

<hr>

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

<a href="/resdb/view/cycleList.htm">Atgriezties uz ciklu sarakstu</a>

</body>
</html>
