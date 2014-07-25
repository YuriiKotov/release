package springProject.controller;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;

/**
 * Created by Юрий on 10.07.2014.
 */
@WebServlet("/goods")
public class Goods extends HttpServlet{

    int sum = 0;
    Map<String, String[]> pair;
    String[] quantity;
    Map<String, Integer> priceForGoods;
    Map<String, Integer> goodsQuantity;

    @Override
    public void init() {
        pair = new HashMap<>();
        goodsQuantity = new HashMap<>();
        priceForGoods = new HashMap<>();
        priceForGoods.put("eggs", 20);
        priceForGoods.put("bread", 5);
        priceForGoods.put("potato", 9);
        priceForGoods.put("pasta", 15);
        priceForGoods.put("iceCream", 25);
        priceForGoods.put("cake", 110);
        priceForGoods.put("carrots", 5);
        priceForGoods.put("meat", 65);
    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        pair = request.getParameterMap();
        checkParameter(pair);
        request.setAttribute("addToBasket", String.valueOf(sum));
        request.setAttribute("listGoods", goodsQuantity);
        request.getRequestDispatcher("store.jsp").forward(request, response);
    }

    public void checkParameter(Map<String, String[]> pair) {
        for (Map.Entry<String, Integer> map : priceForGoods.entrySet()) {
            if (pair.get(map.getKey()) != null) {
                quantity = pair.get(map.getKey());
                if (Integer.parseInt(quantity[0]) > 0) {
                    sum += Integer.parseInt(quantity[0]) * map.getValue();

                    if (goodsQuantity.get(map.getKey()) != null) {
                        int oldValue = goodsQuantity.get(map.getKey());
                        int newValue = oldValue + Integer.parseInt(quantity[0]);
                        goodsQuantity.put(map.getKey(), newValue);
                    } else {
                        goodsQuantity.put(map.getKey(), Integer.parseInt(quantity[0]));
                    }
                }
            }
        }
    }
}
