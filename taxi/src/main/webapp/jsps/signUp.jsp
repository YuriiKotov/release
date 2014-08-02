<%@ page import="project.domain.Client" %>
<%@ page import="java.util.List" %>
<%--
  Created by IntelliJ IDEA.
  User: yurii
  Date: 7/24/14
  Time: 2:58 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Registration form for Clients</title>
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
<div class="regClient">
    <form action="/logicSignUp" method="post">
        <table>
            <tr>
                <td><label>Login*</label></td>
                <td><input type="text" name="login"></td>
            </tr>
            <tr>
                <td><label>First name*</label></td>
                <td><input type="text" name="first_name"></td>
            </tr>
            <tr>
                <td><label>Last name</label></td>
                <td><input type="text" name="last_name"></td>
            </tr>
            <tr>
                <td><label>Mobile number*</label></td>
                <td><input type="text" name="mobile"></td>
            </tr>
            <tr>
                <td><label>Address</label></td>
                <td><input type="text" name="address"></td>
            </tr>
            <tr>
                <td><label>Password*</label></td>
                <td><input type="text" name="password"></td>
            </tr>
            <tr>
                <td><label>Submit Password*</label></td>
                <td><input type="text" name="submit_password"></td>
            </tr>
            <tr>
                <td colspan="2"><input type="submit" value="Enter"></td>
            </tr>
            <tr>
                <td colspan="2">* - Поля обязательные для ввода</td>
            </tr>
        </table>
    </form>
</div>
</body>
</html>
