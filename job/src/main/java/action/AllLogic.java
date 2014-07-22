package action;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by yurii on 7/16/14.
 */
@WebServlet("/logic")
public class AllLogic extends HttpServlet {

    File file = new File("/home/yurii/text.txt");
    BufferedReader br;
    String strFromText;
    List<String> mapValuesFromFile = new ArrayList<String>();
    List<String> mapKeys = new ArrayList<String>();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        readFromFile();
        request.setAttribute("val", mapValuesFromFile);
        request.getRequestDispatcher("index.jsp").forward(request, response);
    }

    public void readFromFile() throws IOException {
        mapKeys.clear();
        mapValuesFromFile.clear();
        br = new BufferedReader(new FileReader(file));
        while ((strFromText = br.readLine()) != null) {
            String[] mas = strFromText.split("=");
            mapKeys.add(mas[0]);
            mapValuesFromFile.add(mas[1]);
        }
        br.close();
    }
}
