/**
 * Created by yurii on 8/15/14.
 */
public class Test {

    static String str = "Value 1";

    public static String changeIt(String s) {
        s = "Value 2";
        return s;
    }

    public static void main(String[] args) {
        changeIt(str);
        System.out.println(str);
    }
}