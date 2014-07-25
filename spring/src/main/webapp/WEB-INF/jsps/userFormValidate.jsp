<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  User: al1
  Date: 15.06.13
  Time: 8:32
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>User login form with validation</title>
    <style>
        .error {
            color: #ff0000;
        }
    </style>
</head>
<body>
<form:form method="get" commandName="user" action="/valid/userVerify">
    <table>
        <tr>
            <td><form:label path="login">Login</form:label></td>
            <td><form:input path="login"/></td>
            <td><form:errors path="login" cssClass="error"/></td>
        </tr>
        <tr>
            <td><form:label path="pass">Password</form:label></td>
            <td><form:input path="pass"/></td>
            <td><form:errors path="pass" cssClass="error"/></td>
        </tr>
        <tr>
            <td colspan="2">
                <input type="submit" value="Login"/>
            </td>
        </tr>
    </table>
</form:form>
</body>
</html>