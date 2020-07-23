
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Update Student</title>
</head>
<body>
<h2>Update Student</h2>
<form action = "StudentControllerServlet" method = "GET">
    <input type ="hidden" name= "command" value = "UPDATE">
    <input type ="hidden" name= "studentId" value = "${THE_STUDENT.id}">
    <table>
        <tbody>
        <tr>
            <td><label>First name: </label></td>
            <td><input type= "text" name = "firstName" value = "${THE_STUDENT.firstName}"/></td>
        </tr>
        <tr>
            <td><label>Last name: </label></td>
            <td><input type= "text" name = "lastName" value = "${THE_STUDENT.lastName}"/></td>
        </tr>
        <tr>
            <td><label>Email: </label></td>
            <td><input type= "text" name = "email" value = "${THE_STUDENT.email}"/></td>
        </tr>
        <tr>
            <td><label></label></td>
            <td><input type="submit" value = "Save" class = "save" /></td>
        </tr>
        </tbody>
    </table>
</form>

<p>
    <a href = "StudentControllerServlet"> Back to List </a>
</p>

</body>
</html>
