<%@ page import="java.io.PrintWriter" %>
<%@ page import="model.User" %>
<%@ page import="model.Product" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: User
  Date: 04.07.2019
  Time: 19:48
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>All products</title>
</head>
<body>
<div align="center">
    <form action="/products" method="post">
        <table align="top" border="1" cellpadding="4" cellspacing="0">
            <caption>Products list</caption>
            <tr>
                <th>Id</th>
                <th>Name</th>
                <th>Description</th>
                <th>Price</th>
                <th></th>
                <th></th>
            </tr>
            <c:forEach items="${products}" var="product">
                <tr>
                    <td>${product.id}</td>
                    <td>${product.name}</td>
                    <td>${product.description}</td>
                    <td>${product.price}</td>
                    <td><a href="/product?id=${product.id}">edit</a></td>
                    <td><a href="/product/delete?id=${product.id}">delete</a></td>
                </tr>
            </c:forEach>
        </table>
    </form>
    <p><input type="button" class="inline" value=" Users " onClick='location.href="/admin/users"'
        <c:if test="${user.role == 'user'}"> disabled </c:if>>
        <input type="button" class="inline" value=" Add item " onClick='location.href="/add_product"'>
    </p>
</div>
</body>
</html>
