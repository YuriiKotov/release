<%--
  Created by IntelliJ IDEA.
  User: al1
  Date: 3/16/13
  Time: 5:49 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title></title>
</head>
<body>
    <c:forEach var="employee" items="${employees}">
        <c:out value="${employee}"/><br/>
    </c:forEach>
</body>
</html>