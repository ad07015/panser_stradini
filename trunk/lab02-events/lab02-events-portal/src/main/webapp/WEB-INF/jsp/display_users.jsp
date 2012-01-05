<%@ include file="/header.jsp" %>
<%@ page contentType="text/html;charset=UTF-8"%>

<br/>
<c:choose>
    <c:when test="${!empty friendshipList}">

        <!-- Render users page -->
        <div id="users" align="center">

            <h1>People</h1>
            <br />

            <table class="default_table" align="center">
                <thead>
                    <tr>
                        <th>Username</th>
                        <th>Full Name</th>
                        <th>Email</th>	
                        <th>Status</th>
                    </tr>
                </thead>

                <!-- Iterate through the list of users, generate table rows -->
                <c:forEach var="fr" items="${friendshipList}">
                    <tr>
                        <td width="100px"><c:out value="${fr.user2.username}" /></td>
                        <td width="250px"><c:out value="${fr.user2.fullName}" /></td>
                        <td width="250px"><a href="mailto:<c:out value="${fr.user2.email}" />"><c:out value="${fr.user2.email}" /></a> </td>	
                        <td>
                            <c:choose>
                                <c:when test="${fr.status == 'REQUESTED'}">
                                    <span>Friendship request sent</span>
                                </c:when>
                                <c:when test="${fr.status == 'ACCEPTED'}">
                                    <span>Friend</span>
                                </c:when>
                                <c:when test="${fr.status == 'DECLINED'}">
                                    <span>Friendship declined</span>
                                </c:when>
                                <c:otherwise>
                                    <span>
                                        <form method="POST" action="/lab02-events-portal/events/user_invite">
                                            <input type="hidden" name="userId" value="${fr.user2.id}"/>
                                            <input type="submit" value="Invite"/>
                                        </form>
                                    </span>
                                </c:otherwise>
                            </c:choose>
                        </td>
                    </tr>
                </c:forEach>
            </table>
            <br /><br />
        </div>
    </c:when>
    <c:otherwise>
        <h2>There are no people in a portal</h2>	
    </c:otherwise>
</c:choose>

<!-- Link back to home page -->
<p align="center">
    <a href="/lab02-events-portal/events/home">
        <b>Back to Home Page</b>
    </a></p>

<%@ include file="/footer.jsp" %>