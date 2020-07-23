<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Adding a student</title>
</head>
<body>

<form action = "StudentControllerServlet" method = "GET">
    <input type ="hidden" name= "command" value = "ADD">

    <table>
        <tbody>
        <tr>
            <td><label>First name: </label></td>
            <td><input type= "text" name = "firstName" /></td>
        </tr>
        <tr>
            <td><label>Last name: </label></td>
            <td><input type= "text" name = "lastName" /></td>
        </tr>
        <tr>
            <td><label>Email: </label></td>
            <td><input type= "text" name = "email" /></td>
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
