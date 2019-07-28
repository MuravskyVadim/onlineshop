<%@ page import="model.User" %>
<%@ page import="java.io.PrintWriter" %>
<%@ page import="java.util.List" %>
<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 05.07.2019
  Time: 11:39
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Users</title>
</head>
<body>
<form >
    <table>
        <%
            PrintWriter printWriter = response.getWriter();
            printWriter.write("<center>");
            printWriter.write("<h2> Список пользователей </h2>");
            printWriter.write("<table border=\"1\">\n" +
                    "    <tr>\n" +
                    "        <th>Id</th>\n" +
                    "        <th>Email</th>\n" +
                    "        <th>Password</th>\n" +
                    "    </tr>");

            List<User> allUsers = (List<User>) request.getAttribute("users");
            for (User user : allUsers) {
                printWriter.write("<tr>");
                printWriter.write("<td>" + user.getId() + "</td>");
                printWriter.write("<td>" + user.getEmail() + "</td>");
                printWriter.write("<td>" + user.getPassword() + "</td>");
                printWriter.write("</tr>");
            }
            printWriter.write("</center>");
        %>
    </table>

</form>
<form>
    <p><input type="button" value="Goods" onClick='location.href="/all_products"'></p>
</form>
</body>
</html>
