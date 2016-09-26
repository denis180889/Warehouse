<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
    <title>Spring MVC Form Handling</title>
</head>
<body>


<h2>Warehouse Information</h2>
<table>
    <c:forEach var="warehouse" items="${warehouses}">
    <tr>
        <td>${warehouse.id}</td>
        <td>${warehouse.name}</td>
        <td>${warehouse.description}</td>
        <td>
            </c:forEach>
</table>

<c:if test="${admin}">

<jsp:include page="addWarehouse.jsp"></jsp:include>

</c:if>

</body>
</html>