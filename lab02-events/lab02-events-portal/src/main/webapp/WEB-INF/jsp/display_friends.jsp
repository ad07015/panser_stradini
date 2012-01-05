<%@ include file="/header.jsp" %>
<%@ page contentType="text/html;charset=UTF-8"%>

<br/>
<c:choose>
	<c:when test="${!empty friendList}">
	
		<!-- Render users page -->
		<div id="users" align="center">

		<h1>Friends</h1>
		<br />

		<table class="default_table" align="center">
			<thead>
				<tr>
					<th>Username</th>
					<th>Full Name</th>
					<th>Email</th>
				</tr>
			</thead>
			
			<!-- Iterate through the list of users, generate table rows -->
			<c:forEach var="friend" items="${friendList}">
				<tr>
					<td width="100px"><c:out value="${friend.username}" /></td>
					<td width="250px"><c:out value="${friend.fullName}" /></td>
					<td width="250px"><a href="mailto:<c:out value="${friend.email}" />"><c:out value="${friend.email}" /></a> </td>	
                                </tr>
			</c:forEach>
		</table>
		<br /><br />
		</div>
	</c:when>
	<c:otherwise>
	<h2>This user has no friends</h2>	
</c:otherwise>
</c:choose>

<!-- Link back to home page -->
<p align="center">
<a href="/lab02-events-portal/events/home">
	<b>Back to Home Page</b>
</a></p>

<%@ include file="/footer.jsp" %>