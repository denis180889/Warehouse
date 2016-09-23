<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>Log in</title>
</head>
<body>
    <c:url var="loginUrl" value="/j_spring_security_check"></c:url>
    <form action="${loginUrl}" method="POST">
        <h2>Please sign in</h2>
        <input type="text" name="j_username" />
        <input type="password" name="j_password" />
        <button type="submit">Войти</button>
    </form>

</body>
</html>
