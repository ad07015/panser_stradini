<%@ include file="/header.jsp" %>
<%@ page contentType="text/html;charset=UTF-8"%>

<div align="center">

		<h3>Registration</h3>
		
		<!-- Spring MVC form -->
		<form:form commandName="user">
			<table cellspacing="0" cellpadding="5" id="register_form">
				<tr>
					<td colspan="2" align="center">
						<spring:hasBindErrors name="user">
							<form:errors path="*" cssStyle="color: red" />
						</spring:hasBindErrors>
						&nbsp;
					</td>
				</tr>
				<tr>
					<td nowrap><label for="username">Username:</label></td>
					<td><form:input path="username" /></td>
				</tr>
				<tr>
					<td nowrap><label for="password">Password:</label></td>
					<td><form:password path="password" /></td>
				</tr>
				<tr>
					<td nowrap><label for="passwordConfirm">Confirm Password:</label></td>
					<td><form:password path="passwordConfirm" /></td>
				</tr>
				<tr>
					<td nowrap><label for="fullName">Full Name:</label></td>
					<td><form:input path="fullName" /></td>
				</tr>
				<tr>
					<td nowrap><label for="email">Email:</label></td>
					<td><form:input path="email" /></td>
				</tr>
				<tr>
					<td nowrap><label for="facebookId">Facebook ID:</label></td>
					<td><form:input path="facebookId" /></td>
				</tr>
				<tr>
					<td colspan="2" align="center">
						<input type="submit" value="Register" />
					</td>
				</tr>
			</table>
		</form:form>
		
		<a href="/lab02-events-portal/events/login"><b>Login</b></a><br/>		
</div>

<%@ include file="/footer.jsp" %>