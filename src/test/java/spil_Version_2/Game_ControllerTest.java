package spil_Version_2;

import gui_fields.GUI_Car;
import gui_fields.GUI_Field;
import gui_fields.GUI_Ownable;
import gui_fields.GUI_Player;
import org.junit.jupiter.api.Test;

import java.awt.*;
import java.io.FileNotFoundException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class Game_ControllerTest {

//b3
    //tester at spilleren dør når han har minus penge
        @Test
        void testSpillerDør() throws FileNotFoundException {

            int[] choice = {1,1,3};
            boolean[] bool = {true,true,true};
            GUIUserIOAdapterTest testUserIO = new GUIUserIOAdapterTest(choice,bool);
            Board_Creator b = new Board_Creator();
            GUI_Field[] fields = b.istantiererFelter();
            Game_Controller.setFields(fields);

            Player player = new Player("test",30000,0,testUserIO);
            Player player2 = new Player("test2",49,0,testUserIO);
            Player[] players = {player,player2};
            Game_Controller.setPlayers(players);

            ArrayList<Player> playerList = new ArrayList<>();
            playerList.add(player);
            playerList.add(player2);
            Game_Controller.setPlayerList(playerList);

            GUI_Car car = new GUI_Car();
            player.setCar(car);

            car.setPrimaryColor(Color.red);
            GUI_Player pl = new GUI_Player(player.getName(), player.getKonto().getBalance(), car);
            player.pl = pl;
            player2.pl= pl;

            player.setPos(1);
            player.landOnField.hitField(player,fields,testUserIO);


            player2.setPos(1);
            player2.landOnField.hitField(player2,fields,testUserIO);
            player2.spil(fields);


            assertEquals(true,  player2.hasLost);

    }
}