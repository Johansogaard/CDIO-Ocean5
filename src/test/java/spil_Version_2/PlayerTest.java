package spil_Version_2;

import gui_fields.GUI_Car;
import gui_fields.GUI_Field;
import gui_fields.GUI_Player;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.awt.*;
import java.io.FileNotFoundException;
import java.lang.reflect.Method;

import static org.junit.jupiter.api.Assertions.*;

class PlayerTest {
//h6
    //ser om man betaler dobbelt leje på ubebyggede grunde når man har et sæt
    @Test
    void checkcheckdoublecost() throws FileNotFoundException {

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

        player.setPos(1);
        player.landOnField.hitField(player,fields,testUserIO);
        player.setPos(3);
        player.landOnField.hitField(player,fields,testUserIO);

        player2.setPos(3);
        player2.landOnField.hitField(player2,fields,testUserIO);

        assertEquals(player2.getKonto().getBalance(), 30000-2*50);


        }
        //b2
    //cheker at man betaler leje
    @Test
    void checkpayrent() throws FileNotFoundException {

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

        player.setPos(1);
        player.landOnField.hitField(player,fields,testUserIO);


        player2.setPos(1);
        player2.landOnField.hitField(player2,fields,testUserIO);

        assertEquals(player2.getKonto().getBalance(), 30000-50);
        assertEquals(player.getKonto().getBalance(), 30000-1200+50);


    }
    //b2
    //chekker at man ikke betaler leje til sig selv
    @Test
    void checkpayrentegetfelt() throws FileNotFoundException {

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

        player.setPos(1);
        player.landOnField.hitField(player,fields,testUserIO);


        player.setPos(1);
        player.landOnField.hitField(player2,fields,testUserIO);


        assertEquals(player.getKonto().getBalance(), 30000-1200);


    }
    //s2
    //tjekker om man for penge over start
    @Test
    void moneypassstart() throws FileNotFoundException {

        int[] choice = {1};
        boolean[] bool = {false};
        GUIUserIOAdapterTest testUserIO = new GUIUserIOAdapterTest(choice,bool);
        Board_Creator b = new Board_Creator();
        GUI_Field[] fields = b.istantiererFelter();
        Game_Controller.setFields(fields);

        Player player = new Player("test",30000,0,testUserIO);
        Player[] players = {player};
        Game_Controller.setPlayers(players);
        GUI_Car car = new GUI_Car();
        player.setCar(car);

        car.setPrimaryColor(Color.red);
        GUI_Player pl = new GUI_Player(player.getName(), player.getKonto().getBalance(), car);
        player.pl = pl;

        player.setPos(39);
        player.spil(fields);
        //Giver fejl
        assertEquals( 34000, player.getKonto().getBalance());



    }


    @Test
    @DisplayName("Testing spil method")
    void spil()  throws Exception {
        /*
        int i = 0;
        while (i<100) {
            int[] choice = {1};
            boolean[] bool = {true};
            GUIUserIOAdapterTest testUserIO = new GUIUserIOAdapterTest(choice,bool);
            Player player = testUserIO.istancierTest(testUserIO);
            player.spil(Game_Controller.getFields());
            System.out.println(player.getPos());
            assertTrue(player.getPos() >= 2 && player.getPos() <= 36);
            assertFalse(player.getPos() < 2 || player.getPos() > 36);

*/



        }




    @Test
    void updatePlayerBalance() {
    }
}