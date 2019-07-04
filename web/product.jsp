<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 04.07.2019
  Time: 19:48
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Add product</title>
</head>
<body>
<div align="center">
    ${message}
    <form action="product" method="POST">
        <p>Name: <input type="text" name="name"></p>
        <p>Description: <input type="text" name="description"></p>
        <p>Price: <input type="number" name="price" value="0" min="0" step="0.01"></p>
        <P><button type="submit">Submit</button></P>
    </form>
</div>
</body>
</html>
