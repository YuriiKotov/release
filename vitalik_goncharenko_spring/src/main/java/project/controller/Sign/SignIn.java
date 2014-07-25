package project.controller.Sign;

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
import java.util.List;

/**
 * Created by yurii on 7/24/14.
 */
@WebServlet("/logicSignIn")
public class SignIn extends HttpServlet {

    private WebApplicationContext context;
    ClientService clientService;

    @Override
    public void init() {
        context = WebApplicationContextUtils.getWebApplicationContext(getServletContext());
        clientService = (ClientService) context.getBean("clientServiceImpl");
    }
    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        List<Client> clientList = clientService.clientList();

        if (clientList != null) {
            for (Client client : clientList) {
                if (client.getLogin().equals(request.getParameterMap().get("login")[0]) &&
                        client.getPassword().equals(request.getParameterMap().get("password")[0])) {
                    request.setAttribute("client", client);
                    request.getRequestDispatcher("/order").forward(request, response);
                    return;
                }
                if (client.getLogin().equals(request.getParameterMap().get("login")[0])) {
                    request.setAttribute("message", " Password is incorrect, please try again");
                    request.getRequestDispatcher("/signIn").forward(request, response);
                    return;
                }
            }
        } else {
            request.setAttribute("message", "User with this LOGIN DON'T registered, please SIGN UP");
            request.getRequestDispatcher("/signIn").forward(request, response);
            return;
        }
    }
}
