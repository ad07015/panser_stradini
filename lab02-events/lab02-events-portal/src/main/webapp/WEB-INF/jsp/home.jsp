<%@ include file="/header.jsp" %>
<%@ page contentType="text/html;charset=UTF-8"%>

<br/><br/>
	
<div id="home_header" align="center">	
	<c:choose>
	<c:when test="${!empty sessionScope['user']}">
		<h1><c:out value="${sessionScope['user'].fullName}"/></h1>
		
		<div class="user_profile">
			<b>Email:</b> <c:out value="${sessionScope['user'].email}"/>
			<br/>
			<br/>
			<b>Facebook ID:</b> <c:out value="${sessionScope['user'].facebookId}"/>
		</div>
	</c:when>
	<c:otherwise>
		<h1>Welcome to Events Portal!</h1>
		<br/>
		<br/>
		<br/>
		<br/>
		<p><b>Please 
			<a style="text-decoration: underline;" href="/lab02-events-portal/events/login">login</a> or
			<a style="text-decoration: underline;" href="/lab02-events-portal/events/register">register</a>
			to enter portal.</b></p>
		<br/><br/>
	</c:otherwise>
	</c:choose>
	<br/>
</div>	

<%@ include file="/footer.jsp" %>