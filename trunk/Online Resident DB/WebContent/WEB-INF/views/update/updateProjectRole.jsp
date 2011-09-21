<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link type='text/css' rel='stylesheet' href='/resdb/main.css' />

<script type="text/javascript" src="/resdb/js/validators/constants.js"></script>
<script type="text/javascript" src="/resdb/js/validators/projectRole.js"></script>

<link type="text/css" href="/resdb/js/smoothness/ui.css" rel="stylesheet" />
<script type="text/javascript" src="/resdb/js/jquery.js"></script>
<script type="text/javascript" src="/resdb/js/ui.js"></script>
<script type="text/javascript">
$(function() {
	$("#startDate").datepicker({ dateFormat: 'yy-mm-dd', onSelect: valStartDate })
})

$(function() {
	$("#endDate").datepicker({ dateFormat: 'yy-mm-dd', onSelect: valEndDate })
})
</script>

<title>Update Project Role</title>
</head>

<body onload="addListeners()">
<h1><a href="/resdb/">Online Employee DB</a></h1>
<c:out value="${message}" escapeXml="false" />

<hr>
Enter the updated details of this role. The name and start date fields are obligatory.
<hr>

<form class="bordless" name="updateProjectRoleForm" method="post" onsubmit="return valProjectRole()">
<input type="hidden" name="projectRoleID" value="${projectRole.ID}" />
<input type="hidden" name="employeeID" value="${employeeID}" />
<table class="bordless">
	<tr>
		<td width="160px">Role Name</td>
		<td width="1px">
			<select name="roleID">
				<c:forEach var="role" items="${roleList}">
				  	
					<c:choose>
						<c:when test="${role.role == projectRole.role}">
							<option selected value="${role.ID}">${role.role}</option>
						</c:when>
						<c:otherwise>
							<option value="${role.ID}">${role.role}</option>
						</c:otherwise>
					</c:choose>
					
				</c:forEach>	
			</select>
		</td>
		<td></td>
	</tr>
	<tr>
		<td>Start Date</td>
		<td colspan="2">
			<input id="startDate" class="text" type="text" name="startDate" value="${projectRole.startDate}" maxlength="10" />
			<span id="errorStartDate" class="error"></span>
		</td>
	</tr>
	<tr>
		<td>End Date</td>
		<td colspan="2">
			<input id="endDate" class="text" type="text" name="endDate"  value="${projectRole.endDate}" maxlength="10" />
			<span id="errorEndDate" class="error"></span>
		</td>
	</tr>
	<tr>
		<td></td>
		<td><input class="submit" type="submit" name="updateProjectRole" value="Submit" /></td>
	</tr>
</table>
</form>

<hr>

<a href="/resdb/view/employeeDetails.htm?employeeID=${employeeID}">Back to details page</a>
</body>
</html>