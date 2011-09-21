<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link type='text/css' rel='stylesheet' href='/resdb/main.css' />

<script language="javascript" type="text/javascript">
function viewEmployee(eID)
{
  document.viewEmployeeForm.employeeID.value=eID
  document.viewEmployeeForm.submit()
}

function updateEmployee(eID)
{
  document.updateEmployeeForm.employeeID.value=eID
  document.updateEmployeeForm.submit()
}

function deleteEmployee(eID)
{
	var con
	con = confirm('Are you sure you want to remove this employee?')
	if (con)
	{
  		document.deleteEmployeeForm.employeeID.value=eID
		document.deleteEmployeeForm.submit()
	}
}
</script>

<title>Search Results</title>
</head>

<body>
<form name="viewEmployeeForm" action="/resdb/view/employeeDetails.htm" method="post">
	<input type="hidden" name="employeeID">
	<input type="hidden" name="searchString" value="${searchString}">
</form>
<form name="updateEmployeeForm" action="/resdb/update/employee.htm" method="post">
	<input type="hidden" name="employeeID">
</form>
<form name="deleteEmployeeForm" action="/resdb/view/employees.htm" method="post">
	<input type="hidden" name="deleteEmployee" />
	<input type="hidden" name="employeeID">
</form>

<c:if test="${fn:length(employeeList) != 0}">
	Displaying ${fn:length(employeeList)} result<c:if test="${fn:length(employeeList) != 1}">s</c:if>:
	<hr>
	<table>
		<tr>
			<th width="20%">
				Employee Name
			</th>
			<th width="20%">
				Enterprise ID
			</th>
			<th width="20%">
				Level
			</th>
			<th width="20%">
				Workforce
			</th>
			<th width="20%">
				Actions
			</th>
		</tr>
<c:forEach var="employee" items="${employeeList}">
		<tr>
			<td>
				<a href="javascript:viewEmployee(${employee.ID})"> 
					<c:out value="${employee.firstName}" /> 
					<c:out value="${employee.middleInitial}" />.
					<c:out value="${employee.lastName}" /> 
				</a>
			</td>
			<td>	
				<c:out value="${employee.enterpriseID}" /> 	
			</td>
			<td>	
				<c:out value="${employee.level}" /> 	
			</td>
			<td>	
				<c:out value="${employee.workForce}" /> 	
			</td>
			<td class="buttons">
				<button onClick="javascript:updateEmployee(${employee.ID})">Update</button>
				<button onClick="javascript:deleteEmployee(${employee.ID})">Delete</button>
			</td>
		</tr>
</c:forEach>
</table>
</c:if>

<button class="belowTable" onClick="location.href='/resdb/add/employee.htm'">Add Employee</button>
</body>
</html>