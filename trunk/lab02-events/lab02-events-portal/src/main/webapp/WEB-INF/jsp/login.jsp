<%@ include file="/header.jsp" %>
<%@ page contentType="text/html;charset=UTF-8"%>

<div align="center">

		<h3>Enter your username and password.</h3>
		
		<!-- Spring MVC form -->
		<form:form commandName="user">
			<table cellspacing="5px" id="login_form">
				<tr>
					<td><label for="username">Username</label>:</td>
					<td><form:input path="username" /></td>
				</tr>
				<tr>
					<td><label for="password">Password</label>:</td>
					<td><form:password path="password" /></td>
				</tr>
				<tr><td colspan="2">
					<spring:hasBindErrors name="user">
						<form:errors path="*" cssStyle="color: red" />
					</spring:hasBindErrors>
				</td></tr>
				<tr><td colspan="2" align="center">
					<input type="submit" value="Login" />
				</td></tr>
			</table>
		</form:form>
		
		<a href="/lab02-events-portal/events/register"><b>Register</b></a><br/>		
</div>

<%@ include file="/footer.jsp" %>