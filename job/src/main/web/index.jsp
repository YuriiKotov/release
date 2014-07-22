<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%--
  Created by IntelliJ IDEA.
  User: Юрий
  Date: 16.07.2014
  Time: 17:04
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Some site</title>
    <link rel="stylesheet"  href="style.css" type="text/css">
    <%!
        List<String> map = new ArrayList<String>();
    %>
    <%
        map = (List<String>)request.getAttribute("val");
        if (map == null) {
            request.getRequestDispatcher("/logic").forward(request, response);
        }
    %>
</head>
<body>

    <div>
        <p id="first"><% out.println(map.get(2)); %></p>
        <p id="second"><% out.println(map.get(3)); %></p>
        <p id="third"><% out.println(map.get(4)); %></p>
        <p id="fourth"><% out.println(map.get(5)); %></p>
        <p id="fifth"><% out.println(map.get(6)); %></p>
    </div>
<%
    map = null;
%>
</body>
</html>