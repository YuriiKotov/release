<%@ page import="project.domain.Client" %>
<%@ page import="java.util.List" %>
<%--
  Created by IntelliJ IDEA.
  User: yurii
  Date: 7/24/14
  Time: 8:36 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title></title>
</head>
<body>
<div class="message">
<%
    String message = (String) request.getAttribute("message");
    if (message != null) {
        out.println(message);
    }
%>
</div>
<div class="signInSuper">
    <form action="/logicSignInSuper" method="post">
        <table>
            <tr>
                <td><label>Login</label></td>
                <td><input type="text" name="login"></td>
            </tr>
            <tr>
                <td><label>Password</label></td>
                <td><input type="text" name="password"></td>
            </tr>
            <tr>
                <td colspan="2"><input type="submit" value="Enter"></td>
            </tr>
        </table>
    </form>
</div>
</body>
</html>
