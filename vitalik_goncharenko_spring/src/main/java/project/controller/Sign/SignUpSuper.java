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
@WebServlet("/logicSignUpSuper")
public class SignUpSuper extends HttpServlet {
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
                    request.getRequestDispatcher("/signUpSuper").forward(request, response);
                    return;
                }
            }
        }

        String idNumber = request.getParameterMap().get("id_number")[0];
        boolean resId = false;
        if (!idNumber.contains("2") && !idNumber.contains("4") &&
                !idNumber.contains("6") && !idNumber.contains("8") && idNumber.length() == 10) {
            resId = true;
        }

        char[] login = request.getParameterMap().get("login")[0].toCharArray();

        if (login.length > 3 ) {
            for (int z = 0; z < login.length; z++) {
                if (login[z] == ' ') {
                    request.setAttribute("message", "Login can't contains empty symbol");
                    request.getRequestDispatcher("/signUpSuper").forward(request, response);
                    return;
                }
            }
            if (request.getParameterMap().get("password")[0].equals(request.getParameterMap().get("submit_password")[0]) && resId) {
                char[] checkPassword = request.getParameterMap().get("password")[0].toCharArray();
                if (checkPassword.length > 7) {
                    for (int i = 0; i < checkPassword.length; i++) {
                        boolean res = Character.isDigit(checkPassword[i]);
                        if (res) {
                            for (int h = 0; h < checkPassword.length; h++) {
                                boolean resFirst = Character.isLowerCase(checkPassword[h]);
                                if (resFirst) {
                                    for (int j = 0; j < checkPassword.length; j++) {
                                        boolean resSecond = Character.isUpperCase(checkPassword[j]);
                                        if (resSecond) {
                                            for (int k = 0; k < checkPassword.length; k++) {
                                                if (checkPassword[k] != ' ' && k == checkPassword.length - 1) {
                                                    map = request.getParameterMap();
                                                    clientService.create(map.get("login")[0], map.get("password")[0], null, null, null, null, 0, null, true);
                                                    request.getRequestDispatcher("/signInSuper").forward(request, response);
                                                    return;
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }else {
            request.setAttribute("message", "Убедитесь в следующем: 1.Длинна пароля не менее 8 символов. " +
                    "2. В пароле должны быть хотябы по одной: прописной букве, заглавной и цифре." +
                    "3. Логин не менее 4 символов" + "4. В правильности идентификационныого номера");
            request.getRequestDispatcher("/signUpSuper").forward(request, response);
            return;
        }

    }
}
