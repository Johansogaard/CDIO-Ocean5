package spil_Version_2;

import gui_GameFields.GUI_Parentfield;
import gui_fields.GUI_Field;
import gui_main.GUI;



import java.awt.*;
import java.io.FileNotFoundException;
import java.util.ArrayList;

public class Game_Controller {


    public static Color[] carColors = {Color.blue,Color.green,Color.red,Color.yellow,Color.MAGENTA,Color.BLACK};
    public static int max=-100;
    public static Player vinder;

    public static Player loser;

    private static GUI gui;
    private static Player[] players;

    private static GUI_Field[] fields;
    public static void main(String[] args) throws FileNotFoundException {
        players = Game_Features.playerstoadd();

        Board_Creator b = new Board_Creator();
        // System.out.println(readTextFromFile(file,"jailMessage"));
        fields = b.istantiererFelter();
        gui = new GUI(fields, Color.cyan);

       for (int i = 0; i < players.length; i++) {

            players[i].tilføjspillerGui(gui,getCarColors()[i]);
        }
        playGame(players,fields,gui);

    }
    public static void playGame (Player[] players,GUI_Field[] fields,GUI gui)
    {
        ArrayList<Player> playerList = new ArrayList<>();
        for(int i = 0;i<players.length;i++)
        {
            playerList.add(players[i]);
        }
        for (int i = 0; i < players.length; i=(i+1)%players.length) {
            if(playerList.get(i).spil(gui,fields))
            {
                if(players[i].getKonto().getBalance()<0){
                    gui.getUserButtonPressed(playerList.get(i).getName()+"har mistet sine penge og taber derfor spillet","okay");
                    loser = playerList.get(i);
                    playerList.remove(i);
                }
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
    public static GUI_Field[] getFields() {
        return fields;
    }
    public static Player getPlayer(String playerName)
    {
        Player thePlayer = new Player("GetPlayerDosentWork",0,0);
        for(int i = 0;i<players.length;i++) {
            if (playerName == players[i].getName())
            {
                thePlayer = players[i];
            }
        }
        return thePlayer;
    }
    public static Color[] getCarColors() {
        return carColors;
    }
}
