<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Confirm code</title>
</head>
<body>
<div style="text-align: center;">
    <form action="/user/code" method="POST">
        <p>Confirm code:<br><input type="password" name="code"></p>
        ${message}
        <p><button type="submit">Confirm</button></p>
    </form>
</div>
</body>
</html>
