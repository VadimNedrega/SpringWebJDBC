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
        <th>Description</th>
        <th>Creation Date</th>
        <th>Set_id</th>

    </tr>

    <c:forEach var="kpac" items="${kpacs}">

        <c:url var="deleteButton" value="/kpacs/${kpac.id}">

        </c:url>
        <tr>
            <td>${kpac.id}</td>
            <td>${kpac.title}</td>
            <td>${kpac.description}</td>
            <td>${kpac.creationDate}</td>
            <td>${kpac.kpacSet}</td>
            <td>
                <input type="button" value="Delete" onclick="window.location.href = '${deleteButton}'"/>
                <input type="button" value="Add to Set" onclick="window.location.href = '${pageContext.request.contextPath}/kpacs/${kpac.id}/addToSet'"/>
            </td>
        </tr>

    </c:forEach>


</table>

<br>
<input type="button" value="Add" onclick="window.location.href = 'add-new-kpac'" >
<br>

<h3>Choose option to sort</h3>

<input type="button" value="Sort by id" onclick="window.location.href = 'sort-kpac-by-id'" >
<input type="button" value="Sort by Title" onclick="window.location.href = 'sort-kpac-by-title'" >
<input type="button" value="Sort by Description" onclick="window.location.href = 'sort-kpac-by-description'" >
<input type="button" value="Sort by Date" onclick="window.location.href = 'sort-kpac-by-date'" >

<br><br>

<h3>Choose option to filter</h3>

<form action="${pageContext.request.contextPath}/filter-kpac-by" method="post">
    <input type="radio" value="id" name="filterBy"> filter by id
    <input type="radio" value="Title" name="filterBy"> filter by title
    <input type="radio" value="Description" name="filterBy"> filter by description
    <input type="radio" value="Creation_date" name="filterBy"> filter by creating_date
    <br><br>
    <input type="text" name="filterValue" value="">
    <input type = "submit" value = "submit"/>
</form>

<br>

<input type="button" onclick="window.location.href = 'kpacs';" value="Come back to kpacs page"/>
<br><br>
<input type="button" value="Go to set page" onclick="window.location.href = 'set'" >
<br><br>
<input type="button" value="Go to Start page" onclick="window.location.href = '${pageContext.request.contextPath}/'" >

</body>
</html>