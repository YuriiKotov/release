<%--
  Created by IntelliJ IDEA.
  User: yurii
  Date: 7/16/14
  Time: 6:53 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Authorization</title>
    <link rel="stylesheet"  href="style.css" type="text/css">
</head>
<body>
    <div>
        <%
            String str = (String) request.getAttribute("error");
            if (str != null) {
                out.println(str);
            }
        %>
        <form action="/auto" method="post">
            <label>Login</label>
            <input type="text" name="login"><br>
            <label>Password</label>
            <input type="text" name="password"><br>
            <input type="submit" value="Enter">
        </form>
    </div>
</body>
</html>
