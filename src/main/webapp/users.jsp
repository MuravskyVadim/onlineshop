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
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>All users</title>
</head>
<body>
<div align="center">
    <form action="/admin/users" method="post">
        <table align="top" border="1" cellpadding="4" cellspacing="0">
            <caption>Users list</caption>
            <tr>
                <th>Id</th>
                <th>Name</th>
                <th>Password</th>
                <th>Role</th>
                <th></th>
                <th></th>
            </tr>
            <c:forEach items="${users}" var="user">
                <tr>
                    <td>${user.id}</td>
                    <td>${user.email}</td>
                    <td>${user.password}</td>
                    <td>${user.role}</td>
                    <td><a href="/admin/user?id=${user.id}">edit</a></td>
                    <td><a href="/admin/user/delete?id=${user.id}">delete</a></td>
                </tr>
            </c:forEach>
        </table>
    </form>
    <p><input type="button" value="Goods" onClick='location.href="/products"'></p>
</div>
</body>
</html>
