package spil_Version_2;

import gui_fields.GUI_Field;
import gui_main.GUI;
import java.util.ArrayList;


import java.awt.*;
import java.io.FileNotFoundException;

public class Game_Controller {



    public static boolean devMode = false;
    public static Color[] carColors = {Color.blue,Color.green,Color.red,Color.yellow,Color.MAGENTA,Color.BLACK};
    public static int max=-100;
    public static Player vinder;

    public static void setGui(GUI gui) {
        Game_Controller.gui = gui;
    }

    private static GUI gui;
    private static Player[] players;

    public static ArrayList<Player> getPlayerList() {
        return playerList;
    }

    private static ArrayList<Player> playerList = new ArrayList<>();
    public static void setFields(GUI_Field[] fields) {
        Game_Controller.fields = fields;
    }

    private static GUI_Field[] fields;
    public static void main(String[] args) throws FileNotFoundException {
        Board_Creator b = new Board_Creator();
        fields = b.istantiererFelter();
        GUIUserIOAdapter adapter = new GUIUserIOAdapter(new GUI(fields, Color.cyan));

        players = Game_Features.playerstoadd(adapter);


            for (int i = 0; i < players.length; i++) {

                players[i].tilføjspillerGui(getCarColors()[i]);
            }
        if (devMode == true)
        {
            Devmode devmode = new Devmode();
            devmode.playDevmode(players, fields,adapter);
        }
        else {
            playGame(players, fields);
        }

    }

    public static void playGame (Player[] players,GUI_Field[] fields)
    {

        for(int i = 0; i< players.length;i++){
            playerList.add(players[i]);
        }

        for (int i = 0; i < playerList.size(); i=(i+1)%playerList.size()) {

            if(playerList.get(i).spil(fields))
            {
                gui.getUserButtonPressed(playerList.get(i).getName()+" har mistet sine penge eller er givet op og er derfor ude af spillet", "fair nok");
                playerList.remove(i);
                i=0;

            }
            if(playerList.size() == 1){
                gui.getUserButtonPressed(playerList.get(i).getName()+"har vundet som den sidste spiller stående", "Tak for spillet");

                System.exit(1);
            }


        }



    }

    public static GUI_Field[] getFields() {
        return fields;
    }
    public static Player getPlayer(String playerName,UserIO userIO)
    {
        Player thePlayer = new Player("GetPlayerDosentWork",0,userIO, 0);
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
    public static void setDevMode(boolean devMode) {
        Game_Controller.devMode = devMode;
    }
    public static boolean isDevMode() {
        return devMode;
    }

    public static Player[] getPlayers() {
        return players;
    }
}

