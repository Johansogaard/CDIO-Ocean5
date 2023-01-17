package spil_Version_2;

import gui_fields.GUI_Car;
import gui_fields.GUI_Field;
import gui_fields.GUI_Player;
import org.junit.jupiter.api.Test;

import java.awt.*;
import java.io.FileNotFoundException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class FerryTest2 {
    //f5
    //ser om det virker med færger når man har 3
    @Test
    void checkFerry() throws FileNotFoundException {

        int[] choice = {1};
        boolean[] bool = {true,true,true};
        GUIUserIOAdapterTest testUserIO = new GUIUserIOAdapterTest(choice,bool);
        Board_Creator b = new Board_Creator();
        GUI_Field[] fields = b.istantiererFelter();
        Game_Controller.setFields(fields);

        Player player = new Player("test",30000,0,testUserIO);
        Player player2 = new Player("test2",30000,0,testUserIO);
        Player[] players = {player,player2};
        Game_Controller.setPlayers(players);
        GUI_Car car = new GUI_Car();
        player.setCar(car);

        car.setPrimaryColor(Color.red);
        GUI_Player pl = new GUI_Player(player.getName(), player.getKonto().getBalance(), car);

        player.pl = pl;
        player2.pl= pl;

        player.setPos(5);
        player.landOnField.hitField(player,fields,testUserIO);




        player2.setPos(5);
        player2.landOnField.hitField(player2,fields,testUserIO);

        assertEquals(30000-500, player2.getKonto().getBalance());}
}


