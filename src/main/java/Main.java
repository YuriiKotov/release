import java.io.IOException;

/**
 * Created by yurii on 8/6/14.
 */
public class Main {
    public static void main(String[] args){
        CSVSearch csvSearch = new CSV("ip,name,desc\n" + "10.49.1.4,server1,Main Server\n"+
                "10.52.5.1,server2,Backup Server\n", "name");

//        try {
            System.out.println(csvSearch.find("server9").getField("ip"));
//        } catch (NullPointerException e) {}
    }
}
