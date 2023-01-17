package spil_Version_2;

import gui_fields.GUI_Car;
import gui_fields.GUI_Field;
import gui_fields.GUI_Player;
import org.junit.jupiter.api.Test;

import java.awt.*;
import java.io.FileNotFoundException;
import java.lang.reflect.Method;

import static org.junit.jupiter.api.Assertions.*;

class LandOnFieldTest {
//f3
    @Test
    void landOnGåIFængsel() throws Exception {
        int[] choice = {0};
        boolean[] bool = {true};
        GUIUserIOAdapterTest testUserIO = new GUIUserIOAdapterTest(choice,bool);
        Board_Creator b = new Board_Creator();
        GUI_Field[] fields = b.istantiererFelter();
        Game_Controller.setFields(fields);
        Player player = new Player("test",30000,0,testUserIO);


        player.setPos(30);
        player.landOnField.hitField(player,fields,testUserIO);

        assertTrue(player.isJail());


    }
    //g3
    @Test
    void Getoutofjailbypaying() throws FileNotFoundException {

        int[] choice = {1};
        boolean[] bool = {true};
        GUIUserIOAdapterTest testUserIO = new GUIUserIOAdapterTest(choice,bool);
        Board_Creator b = new Board_Creator();
        GUI_Field[] fields = b.istantiererFelter();
        Game_Controller.setFields(fields);

        Player player = new Player("test",30000,0,testUserIO);
        GUI_Car car = new GUI_Car();
        player.setCar(car);

        car.setPrimaryColor(Color.red);
        GUI_Player pl = new GUI_Player(player.getName(), player.getKonto().getBalance(), car);
        player.pl = pl;
        player.goToJail();
        player.spil(fields);

        assertFalse(player.jail && player.getKonto().getBalance()==29000);



    }
    //g2
    @Test
    void Getoutofjailbythrowing() throws FileNotFoundException {

        int[] choice = {1};
        boolean[] bool = {false};
        GUIUserIOAdapterTest testUserIO = new GUIUserIOAdapterTest(choice,bool);
        Board_Creator b = new Board_Creator();
        GUI_Field[] fields = b.istantiererFelter();
        Game_Controller.setFields(fields);

        Player player = new Player("test",30000,0,testUserIO);
        GUI_Car car = new GUI_Car();
        player.setCar(car);

        car.setPrimaryColor(Color.red);
        GUI_Player pl = new GUI_Player(player.getName(), player.getKonto().getBalance(), car);
        player.pl = pl;
        player.goToJail();
        player.inJail();

        assertTrue((player.jail && player.t1!=player.t2) || (!player.jail && player.t1==player.t2));

    }
    //g5
    @Test
    void Getoutofjailbycard() throws FileNotFoundException {

        int[] choice = {1};
        boolean[] bool = {true};
        GUIUserIOAdapterTest testUserIO = new GUIUserIOAdapterTest(choice,bool);
        Board_Creator b = new Board_Creator();
        GUI_Field[] fields = b.istantiererFelter();
        Game_Controller.setFields(fields);

        Player player = new Player("test",30000,0,testUserIO);
        GUI_Car car = new GUI_Car();
        player.setCar(car);

        car.setPrimaryColor(Color.red);
        GUI_Player pl = new GUI_Player(player.getName(), player.getKonto().getBalance(), car);
        player.pl = pl;
        player.prisoncard=true;
        player.setPos(30);
        player.landOnField.hitField(player,fields,testUserIO);

        assertFalse(player.jail);

    }
//f2
    @Test
    void landontax1() throws FileNotFoundException {

        int[] choice = {1};
        boolean[] bool = {true};
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

        player.setPos(4);
        player.landOnField.hitField(player,fields,testUserIO);

        assertEquals( 26000, player.getKonto().getBalance());



    }
    //f2
    @Test
    void landontax2() throws FileNotFoundException {

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

        player.setPos(4);
        player.landOnField.hitField(player,fields,testUserIO);

        assertEquals( 27000, player.getKonto().getBalance());



    }
    //f2
    @Test
    void landontax3() throws FileNotFoundException {

        int[] choice = {1};
        boolean[] bool = {true, false};
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

        player.setPos(1);
        player.landOnField.hitField(player,fields,testUserIO);

        player.setPos(4);
        player.landOnField.hitField(player,fields,testUserIO);

        assertEquals( 25800, player.getKonto().getBalance());



    }
    //f2
    @Test
    void landontax4() throws FileNotFoundException {

        int[] choice = {1};
        boolean[] bool = {true, true};
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

        player.setPos(1);
        player.landOnField.hitField(player,fields,testUserIO);

        player.setPos(4);
        player.landOnField.hitField(player,fields,testUserIO);

        assertEquals( 24800, player.getKonto().getBalance());



    }
    @Test
    void landonandettax() throws FileNotFoundException {

        int[] choice = {1};
        boolean[] bool = {true, true};
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

        player.setPos(38);
        player.landOnField.hitField(player,fields,testUserIO);

        assertEquals( 28000, player.getKonto().getBalance());

    }}

