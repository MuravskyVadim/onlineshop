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
    ${message}
    <form action="/register" method="POST">
        <p>E-mail:<br><input type="email" name="email" value="${email}"></p>
        <p>Password:<br><input type="password" name="password"></p>
        <p>Repeat password:<br><input type="password" name="repeatPassword"></p>
        <p><button type="submit">Register</button></p>
    </form>
</div>
</body>
</html>
