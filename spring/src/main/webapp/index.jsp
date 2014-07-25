
<%--
  Created by IntelliJ IDEA.
  User: Юрий
  Date: 09.07.2014
  Time: 10:30
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>auto</title>
    <link rel="stylesheet"  href="styleS.css" type="text/css">
</head>
<body>

<div class="register">
    <h5 style="color: white; font-size: 18px">
        <%
        String str = (String) request.getAttribute("accept");
        if (str != null) {
            out.println(str);
        }
        String str1 = (String) request.getAttribute("errorLogin");
        if (str1 != null) {
            out.println(str1);
        }
    %>
    </h5>
    <form class="form" action="/dbUsers" method="GET">
        <label id="label1"><strong>Login</strong></label>
        <input type="text" name="login"><br>
        <label id="label2"><strong>Password</strong></label>
        <input type="text" name="password"><br>
        <input id="submit" type="submit" value="Enter"><br>
        <p>New person?</p>
    </form>
    <div id="pp" >
        <a id="aa" href="register.jsp"><h5><strong>Register</strong></h5></a>
    </div>
</div>
</body>
</html>