package game_Txt;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.TreeMap;

public class FieldText {

    private static FieldText fieldText_instance = null;

    public Map<String, String> mp;
    private FieldText()
    {
     mp= inputTextToMap("src/main/resources/GameText.txt");
    }
    private Map inputTextToMap(String file)
    {
        String input;
        Map<String,String> map= new TreeMap<>();
        try {
            BufferedReader reader = new BufferedReader(new FileReader(file));
            while(true) {
                input = reader.readLine();
                if (input == null || input == "") {
                    reader.close();
                    return map;

                } else {
                    StringTokenizer st = new StringTokenizer(input, ";");
                    map.put(st.nextToken(), st.nextToken());
                }

            }

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }
    public static FieldText getInstance()
    {
        if(fieldText_instance == null)
        {
            fieldText_instance = new FieldText();
        }
        return  fieldText_instance;
    }

}
