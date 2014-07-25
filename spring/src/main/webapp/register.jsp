<%--
  Created by IntelliJ IDEA.
  User: Юрий
  Date: 09.07.2014
  Time: 12:54
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Register</title>
    <link type="text/css" rel="stylesheet" href="styleS.css">
</head>
<body>
    <div class="register">
    <h5>
    <%
        String error = (String)request.getAttribute("error");
        if (error != null) {
            out.println(error);
        }
        String pasError = (String) request.getAttribute("pasError");
        if (pasError != null) {
            out.println(pasError);
        }
    %>
    </h5>
    <form action="/dbUsers" class="form" method="GET">
        <label><strong>Login</strong></label><br>
        <input type="text" name="login"><br>
        <label><strong>Password</strong></label><br>
        <input type="text" name="password1"><br>
        <label><strong>Confirm password</strong></label><br>
        <input type="text" name="password2"><br>
        <input id="submit" type="submit" value="Enter"><br>
    </form>
    </div>
</body>
</html>
