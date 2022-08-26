<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<body>

<h2>List KPACS</h2>
<br><br>

<table>
    <tr>
        <th>Id</th>
        <th>Title</th>

    </tr>
    <c:forEach var="kpacSet" items="${set}">

        <c:url var="choose" value="/kpacs/${kpacId}/addToSet/${kpacSet.id}">

        </c:url>
        <tr>
            <td>${kpacSet.id}</td>
            <td>${kpacSet.title}</td>
            <td>
                <input type="button" value="Choose" onclick="window.location.href = '${choose}'"/>
            </td>
        </tr>

    </c:forEach>


</table>

</body>
</html>