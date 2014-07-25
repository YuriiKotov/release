package springProject.controller;


import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;
import springProject.domain.User;
import springProject.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;

/**
 * Created by Юрий on 09.07.2014.
 */
@WebServlet("/dbUsers")
public class DBUsers extends HttpServlet {

    private WebApplicationContext context;
    UserService userService;

    Map<String, String[]> pair;
    List<String[]> allUsers;
    String[] newPerson;
    String[] newLogin;
    String[] newPassword;
    String[] newConPas;

    String[] login;
    String[] password;


    List<User> users;

    @Override
    public void init() {
        Locale.setDefault(Locale.ENGLISH);
        context = WebApplicationContextUtils.getWebApplicationContext(getServletContext());
        userService = (UserService) context.getBean("UserServiceImpl");
        pair = new HashMap<String, String[]>();
        allUsers = new ArrayList<String[]>();
        newPerson = new String[2];
        users = new ArrayList<User>();

    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {



//List users from DB
        allUsers.clear();
        users = userService.userList();
        for (User user : users) {
            newPerson[0] = user.getLogin();
            newPerson[1] = user.getPassword();
            allUsers.add(newPerson);
        }

///////////////////При заходе с register.jsp/////////////////////
        if (request.getParameterMap().get("password2") != null) {
            pair = request.getParameterMap();
            newLogin = pair.get("login");
            newPassword = pair.get("password1");
            newConPas = pair.get("password2");

//Search same login
            if (allUsers.size() > 0) {
                for (String[] list : allUsers) {
                    for (int i = 0; i < list.length; i++) {
                        if (list[0].equals(newLogin[0])) {
                            request.setAttribute("error", "This login is already in use! Please enter another login");
                            request.getRequestDispatcher("register.jsp").forward(request, response);
                        }
                    }
                }
            }

//Check password
            char[] cMas = newPassword[0].toCharArray();

            if (cMas.length > 7 && !newLogin[0].equals(newPassword[0]) && newPassword[0].equals(newConPas[0])) {
                for (int i = 0; i < cMas.length; i++) {
                    boolean res = Character.isDigit(cMas[i]);
                    if (res) {
                        for (int h = 0; h < cMas.length; h++) {
                            boolean resFirst = Character.isLowerCase(cMas[h]);
                            if (resFirst) {
                                for (int j = 0; j < cMas.length; j++) {
                                    boolean resSecond = Character.isUpperCase(cMas[j]);
                                    if (resSecond) {
                                        newPerson[0] = newLogin[0];
                                        newPerson[1] = newPassword[0];
                                        allUsers.add(newPerson);
                                        userService.create(newLogin[0], newPassword[0]);
                                        request.setAttribute("accept", "You are registered. Now you can enter!");
                                        request.getRequestDispatcher("index.jsp").forward(request, response);
                                    }
                                }
                            }
                        }
                    }
                }
            } else {
                request.setAttribute("pasError", "Убедитесь в следующем: 1.Длинна 8 символов. " +
                        "2. В пароле должны быть буквы, цифры, большие буквы. " +
                        "3. Пароль не должен совпадать с логином");
                request.getRequestDispatcher("register.jsp").forward(request, response);
            }
        }

        //При заходе с index.jps
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

