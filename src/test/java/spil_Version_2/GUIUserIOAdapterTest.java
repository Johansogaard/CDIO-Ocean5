package spil_Version_2;

import gui_fields.GUI_Car;
import gui_fields.GUI_Field;
import gui_fields.GUI_Player;
import gui_main.GUI;

import java.awt.*;
import java.io.FileNotFoundException;
import java.util.ArrayList;

public class GUIUserIOAdapterTest extends UserIO{
    boolean b[];
    int bCounter =-1;
    int choice[];
    int choiceCounter = -1;
    public GUIUserIOAdapterTest(int choice[], boolean b[])
    {
        this.b = b;
        this.choice = choice;
    }
    @Override
    public int getUserButtonPressed(String s, String... keys) {

        choiceCounter++;
        if (choiceCounter == choice.length)
        {
            choiceCounter = 0;
        }
        return choice[choiceCounter];

    }

    @Override
    public void showMessage(String msg) {

    }

    @Override
    public String getUserSelection(String msg, String[] choice) {
        return null;
    }

    @Override
    public int getUserInteger(String msg) {
        return 0;
    }

    @Override
    public void setCar(int tsum, GUI_Player pl, GUI_Field fpos) {

    }

    @Override
    public boolean getUserLeftButtonPressed(String msg, String bt1, String bt2) {

        bCounter++;
        if(bCounter == b.length)
        {
            bCounter = 0;
        }
        return b[bCounter];
    }

    @Override
    public void setDice(int t1, int t2) {

    }

    @Override
    public void displayChanceCard(String title, String description) {

    }

    @Override
    public void addPlayer(GUI_Player player) {

    }
    public Player[] istancierTest(UserIO testUserIO) throws FileNotFoundException {
        Board_Creator b = new Board_Creator();
        GUI_Field[] fields = b.istantiererFelter();
        Game_Controller.setFields(fields);
        Player player1 = new Player("test1", 30000, 0, testUserIO);
        GUI_Car car1 = new GUI_Car();
        car1.setPrimaryColor(Color.red);
        player1.setCar(car1);
        Player player2 = new Player("test2", 30000, 0, testUserIO);
        GUI_Car car2 = new GUI_Car();
        car2.setPrimaryColor(Color.blue);
        player2.setCar(car2);
        Player player3 = new Player("test3", 30000, 0, testUserIO);
        GUI_Car car3 = new GUI_Car();
        car3.setPrimaryColor(Color.green);
        player3.setCar(car3);
        ArrayList<Player> plList = new ArrayList<>();





        GUI_Player pl1 = new GUI_Player(player1.getName(), player1.getKonto().getBalance(), car1);
        GUI_Player pl2 = new GUI_Player(player1.getName(), player1.getKonto().getBalance(), car2);
        GUI_Player pl3 = new GUI_Player(player1.getName(), player1.getKonto().getBalance(), car3);
        player1.pl = pl1;
        player2.pl = pl2;
        player3.pl = pl3;

        plList.add(player1); plList.add(player2);plList.add(player3);
        Game_Controller.setPlayerList(plList);
        Game_Controller.setPlayers(plList.toArray(new Player[plList.size()]));


        return (plList.toArray(new Player[plList.size()]));
    }
}
