<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<body>

<h2>K-PAC-Sets</h2>
<br>
<h4>To see set details please click on title</h4>
<br>

<table>
    <tr>
        <th>Id</th>
        <th>Title</th>

    </tr>

    <c:forEach var="set" items="${set}">

        <c:url var="deleteButton" value="/setDelete/${set.id}">

        </c:url>
        <tr>
            <td>${set.id}</td>
            <td><a href="${pageContext.request.contextPath}/set/${set.id}">${set.title}</a></td>
            <td>
                <input type="button" value="Delete" onclick="window.location.href = '${deleteButton}'"/>
            </td>
        </tr>

    </c:forEach>


</table>

<br>
<input type="button" value="Add" onclick="window.location.href = 'add-new-set'">
<br>

<h3>Choose option to sort</h3>

<input type="button" value="Sort by id" onclick="window.location.href = 'sort-set-by-id'">
<input type="button" value="Sort by Title" onclick="window.location.href = 'sort-set-by-title'">

<br><br>

<h3>Choose option to filter</h3>

<form action="${pageContext.request.contextPath}/filter-set-by" method="post">
    <input type="radio" value="id" name="filterBy"> filter by id
    <input type="radio" value="Title" name="filterBy"> filter by title
    <br>
    <input type="text" name="filterValue" value="">
    <input type="submit" value="submit"/>
</form>

<br>

<input type="button" value="Come back to set page" onclick="window.location.href = 'set'">

<br><br>

<input type="button" value="Go to kpacs page" onclick="window.location.href = 'kpacs'">

<br><br>

<input type="button" value="Go to Start page" onclick="window.location.href = '${pageContext.request.contextPath}/'">

</body>
</html>