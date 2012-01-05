<c:choose>
    <c:when test="${!empty myEvents}">
        <h2>My Events</h2>
        <table class="default_table" align="center">
            <thead>
                <tr>
                    <th>Event name</th>
                    <th>Event time</th>
                </tr>
            </thead>

            <!-- Iterate through the list of users, generate table rows -->
            <c:forEach var="event" items="${myEvents}">
                <tr>
                    <td><a href="/lab02-events-portal/events/event_view?eventId=${event.id}"><c:out value="${event.name}" /></a></td>
                    <td><fmt:formatDate pattern="dd.MM.yyyy hh:mm" value="${event.startTime}" /></td>
                </tr>
            </c:forEach>
        </table>
    </c:when>
    <c:otherwise>
        <h2>There are no my events</h2>	
    </c:otherwise>
</c:choose>