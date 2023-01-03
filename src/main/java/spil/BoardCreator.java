
package spil;

import gui_GameFields.*;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.TreeMap;

public class BoardCreator {

    public GUI_Parentfield[] istantiererFelter() throws FileNotFoundException {
        ArrayList<GUI_Parentfield> fieldlist = new ArrayList<>();

        String input;
        String file = "src/main/resources/fieldsdata.csv";
        Map<String,String> map= new TreeMap<>();
        try {
            BufferedReader reader = new BufferedReader(new FileReader(file));
            while(true) {
                input = reader.readLine();
                if (input == null || input == "") {
                    reader.close();


                   return fields;

                } else {
                    StringTokenizer st = new StringTokenizer(input, ";");
                    map.put(st.nextToken(), st.nextToken());
                }

            }
        return
    } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
 private GUI_Parentfield[] listToArray(ArrayList<GUI_Parentfield> list)
 {

     GUI_Parentfield[] fields = new GUI_Parentfield[list.size()];
     for (int i = 0;i<)
 }

}
