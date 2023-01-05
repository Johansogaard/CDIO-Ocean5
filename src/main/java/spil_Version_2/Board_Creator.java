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
        public GUI_Field[] istantiererFelter() throws FileNotFoundException {
            ArrayList<GUI_Field> fieldlist = new ArrayList<>();

            String input;
            String file = "src/main/resources/fieldsdata.csv";
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
                    newStreet.setBackGroundColor(fieldColor(data[11]));
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
                    s.setRent(data[4]);
                    list.add(s);

                }
                break;
                case "brewery":
                {
                    GUI_Brewery b = new GUI_Brewery();
                    b.setTitle(data[0]);
                    b.setSubText("Kr. "+data[3]);
                    b.setDescription("Denne grund koster "+data[3]);
                    b.setRent(data[4]);
                    list.add(b);
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
        private GUI_Field[] listToArray(ArrayList<GUI_Field> list)
        {

            GUI_Field[] fields = new GUI_Field[list.size()];
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
                    c= new Color(102,0,100);
                }
                break;
                default:
                {

                }
                break;
            }
            return c;
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


