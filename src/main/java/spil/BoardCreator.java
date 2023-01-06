
package spil;
import java.awt.Color;
import gui_GameFields.*;

import javax.xml.crypto.Data;
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
                    for (int i =0;st.hasMoreTokens();i++)
                    {
                        currToken[i]= st.nextToken().trim();
                    }
                    fieldlist =addfield(currToken, fieldlist);

                }

            }

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
                list.add(new GUI_Street(fieldColor(data[11], data[0]),Color.black,data[0],"Kr. "+ data[3],
                        "Denne grund koster " + data[3] + " Kr",Integer.parseInt(data[3]),
                        Integer.parseInt(data[4]),rentArr(data),data[11]));
            }
            break;
            case "start": {
                list.add(new GUI_Start());
            }
            break;
            case "chance":
            {
                list.add(new GUI_Chance());
            }
            break;
            case "jail":
            {
                list.add(new GUI_Jail());
            }
            break;
            case "tax":
            {
                list.add(new GUI_Tax(data[0],"Kr "+ data[3],"Du skal betale " + data[3] + " Kr i skat"));
            }
            break;
            case "ferry":
            {
                int[] rent ={Integer.parseInt(data[4]),Integer.parseInt(data[5]),Integer.parseInt(data[6]),Integer.parseInt(data[7])};
                list.add(new GUI_Brewery(data[0],"Kr. " + data[3],
                        "Dette bryggerig koster " + data[3],Integer.parseInt(data[3]),rent));

            }
            break;
            case "brewery":
            {   int[] rent ={Integer.parseInt(data[4]),Integer.parseInt(data[5])};
               list.add(new GUI_Brewery(data[0],"Kr. " + data[3],
                       "Dette bryggerig koster " + data[3],Integer.parseInt(data[3]),rent));
            }
            break;
            case "refugee":
            {
                list.add(new GUI_Parkering());
            }
            break;
            default:
            {

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

    static ArrayList<String> blue= new ArrayList<String>();
    static ArrayList<String> red = new ArrayList<String>();
    static ArrayList<String> green = new ArrayList<String>();
    static ArrayList<String> orange = new ArrayList<String>();
    static ArrayList<String> grey = new ArrayList<String>();
    static ArrayList<String> white = new ArrayList<String>();
    ArrayList<String> yellow = new ArrayList<String>();
    static ArrayList<String> purple = new ArrayList<String>();


private Color fieldColor(String color,String title)
{
    Color c = Color.black;
    switch (color)
    {
        case "blue":
        {
          c= Color.blue;
            blue.add(title);

        }
        break;
        case "orange":
        {
         c= Color.orange;
            orange.add(title);

        }
        break;
        case "green":
        {
        c= Color.green;
            green.add(title);
        }
        break;
        case "grey":
        {
         c= Color.gray;
            grey.add(title);
        }
        break;
        case "red":
        {
           c= Color.red;
            red.add(title);
        }
        break;
        case "white":
        {
          c= Color.white;
            white.add(title);
        }
        break;
        case "yellow":
        {
          c= Color.yellow;
            yellow.add(title);
        }
        break;
        case "purple":
        {
           c= new Color(102,0,100);
            purple.add(title);

        }
        break;
        default:
        {

        }
        break;
    }
    return c;
}

public static ArrayList<String> getColorArray(String color) {


    ArrayList<String> colorarray = null;
    switch (color) {
        case "blue": {
            colorarray = blue;
        }
        break;

        case "red": {
            colorarray = red;
        }
        break;
        case "orange": {
            colorarray = orange;
        }
        break;
        case "grey": {
            colorarray = grey;
        }
        break;
        case "white": {
            colorarray = white;
        }
        break;
        case "green": {
            colorarray = green;
        }
        break;
        case "purple": {
            colorarray = purple;
        }


        break;

    }

    return colorarray;
}




    private int[] rentArr(String[] data)
{
    int[] rent = new int[6];
    for (int i = 5;i<11;i++)
    {
        rent[i-5]= Integer.parseInt(data[i]);
    }
    return rent;
}
}
