<%@ include file="/header.jsp" %>
<%@ page contentType="text/html;charset=UTF-8"%>

<!--Pasākuma nosaukums (saite, nospiežot uz kuras ir iespējams aiziet uz pasākuma lapu)
Pasākuma sākuma laiks
Ielūguma autors
Ielūguma nosūtīšanas laiks
Iespēja pieņemt ielūgumu (poga vai saite "Attend")
Iespēja noraidīt ielūgumu (poga vai saite "Decline")
-->
<br/>
<center>
    <a href="/lab02-events-portal/events/event_private_form"><c:out value="Create new private event" /></a><br/>
    <a href="/lab02-events-portal/events/event_public_form"><c:out value="Create new public event" /></a>
    <%@ include file="display_events_invitations.jsp" %>
    <%@ include file="display_events_my.jsp" %>
    <%@ include file="display_events_attending.jsp" %>
</center>