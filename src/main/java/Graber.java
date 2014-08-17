import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by yurii on 8/14/14.
 */
public class Graber {
    public static void main(String[] args) {
        File file = new File("src/main/resources/streets.txt");
        String str = "";

        try {
            BufferedReader br = new BufferedReader(new FileReader(file));
            String line;



            while ((line = br.readLine()) != null) {
                String l = "";
                String[] mas = line.split(" ");
                String[] n = new String[mas.length];
                for (int i = 0; i < mas.length; i++) {
                    if (i >= mas.length-1) {
                        n[i] = mas[0];
                        l+= n[i];
                    } else {
                        n[i] = mas[i + 1];
                        l += n[i] + " ";
                    }
                }
                str += l + "\n";
                }
//            System.out.println(str);
                BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file));
                bufferedWriter.write(str);
                bufferedWriter.flush();


        } catch (IOException e) {}


    }
}
