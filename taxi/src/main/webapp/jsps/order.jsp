<%@ page import="java.util.Date" %>
<%@ page import="java.util.Calendar" %>
<%@ page import="java.text.DateFormat" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.util.Random" %>
<%@ page import="project.domain.Client" %>
<%--
  Created by IntelliJ IDEA.
  User: yurii
  Date: 7/24/14
  Time: 6:33 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title></title>
</head>
<body>
    <div>
        <form action="/newOrder" method="post">
            <table>
                <tr>
                    <%
                        Client client = (Client) request.getAttribute("client");
                        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
                        Date date = new Date();
                    %>
                    <td><label>Date</label></td>
                    <td><input type="text" name="first_name" value="<%out.println(date);%>" readonly></td>
                </tr>
                <tr>
                    <td><label>Client</label></td>
                    <td><input type="text" name="last_name" value="<%out.println(client.getFirst_name());%>" readonly></td>
                </tr>

                    <td><input type="text" name="id" value="<%out.println(client.getId());%>" hidden></td>

                <tr>
                    <td><label>Address feed</label></td>
                    <td><input type="text" name="start"></td>
                </tr>
                <tr>
                    <td><label>Address destination</label></td>
                    <td><input type="text" name="finish"></td>
                </tr>
                <%
                    String cost = (String) request.getAttribute("cost");
                %>
                <tr>
                    <td><label>How it cost</label></td>
                    <td><input type="text" name="finish" value="<%out.println(cost);%>" readonly>
                    <input type="submit" value="do count"></td>
                </tr>
                <tr>
                    <td colspan="2"><input type="submit" value="Enter"></td>
                </tr>
            </table>
        </form>
    </div>
</body>
</html>
