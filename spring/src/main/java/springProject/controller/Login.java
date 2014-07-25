package springProject.controller;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;
import springProject.domain.User;
import springProject.service.UserServiceImpl;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: al1
 * Date: 09.06.13
 */
@WebServlet("/login")
public class Login extends HttpServlet {

    private WebApplicationContext context;
    UserServiceImpl userService;

    Map<String, String[]> pair;
    List<String[]> allUsers;
    List<User> users;
    String[] newPerson;

    String[] login;
    String[] password;

    @Override
    public void init() {
        pair = new HashMap<>();
        context = WebApplicationContextUtils.getWebApplicationContext(getServletContext());
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws javax.servlet.ServletException, IOException {

        userService = (UserServiceImpl) context.getBean("userServiceImpl");

        //List users from DB
        allUsers.clear();
        users = userService.userList();
        for (User user : users) {
            newPerson[0] = user.getLogin();
            newPerson[1] = user.getPassword();
            allUsers.add(newPerson);
        }

        pair = request.getParameterMap();
        login = pair.get("login");
        password = pair.get("password");

//Проверка на правильность логина и пароля
        if (allUsers != null) {
            for (String[] loginAndPasswordUsers : allUsers) {
                for (int i = 0; i < loginAndPasswordUsers.length; i++) {
                    if (login[0].equals(loginAndPasswordUsers[0]) && password[0].equals(loginAndPasswordUsers[1])) {
                        request.getRequestDispatcher("store.jsp").forward(request, response);
                    }
                }
            }
        }

//В случае false выводим ошибку
        request.setAttribute("errorLogin", "User with this login/password don't authorization. Please, try again!");
        request.getRequestDispatcher("index.jsp").forward(request, response);
    }
}
