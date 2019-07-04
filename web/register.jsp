<%--
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
    <h4>${message}</h4>
    <form action="register" method="POST">
        <p>E-mail: <input type="email" name="email"></p>
        <p>Password: <input type="password" name="password"></p>
        <p>Repeat: <input type="password" name="repeatPassword"></p>
        <P><button type="submit">Register </button></P>
    </form>
</div>
</body>
</html>
