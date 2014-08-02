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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by yurii on 7/24/14.
 */
@WebServlet("/logicSignUp")
public class SignUp extends HttpServlet {
    Map<String, String[]> map;

    private WebApplicationContext context;
    ClientService clientService;

    @Override
    public void init() {
        map = new HashMap<String, String[]>();
        context = WebApplicationContextUtils.getWebApplicationContext(getServletContext());
        clientService = (ClientService) context.getBean("clientServiceImpl");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        List<Client> clientList = clientService.clientList();

        if (clientList != null) {
            for (Client client : clientList) {
                if (client.getLogin().equals(request.getParameterMap().get("login")[0])) {
                    request.setAttribute("message", "User with this Name is already registered");
                    request.getRequestDispatcher("/signUp").forward(request, response);
                    return;
                }
            }
        }

        char[] checkPassword = request.getParameterMap().get("password")[0].toCharArray();

        boolean spaceInPas = true;
        for (int i = 0; i < checkPassword.length; i++) {
            if (checkPassword[i] == ' ') {
                spaceInPas = false;
            }
        }

        boolean enableName = true;
        if (request.getParameterMap().get("first_name") == null) {
            enableName = false;
        }

        if (request.getParameterMap().get("password")[0].equals(request.getParameterMap().get("submit_password")[0]) &&
                request.getParameterMap().get("mobile")[0] != null) {
            if (checkPassword.length > 7) {
                for (int i = 0; i < checkPassword.length; i++) {
                    boolean res = Character.isDigit(checkPassword[i]);
                    if (res) {
                        for (int h = 0; h < checkPassword.length; h++) {
                            boolean resFirst = Character.isLowerCase(checkPassword[h]);
                            if (resFirst) {
                                for (int j = 0; j < checkPassword.length; j++) {
                                    boolean resSecond = Character.isUpperCase(checkPassword[j]);
                                    if (resSecond && spaceInPas && enableName) {
                                        map = request.getParameterMap();
                                        clientService.create(map.get("login")[0], map.get("password")[0], map.get("first_name")[0], map.get("last_name")[0], Integer.valueOf(map.get("mobile")[0]), map.get("address")[0], 0, null, false);
                                        request.getRequestDispatcher("/signIn").forward(request, response);
                                        return;
                                    }
                                }
                            }
                        }
                    }
                }
            }
        } else {
            request.setAttribute("message", "Убедитесь в следующем: 1.Длинна пароля не менее 8 символов. " +
                    "2. В пароле должны быть хотябы по одной: прописной букве, заглавной и цифре");
            request.getRequestDispatcher("/signUp").forward(request, response);
            return;
        }
    }

}
