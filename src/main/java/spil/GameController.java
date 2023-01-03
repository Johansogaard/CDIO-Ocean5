package spil;

import game_Txt.FieldText;
import gui_GameFields.*;
import gui_main.GUI;

import java.awt.*;
import java.io.FileNotFoundException;

public class GameController {
    final static String file = "src/main/resources/GameText.txt";
    public static FieldText mt = FieldText.getInstance();
    public static int max=-100;
    public static Player vinder;
    public static Boolean hasWon = false;
    public static void main(String[] args) throws FileNotFoundException {
        Player[] players = GameFeatures.playerstoadd();
        BoardCreator b = new BoardCreator();
           // System.out.println(readTextFromFile(file,"jailMessage"));
            GUI_Parentfield[] fields = b.istantiererFelter();
           GUI gui = new GUI(fields, Color.cyan);
           for (int i = 0; i < players.length; i++) {
               players[i].tilføjspillerGui(gui);
           }
           playGame(players,fields,gui);
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



}
