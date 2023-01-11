package spil_Version_2;


import gui_GameFields.GUI_Ferry;
import gui_fields.*;

import java.awt.*;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Board_Creator {


    static ArrayList<String[]> fieldData = new ArrayList<>();
        public GUI_Field[] istantiererFelter() throws FileNotFoundException {
            ArrayList<GUI_Field> fieldlist = new ArrayList<>();

            int f = 0;
            String input;
            String file = "src/main/resources/fieldsdata.csv";
            try {
                BufferedReader reader = new BufferedReader(new FileReader(file));

                while(true) {
                    input = reader.readLine();
                    if (input == null || input == "") {
                        reader.close();

                        fieldData.remove(0);
                        return listToArray(fieldlist);

                    } else {
                        String[] dataArray =  input.split(",",-1);
                                //   StringTokenizer st = new StringTokenizer(input, ",");

                        /*String[] currToken = new String[st.countTokens()];
                        for (int i =0;st.hasMoreTokens();i++)
                        {

                            currToken[i]= st.nextToken().trim();
                        }*/

                        dataArray[2] = dataArray[2].trim();


                        fieldData.add(f,dataArray);
                        fieldlist =addfield(dataArray, fieldlist);
                        f++;

                    }

                }

            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

    static ArrayList<String> ferry= new ArrayList<String>();
    static ArrayList<String> brewery= new ArrayList<String>();

        private  ArrayList<GUI_Field> addfield(String[] data,ArrayList<GUI_Field> list)
        {


            switch (data[2])
            {

                case "street":
                {
                    gui_fields.GUI_Street newStreet = new gui_fields.GUI_Street();

                    newStreet.setTitle(data[0]);
                    newStreet.setSubText("Kr. "+data[3]);
                    newStreet.setDescription("Denne grund koster "+data[3]);
                    newStreet.setRent(data[5]);
                    newStreet.setBackGroundColor(fieldColor(data[11],data[0]));
                    list.add(newStreet);


                }
                break;
                case "start": {
                    list.add(new gui_fields.GUI_Start());
                }
                break;
                case "chance":
                {
                    list.add(new gui_fields.GUI_Chance());
                }
                break;
                case "jail":
                {
                    GUI_Jail jail = new GUI_Jail();
                    jail.setSubText(data[0]);
                    list.add(jail);
                }
                break;
                case "tax":
                {
                    list.add(new GUI_Tax());
                    //list.add(new GUI_Tax(data[0],"Kr "+ data[3],"Du skal betale " + data[3] + " Kr i skat"));
                }
                break;
                case "ferry":
                {
                    GUI_Shipping s = new GUI_Shipping();
                    s.setTitle(data[0]);
                    s.setSubText("Kr. "+data[3]);
                    s.setDescription("Denne grund koster "+data[3]);
                    s.setRent(data[5]);
                    list.add(s);
                    ferry.add(data[0]);

                }
                break;
                case "brewery":
                {
                    GUI_Brewery b = new GUI_Brewery();
                    b.setTitle(data[0]);
                    b.setSubText("Kr. "+data[3]);
                    b.setDescription("Denne grund koster "+data[3]);
                    b.setRent(data[5]);
                    list.add(b);
                    brewery.add(data[0]);
                }
                break;
                case "refugee":
                {
                  GUI_Refuge refuge =  new GUI_Refuge();
                  refuge.setSubText("Parkering");
                    list.add(refuge);
                }
                break;
                default:
                {

                }
                break;

            }

            return list;
        }
    public static ArrayList<String> getTypeArray(String type) {
        ArrayList<String> TypeArray = null;
        switch (type) {
            case "ferry": {
                TypeArray = ferry;
            }
            break;
            case "brewery": {
                TypeArray = brewery;
            }
            break;

        }
        return TypeArray;
    }
        private GUI_Field[] listToArray(ArrayList<GUI_Field> list)
        {

            GUI_Field[] fields = new GUI_Field[list.size()];
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
    static ArrayList<String> yellow = new ArrayList<String>();
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
    public static ArrayList<String> getGroupArray(String color) {


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
            case "yellow": {
                colorarray = yellow;
            }
            break;
            case "ferry": {
                colorarray = ferry;
            }
            break;
            case "brewery": {
                colorarray = brewery;
            }
            break;
            default:
            {

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
    public static ArrayList<String[]> getFieldData() {
       return fieldData;
    }
    }


