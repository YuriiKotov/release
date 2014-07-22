package action;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by yurii on 7/16/14.
 */
@WebServlet("/save")
public class Saver extends HttpServlet {

    Map<String, String[]> map = new HashMap<>();
    List<String> values = new ArrayList<String>();
    List<String> keys = new ArrayList<String>();
    AllLogic allLogic = new AllLogic();
    File file = new File("/home/yurii/text.txt");
    BufferedWriter bw;
    String newLines = "";


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        values.clear();
        keys.clear();
        allLogic.readFromFile();
        values = allLogic.mapValuesFromFile;
        keys = allLogic.mapKeys;

        map = request.getParameterMap();

        for (int i = 2; i < keys.size(); i++) {
            if (!map.get(keys.get(i))[0].equals(values.get(i))) {
                values.set(i, map.get(keys.get(i))[0]);
            }
        }

        for (int i = 0; i < keys.size(); i++) {
            newLines = newLines + keys.get(i) + "=" + values.get(i) + "\n";
        }
        write();
        request.getRequestDispatcher("accepter.jsp").forward(request, response);
    }

    public void write() throws IOException{
        bw = new BufferedWriter(new FileWriter(file));
        bw.write(newLines);
        bw.close();
        newLines = "";
    }
}
