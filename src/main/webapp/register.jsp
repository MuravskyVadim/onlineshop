<%@ page import="java.io.Writer" %><%--
  Created by IntelliJ IDEA.
  User: User
  Date: 04.07.2019
  Time: 18:38
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Registration</title>
</head>
<body>
<div align="center">
    <%
        Writer writer = response.getWriter();
        writer.write("<h4><center>");
        String message = (String) request.getAttribute("message");
        if (message != null) {
            writer.write(message);
        }
        writer.write("</h4></center>");
    %>

    <form action="/register" method="POST">
        <p>E-mail: <input type="email" name="email" value="
            <%=
                request.getParameter("email") != null ? request.getParameter("email") : ""
            %>">
        </p>
        <p>Password: <input type="password" name="password"></p>
        <p>Repeat: <input type="password" name="repeatPassword"></p>
        <p><button type="submit">Register</button></p>
    </form>
</div>
</body>
</html>
