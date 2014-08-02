package project.controller.Order;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;
import project.domain.Client;
import project.service.ClientService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 * Created by yurii on 7/25/14.
 */
@WebServlet("/newOrder")
public class NewOrder extends HttpServlet {

    private WebApplicationContext context;
    ClientService clientService;
    Random random;
    Map<String, String[]> map;
    Client client;

    @Override
    public void init() {
        context = WebApplicationContextUtils.getWebApplicationContext(getServletContext());
        clientService = (ClientService) context.getBean("clientServiceImpl");
        random = new Random();
        map = new HashMap<String, String[]>();
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        System.out.println(request.getParameterMap().get("id")[0]);
        client = clientService.read(Long.valueOf(request.getParameterMap().get("id")[0]));
        request.setAttribute("client", client);

        if (request.getParameterMap().get("start")[0].toCharArray().length > 5 && request.getParameterMap().get("finish")[0].toCharArray().length > 5) {
            request.setAttribute("cost", String.valueOf(random.nextInt(50) + 35));
            request.getRequestDispatcher("/order").forward(request, response);
        } else {
            request.getRequestDispatcher("/order").forward(request, response);
        }
    }
}
