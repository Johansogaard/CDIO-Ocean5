
package spil;
import java.awt.Color;
import gui_GameFields.*;

import java.awt.*;
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


                   return listToArray(fieldlist);

                } else {
                    StringTokenizer st = new StringTokenizer(input, ",");
                    String[] currToken = new String[st.countTokens()];
                    for (int i =0;i<st.countTokens();i++)
                    {
                        currToken[i]= st.nextToken();
                    }
                    fieldlist =addfield(currToken, fieldlist);

                }

            }
        return
    } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    private  ArrayList<GUI_Parentfield> addfield(String[] data,ArrayList<GUI_Parentfield> list)
    {


        switch (data[2])
        {

            case "street":
            {


                list.add(new GUI_Street(fieldColor(data[10]),Color.black,);
            }
            break;
        }

return list;
    }
 private GUI_Parentfield[] listToArray(ArrayList<GUI_Parentfield> list)
 {

     GUI_Parentfield[] fields = new GUI_Parentfield[list.size()];
     for (int i = 0;i<list.size();i++)
     {
         fields[i] = list.get(i);
     }
     return fields;
 }
private Color fieldColor(String color)
{
    Color c = Color.black;
    switch (color)
    {
        case "blue":
        {
          c= Color.blue;
        }
        break;
        case "orange":
        {
         c= Color.orange;
        }
        break;
        case "green":
        {
        c= Color.green;
        }
        break;
        case "grey":
        {
         c= Color.gray;
        }
        break;
        case "red":
        {
           c= Color.red;
        }
        break;
        case "white":
        {
          c= Color.white;
        }
        break;
        case "yellow":
        {
          c= Color.yellow;
        }
        break;
        case "purple":
        {
           c= Color.getHSBColor(300,100,50);
        }
        break;
    }
    return c;
}}
