package project.controller.Redirect;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

/**
 * Created by yurii on 7/24/14.
 */
@WebServlet("/signIn")
public class SignIn extends HttpServlet{

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        request.getRequestDispatcher("jsps/signIn.jsp").forward(request, response);
    }
    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        request.getRequestDispatcher("jsps/signIn.jsp").forward(request, response);
    }
}
