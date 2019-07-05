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
<html>
<head>
    <title>All products</title>
</head>
<body>
<table>
    <%
        PrintWriter printWriter = response.getWriter();
        printWriter.write("<center>");
        printWriter.write("<h2> Список товаров </h2>");
        printWriter.write("<table border=\"1\">\n" +
                "    <tr>\n" +
                "        <th>Id</th>\n" +
                "        <th>Name</th>\n" +
                "        <th>Description</th>\n" +
                "        <th>Price</th>\n" +
                "    </tr>");

        List<Product> allProducts = (List<Product>) request.getAttribute("all_products");
        for (Product product : allProducts) {
            printWriter.write("<tr>");
            printWriter.write("<td>" + product.getId() + "</td>");
            printWriter.write("<td>" + product.getName() + "</td>");
            printWriter.write("<td>" + product.getDescription() + "</td>");
            printWriter.write("<td>" + product.getPrice() + "</td>");
            printWriter.write("</tr>");
        }
        printWriter.write("</center>");
    %>
</table>
<form>
    <p><input type="button" class="inline" value=" Users " onClick='location.href="/users"'>
    <input type="button" class="inline" value=" Add item  " onClick='location.href="/add_product"'></p>
</form>
</body>
</html>
