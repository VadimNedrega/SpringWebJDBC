<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<body>

<h2>Set Details</h2>
<br><br>
<h3>Set includes next KPACs`:</h3>
<br>

<table>
    <tr>
        <th>Id</th>
        <th>Title</th>
        <th>Description</th>
        <th>Creation Date</th>

    </tr>

    <c:forEach var="kpac" items="${kpacs}">

        <tr>
            <td>${kpac.id}</td>
            <td>${kpac.title}</td>
            <td>${kpac.description}</td>
            <td>${kpac.creationDate}</td>
        </tr>

    </c:forEach>


</table>

<br>

<input type="button" value="Come back to set page" onclick="window.location.href = '${pageContext.request.contextPath}/set'" >

</body>
</html>