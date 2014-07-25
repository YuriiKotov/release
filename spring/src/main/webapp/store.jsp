<%@ page import="springProject.controller.DBUsers" %>
<%@ page import="springProject.controller.Goods" %>
<%@ page import="java.util.Objects" %>
<%@ page import="java.util.Map" %>
<%--
  Created by IntelliJ IDEA.
  User: Юрий
  Date: 10.07.2014
  Time: 13:08
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Store</title>
    <link rel="stylesheet"  href="styleS.css" type="text/css">
</head>
<body class="storeBody">
    <div class="head">
        <div id="info">
            <ul>
                <li>
                    <img src="http://lopata.in.ua/files/dollar4.jpg" id="imgMoney"/>
                </li>
                <li id="showMeMoney">
                    <%
                        String sum = (String) request.getAttribute("addToBasket");
                        if (sum != null) {
                            out.println(sum);
                        } else {
                            out.println(0);
                        }
                    %>
                </li>
                <li>
                    <img src="http://t1.ftcdn.net/jpg/00/63/14/86/240_F_63148688_ZazzDHID1Djt8jG8BQwbQFww6nMb4KwT.jpg" id="imgBasket"/>
                </li>
                <li>
                    <a href="buy.jsp" id="aa">Оплатить</a>
                </li>
            </ul>
        </div>
    </div>



    <div id="storeBody">
        <table>
            <tr>
                <td><img src="http://t1.gstatic.com/images?q=tbn:ANd9GcRn-aKWxv7t_qJqKcZsd520ziVVjWStGHWz8prBG092cltdQkZMZQ"></td>
                <td><img src="http://северхлеб.рф/upload/catalog/%D0%94%D0%B0%D1%80%D0%BD%D0%B8%D1%86%D0%BA%D0%B8%D0%B9-5.jpg"></td>
                <td><img src="http://nov-bazar.od.ua/image/data/foto/34579108_1.jpg"></td>
                <td><img src="http://i-fakt.ru/wp-content/uploads/2014/03/makaroni.jpg"></td>
                <td><img src="http://sroki.net/wp-content/uploads/2013/11/239_e33af6e087cccd473c401323fb04c6e0_200.jpg"></td>
                <td><img src="http://t0.gstatic.com/images?q=tbn:ANd9GcSRY6-jcwobEa4JNpmbOg6aq430ts5Q0hXiWd6JqB3hZLSbiFvseA"></td>
                <td><img src="http://supermarket-semena.com.ua/images/Morkov.jpg"></td>
                <td><img src="http://myaso.od.ua/images/stories/1201.jpg"></td>
            </tr>
            <tr>
                <td> <form class="f1" action="/goods" method="GET">
                        <label>Цена: 20,-</label><br>
                        <label>Введите количество</label><br>
                        <input type="number" name="eggs"><br>
                        <input type="submit" value="Enter">
                        </form></td>
                <td>
                    <form class="f1" action="/goods" method="GET">
                        <label>Цена: 5,-</label><br>
                        <label>Введите количество</label><br>
                        <input type="number" name="bread"><br>
                        <input type="submit" value="Enter">
                    </form></td>
                <td><form class="f1" action="/goods" method="GET">
                    <label>Цена: 9,-</label><br>
                    <label>Введите количество</label><br>
                    <input type="number" name="potato"><br>
                    <input type="submit" value="Enter">
                </form></td>
                <td><form class="f1" action="/goods" method="GET">
                    <label>Цена: 15,-</label><br>
                    <label>Введите количество</label><br>
                    <input type="number" name="pasta"><br>
                    <input type="submit" value="Enter">
                </form></td>
                <td><form class="f1" action="/goods" method="GET">
                    <label>Цена: 25,-</label><br>
                    <label>Введите количество</label><br>
                    <input type="number" name="iceCream"><br>
                    <input type="submit" value="Enter">
                </form></td>
                <td><form class="f1" action="/goods" method="GET">
                    <label>Цена: 110,-</label><br>
                    <label>Введите количество</label><br>
                    <input type="number" name="cake"><br>
                    <input type="submit" value="Enter">
                </form></td>
                <td><form class="f1" action="/goods" method="GET">
                    <label>Цена: 5,-</label><br>
                    <label>Введите количество</label><br>
                    <input type="number" name="carrots"><br>
                    <input type="submit" value="Enter">
                </form></td>
                <td><form class="f1" action="/goods" method="GET">
                    <label>Цена: 65,-</label><br>
                    <label>Введите количество</label><br>
                    <input type="number" name="meat"><br>
                    <input type="submit" value="Enter">
                </form></td>
            </tr>
            <tr>
                <td colspan="8">Товара у вас в наличии</td>
            </tr>
            <tr>
                <td colspan="8">
                <%
                    Map<String, Integer> mapGoods = (Map<String, Integer>) request.getAttribute("listGoods");
                    if (mapGoods != null) {
                        for (Map.Entry<String, Integer> map : mapGoods.entrySet()) {
                            out.println(map.getKey() + ": " + map.getValue() + " шт" + "<br/>");
                        }
                    }
                %>
                </td>
            </tr>
        </table>
    </div>
</body>
</html>
