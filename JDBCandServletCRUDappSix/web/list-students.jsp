<%@ taglib uri= "http://java.sun.com/jsp/jstl/core" prefix= "c" %>
<%@ page import = "java.util.*, tracker.*" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>List Students Page</title>
</head>
<body>

<a href="add-student-form.jsp">Add Student</a>

<table>
    <tr>
        <th>First Name </th>
        <th>Last Name </th>
        <th>Email </th>
        <th>Action</th>
    </tr>

    <c:forEach var="tempStudent" items= "${STUDENT_LIST}">


        <c:url var = "tempLink" value= "StudentControllerServlet">
            <c:param name= "command" value= "LOAD" />
            <c:param name= "studentId" value= "${tempStudent.id}" />
        </c:url>
        <c:url var = "deleteLink" value= "StudentControllerServlet">
            <c:param name= "command" value= "DELETE" />
            <c:param name= "studentId" value= "${tempStudent.id}"/>
        </c:url>

        <tr>
            <td>${tempStudent.firstName }</td>
            <td>${tempStudent.lastName }</td>
            <td>${tempStudent.email }</td>
            <td> <a href ="${tempLink}">Update</a> | <a
                    href ="${deleteLink}" onclick = "if (!(confirm(
                        'Are you sure you want to delete this student?'))) return false">Delete</a></td>
        </tr>
    </c:forEach>
</table>
<p>
    <a href="${pageContext.request.contextPath}/StudentControllerServlet">Return to Home</a>
</p>
</body>
</html>
