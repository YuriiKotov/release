<%@ page import="java.util.Map" %>
<%@ page import="java.util.HashMap" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%--
  Created by IntelliJ IDEA.
  User: yurii
  Date: 7/16/14
  Time: 6:19 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Admin</title>
    <link rel="stylesheet"  href="style.css" type="text/css">
    <%!
        List<String> map = new ArrayList<String>();
        List<String> keys = new ArrayList<String>();
        String accept;
    %>
</head>
<body>
    <%
        accept = (String) request.getAttribute("accept");
        if (accept == null) {
            request.getRequestDispatcher("authorization.jsp").forward(request, response);
        } else {
            map = (List<String>) request.getAttribute("map");
            keys = (List<String>) request.getAttribute("keys");
        }
    %>
    <div>
        <form action="/save" method="post">
        <%
            for (int i = 2; i < map.size(); i++) {
                out.println(keys.get(i) + "<input type=\"text\" value=\"" + map.get(i) + "\" name=\"" + keys.get(i) + "\">" + "<br>");
            }
        %>
            <input type="submit" value="Enter">
        </form>
    </div>
</body>
</html>
