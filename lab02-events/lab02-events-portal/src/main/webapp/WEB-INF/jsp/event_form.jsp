<%@ include file="/header.jsp" %>
<%@ page contentType="text/html;charset=UTF-8"%>

<br/><br/>

<div id="home_header" align="center">
    <c:choose>
        <c:when test="${editing == true}">
            <h2>Update Event</h2>
        </c:when>
        <c:otherwise>
            <h2>Create Event</h2>	
       </c:otherwise>
    </c:choose>
    <form:form commandName="eventForm" action="/lab02-events-portal/events/event_form" cssClass="event" method="post">
       <form:hidden path="id" />
       <form:hidden path="privateType" />
       <table>
            <tr>
                <td><label for="name">Name</label></td>
                <td><form:input path="name" readonly="${readonly}" /></td>
                <td><form:errors path="name" cssClass="error" /></td> 
                <!-- TODO: make column width fixed -->
            </tr>
            <tr>
                <td><label for="name">Description</label></td>
                <td><form:input path="description" readonly="${readonly}" /></td>
                <td><form:errors path="description" cssClass="error" /></td>
            </tr>
            <tr>
                <td><label for="name">Location</label></td>
                <td><form:input path="location" readonly="${readonly}" /></td>
                <td><form:errors path="location" cssClass="error" /></td>
            </tr>
            <tr>
                <td><label for="name">Phone</label></td>
                <td><form:input path="contactPhone" readonly="${readonly}" /></td>
                <td><form:errors path="contactPhone" cssClass="error" /></td>
            </tr>
            <tr>
                <td><label for="name">Start Time</label></td>
                <td><form:input path="startTime" readonly="${readonly}" /></td>
                <td><form:errors path="startTime" cssClass="error" /></td>
            </tr>
            <tr>
                <td><label for="name">End Time</label></td>
                <td><form:input path="endTime" readonly="${readonly}" /></td>
                <td><form:errors path="endTime" cssClass="error" /></td>
            </tr>
            <c:choose>
                <c:when test="${eventForm.privateType == true}">
                    <tr>
                        <td>Access Code</td>
                        <td>
                            <form:input path="accessCode" readonly="${readonly}" />
                            <div style="font-size:11px">Input access code to make private event</div>
                        </td>
                        <td><form:errors path="accessCode" cssClass="error" /></td>
                    </tr>
                </c:when>
            </c:choose>

            <c:choose>
                <c:when test="${readonly != true && !empty friendList}">
                    <tr>
                        <td>Invitees</td>
                        <td><form:checkboxes items="${friendList}" itemLabel="fullName" itemValue="id"
                                         path="inviteList"  delimiter="<br/>"/></td> <!-- add separator with line break -->
                        <td><form:errors path="inviteList" cssClass="error" /></td>
                    </tr>
            </c:when>
            </c:choose>
                    
            <c:choose>
            <c:when test="${readonly != true}">
                 <tr>
                    <td colspan="3" align="center">
                         <c:choose>
                            <c:when test="${editing == true}">
                                <input type="submit" value="Update"/>
                            </c:when>
                            <c:otherwise>
                               <input type="submit" value="Save"/>
                            </c:otherwise>
                        </c:choose>
                    </td>
                </tr>
            </c:when>
             </c:choose>
        </table>
    </form:form>
    
    <c:choose>
    <c:when test="${editing == true}">
        <c:choose>
        <c:when test="${!empty event_invitees}">
            <h2>Invited friends</h2>
            <table class="default_table" align="center">
                <thead>
                    <tr>
                        <th>Username</th>
                        <th>Full Name</th>
                        <th>Status</th>
                    </tr>
                </thead>
                <c:forEach var="invite" items="${event_invitees}">
                    <tr>
                        <td><c:out value="${invite.invited.username}" /></td>
                        <td><c:out value="${invite.invited.fullName}" /></td>
                        <td><c:out value="${invite.status}" /></td>
                    </tr>
                </c:forEach>
            </table>
        </c:when>
        <c:otherwise>
            <h2>No invited friends</h2>	
        </c:otherwise>
        </c:choose>
    </c:when>
    </c:choose>
                
    <br/>
</div>	

<%@ include file="/footer.jsp" %>