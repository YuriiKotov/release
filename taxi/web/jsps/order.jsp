<%@ page import="java.util.Date" %>
<%@ page import="java.util.Calendar" %>
<%@ page import="java.text.DateFormat" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.util.Random" %>
<%@ page import="project.domain.Client" %>
<%@ page import="org.hibernate.Session" %>
<%@ page import="java.util.List" %>
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
        <form action="/newOrder" method="get">
            <table>
                <tr>
                    <%
                        List<String> list = (List<String>) request.getAttribute("list");
                        Client client = (Client) session.getAttribute("client");
                        SimpleDateFormat dateFormat = new SimpleDateFormat();
                        Date currentDate = new Date();
                        String date = dateFormat.format(currentDate);
                    %>
                    <td><label>Date</label></td>
                    <td><input type="text" name="first_name" value="<%out.println(date);%>" readonly></td>
                </tr>
                <tr>
                    <td><label>Client</label></td>
                    <td><input type="text" name="last_name" value="<%out.println(client.getFirst_name());%>" readonly></td>
                </tr>
                <tr>
                    <td><label>Departure</label></td>
                    <%
                        request.getParameter("start");
                    %>
                    <%--<td><textarea placeholder="Enter address" name="start"></textarea></td>--%>
                    <td><input list="addresses" name="start">
                        <datalist id="addresses" >
                            <%
                                for (int i = 0; i < list.size(); i++) {
                                    out.println("<option>" + list.get(i) + "</option>");
                                }
                            %>
                            <%--<option>Аперитивы</option>--%>
                            <%--<option>Горячие</option>--%>
                            <%--<option>Десертные</option>--%>
                            <%--<option>Диджестивы</option>--%>
                            <%--<option>Молочные</option>--%>
                            <%--<option>Слоистые</option>--%>
                        </datalist> </td>

                </tr>
                <tr>
                    <td><label>Destination</label></td>
                    <%--<td><textarea placeholder="Enter address" name="finish" ></textarea>--%>
                    <%--<td><input id="activitykeyworddisplay" name="activitykeyworddisplay" class="textfield" type="text" placeholder="Anything" value="" autocomplete="off"></td></td>--%>
                    <td><input list="addresses" name="finish">
                        <datalist id="addresses">
                            <%
                                for (int i = 0; i < list.size(); i++) {
                                    out.println("<option>" + list.get(i) + "</option>");
                                }
                            %>
                        </datalist> </td>
                    <div></div>
                </tr>
                <tr>
                    <td><label>How it cost</label></td>
                    <td><input type="text" name="finish" value="" readonly>
                </tr>
                <tr>
                    <td colspan="2"><input type="submit" value="Enter"></td>
                </tr>
            </table>
        </form>
    </div>
</body>
</html>
