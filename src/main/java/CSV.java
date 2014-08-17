import java.util.ArrayList;
import java.util.List;

/**
 * Created by yurii on 8/6/14.
 */
public class CSV implements CSVSearch {
    String[] headers;
    List<String[]> db = new ArrayList<>();
    int pos;

    public CSV(String text, String name) {
        toDb(text, name);
    }

    @Override
    public CSVRecord find(String key) {
        for (String[] str : db) {
            if (str[pos].equals(key)) {
                new CSVRecord() {
                    @Override
                    public String getField(String name) {
                        for (int i = 0; i < headers.length; i++) {
                            if (headers[i].equals(name)) {
                                return str[i];
                            }
                        }
                        return null;
                    }
                };
            }
        }
        return null;
    }

    private void toDb(String text, String name) {
        String[] lines = text.split("\n");
        headers = lines[0].split(",");
        for (int i = 0; i < headers.length; i++) {
            if (headers[i].equals(name)) {
                pos = i;
            }
        }
        for (int i = 1; i < lines.length; i++) {
            db.add(lines[i].split(","));
        }
    }
}