<%@ include file="/header.jsp" %>
<%@ page contentType="text/html;charset=UTF-8"%>

<br/>
<c:choose>
	<c:when test="${!empty requestList}">
	
		<!-- Render users page -->
		<div id="users" align="center">

		<h1>Requested Friendships</h1>
		<br />

		<table class="default_table" align="center">
			<thead>
				<tr>
					<th>Username</th>
					<th>Full Name</th>
					<th>Date</th>	
                                        <th colspan="2">Action</th>
				</tr>
			</thead>
			
			<!-- Iterate through the list of users, generate table rows -->
			<c:forEach var="request" items="${requestList}">
				<tr>
					<td width="100px"><c:out value="${request.user2.username}" /></td>
					<td width="150px"><c:out value="${request.user2.fullName}" /></td>
                                        <td width="150px"><fmt:formatDate pattern="dd.MM.yyyy hh:mm" value="${request.lastUpdated}" /></td>
					<td>
                                            <form action="/lab02-events-portal/events/requests" method="POST">
                                                <input type="hidden" name="userId" value="${request.user2.id}"/>
                                                <input type="hidden" name="response" value="accepted"/>  
                                                <input type="submit" value="Accept"/>
                                            </form>
                                        </td>
                                        <td>
                                            <form action="/lab02-events-portal/events/requests" method="POST">
                                                <input type="hidden" name="userId" value="${request.user2.id}"/>
                                                <input type="hidden" name="response" value="declined"/>
                                                <input type="submit" value="Decline"/>
                                            </form>
                                        </td>
                                </tr>
			</c:forEach>
		</table>
		<br /><br />
		</div>
	</c:when>
	<c:otherwise>
	<h2>There are no friendship requests</h2>	
</c:otherwise>
</c:choose>

<!-- Link back to home page -->
<p align="center">
<a href="/lab02-events-portal/events/home">
	<b>Back to Home Page</b>
</a></p>

<%@ include file="/footer.jsp" %>