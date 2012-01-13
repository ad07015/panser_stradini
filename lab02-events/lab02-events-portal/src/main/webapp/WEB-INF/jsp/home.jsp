<%@ include file="/header.jsp" %>
<%@ page contentType="text/html;charset=UTF-8"%>

<br/><br/>
	
<div id="home_header" align="center">	
        <table class="default_table" align="center" id="table_attendings">
            <thead>
                <tr>
                    <th>First name</th>
                    <th>Last name</th>
                </tr>
            </thead>
            <!-- Iterate through the list of users, generate table rows -->
            <c:forEach var="attending" items="${topPlayerTableRowList}">
                <tr>
                    <td><c:out value="${attending.firstName}" /></td>
                    <td><c:out value="${attending.lastName}" /></td>
                </tr>
            </c:forEach>
        </table>
</div>	

<%@ include file="/footer.jsp" %>