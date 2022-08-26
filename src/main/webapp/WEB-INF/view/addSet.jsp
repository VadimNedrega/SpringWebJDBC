<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<body>

<h2>KPAC info</h2>
<br><br>

<form:form action="save-set" modelAttribute = "set">

    <form:hidden path="id"/>
    <br><br>
    Title: <form:input path="title"/>
    <br><br>
    <input type="submit" value="OK">

</form:form>
</body>
</html>