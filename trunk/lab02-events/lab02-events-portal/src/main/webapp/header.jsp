<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<html>
	<head>
		<title>Events Portal</title>
		<link rel="stylesheet" type="text/css" href="<c:url value="/css/main.css"/>" />
	</head>
	<body>
		<div id="menu">
		        <p style="text-align: center; font-size: 13pt;"><b>Events Portal</b></p>
		        <hr/>
		        <c:if test="${!empty sessionScope['user']}">
		        	<p>Welcome, <b><c:out value="${sessionScope['user'].username}"></c:out></b></p>
		        	<hr/>
		        </c:if>
		        
				<a href="/lab02-events-portal/events/home">Home</a>
				<hr/>

				<c:if test="${!empty sessionScope['user']}">
					<a href="/lab02-events-portal/events/users">People</a>
                                        <hr/>
					<a href="/lab02-events-portal/events/requests">Req. Friendships (<c:out value="${sessionScope['user_requests_count']}" />)</a>
					<hr/>
					<a href="/lab02-events-portal/events/friends">Friends</a>
					<hr/>
                                        <a href="/lab02-events-portal/events/events">Events</a>
					<hr/>
					<a href="/lab02-events-portal/events/logout">Logout</a>
				</c:if>
				
		</div>
		<div id="content">
