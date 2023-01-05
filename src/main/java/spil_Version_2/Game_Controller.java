package spil_Version_2;

import gui_GameFields.GUI_Parentfield;
import gui_fields.GUI_Field;
import gui_main.GUI;
import spil.GameFeatures;
import spil.Player;

import java.awt.*;
import java.io.FileNotFoundException;

public class Game_Controller {
    public static int max=-100;
    public static Player vinder;
    private static GUI gui;
    public static void main(String[] args) throws FileNotFoundException {
        //Player[] players = GameFeatures.playerstoadd();
        Player[] players = new Player[1];
        Board_Creator b = new Board_Creator();
        // System.out.println(readTextFromFile(file,"jailMessage"));
        GUI_Field[] fields = b.istantiererFelter();
        gui = new GUI(fields, Color.cyan);

      /*  for (int i = 0; i < players.length; i++) {
            players[i].tilføjspillerGui(gui);
        }
        playGame(players,fields,gui);*/
    }
    public static void playGame (Player[] players,GUI_Parentfield[] fields,GUI gui)
    {

        for (int i = 0; i < players.length; i=(i+1)%players.length) {
            if(players[i].spil(gui,fields))
            {
                break;
            }


        }



        for (int i = 0; i < players.length; i++)
        {

            if (players[i].getKonto().getBalance() == max)
            {
                if(players[i].getKonto().getFieldvalue() > vinder.getKonto().getFieldvalue())
                {
                    vinder = players[i];
                }
            }
            else if (players[i].getKonto().getBalance()>max)
            {
                max = players[i].getKonto().getBalance();
                vinder = players[i];
            }

        }
        gui.getUserButtonPressed(vinder.getName() + " har vundet med flest penge", "Okay");
        System.exit(1);

    }
    public static GUI getGui() {
        return gui;
    }

}
