package action;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by yurii on 7/16/14.
 */
@WebServlet("/auto")
public class Authorization extends HttpServlet {
    String login;
    String password;
    AllLogic allLogic;
    List<String> map;
    List<String> keys;

    @Override
    public void init() {
        allLogic = new AllLogic();
        map = new ArrayList<String>();
        keys = new ArrayList<String>();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        login = request.getParameterMap().get("login")[0];
        password = request.getParameterMap().get("password")[0];

        map.clear();
        keys.clear();
        allLogic.readFromFile();
        map = allLogic.mapValuesFromFile;
        keys = allLogic.mapKeys;

        if (map.get(0).equals(login) && map.get(1).equals(password)) {
            request.setAttribute("accept", "zer good!");
            request.setAttribute("map", map);
            request.setAttribute("keys", keys);
            request.getRequestDispatcher("admin.jsp").forward(request, response);
        } else {
            request.setAttribute("error", "error");
            request.getRequestDispatcher("authorization.jsp").forward(request, response);

        }
    }
}
