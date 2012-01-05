<c:choose>
    <c:when test="${!empty attendings}">
        <h2>Attending</h2>
        <table class="default_table" align="center" id="table_attendings">
            <thead>
                <tr>
                    <th>Event name</th>
                    <th>Author</th>
                    <th>Event time</th>
                </tr>
            </thead>
            <!-- Iterate through the list of users, generate table rows -->
            <c:forEach var="attending" items="${attendings}">
                <tr>
                    <td><a href="/lab02-events-portal/events/event_view?eventId=${attending.id}"><c:out value="${attending.name}" /></a></td>
                    <td><c:out value="${attending.owner.fullName}" /></td>
                    <td><fmt:formatDate pattern="dd.MM.yyyy hh:mm" value="${attending.startTime}" /></td>
                </tr>
            </c:forEach>
        </table>
    </c:when>
    <c:otherwise>
        <h2>There are no attendings</h2>	
    </c:otherwise>
</c:choose>