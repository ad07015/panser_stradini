<c:choose>
    <c:when test="${!empty invitations}">
        <h2>Invitations</h2>
        <table class="default_table" align="center" id="table_invitations">
            <thead>
                <tr>
                    <th>Event name</th>
                    <th>Event time</th>
                    <th>Author</th>	
                    <th>Invite time</th>
                    <th colspan="2">Action</th>
                </tr>
            </thead>

            <!-- Iterate through the list of users, generate table rows -->
            <c:forEach var="invitation" items="${invitations}">
                <tr>
                    <td><a href="/lab02-events-portal/events/event_view?eventId=${invitation.event.id}"><c:out value="${invitation.event.name}" /></a></td>
                    <td><fmt:formatDate pattern="dd.MM.yyyy hh:mm" value="${invitation.event.startTime}" /></td>
                    <td><c:out value="${invitation.creator.fullName}" /></td>
                    <td><fmt:formatDate pattern="dd.MM.yyyy hh:mm" value="${invitation.createdDate}" /></td>
                    <td>
                        <form method="POST" action="/lab02-events-portal/events">
                            <input type="hidden" name="eventId" value="${invitation.event.id}"/>
                            <input type="hidden" name="response" value="attend"/>
                            <input type="submit" value="Attend"/>
                        </form>
                    </td>
                    <td>
                        <form method="POST" action="/lab02-events-portal/events">
                            <input type="hidden" name="eventId" value="${invitation.event.id}"/>
                            <input type="hidden" name="response" value="decline"/>
                            <input type="submit" value="Decline"/>
                        </form>
                    </td>
                </tr>
            </c:forEach>
        </table>
    </c:when>
    <c:otherwise>
        <h2>There are no Invitations</h2>	
    </c:otherwise>
</c:choose>