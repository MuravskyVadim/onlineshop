<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 04.07.2019
  Time: 15:07
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>Welcome to shop!!!</title>
  </head>
  <body>
  <div style="text-align: center;">
    <p>Welcome! You can register if you want! <a href="register"> Register </a></p>
    <form action="/login" method="POST">
      <p>E-mail:<br><input type="email" name="email"></p>
      <p>Password:<br><input type="password" name="password"></p>
      ${message}
      <p><button type="submit">Login</button></p>
    </form>
  </div>
  </body>
</html>
