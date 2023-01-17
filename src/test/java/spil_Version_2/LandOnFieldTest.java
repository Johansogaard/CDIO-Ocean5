package spil_Version_2;

import gui_fields.GUI_Car;
import gui_fields.GUI_Field;
import gui_fields.GUI_Ownable;
import gui_fields.GUI_Player;
import org.junit.jupiter.api.Test;

import java.awt.*;
import java.io.FileNotFoundException;
import java.lang.reflect.Method;

import static org.junit.jupiter.api.Assertions.*;

class LandOnFieldTest {

    @Test
    void landOnGåIFængsel() throws Exception {
        int[] choice = {1};
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
    @Test
    void Getoutofjailbythrowing() throws FileNotFoundException {
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
        player.inJail();

        assertTrue((player.jail && player.t1!=player.t2) || (!player.jail && player.t1==player.t2));

    }
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

    @Test
    void testHitOwnable() throws FileNotFoundException {
        // Set up the test environment
        int[] choice = {1};
        boolean[] bool = {true};
        GUIUserIOAdapterTest testUserIO = new GUIUserIOAdapterTest(choice,bool);
        Board_Creator b = new Board_Creator();
        GUI_Field[] fields = b.istantiererFelter();

        Game_Controller.setFields(fields);
        GUI_Ownable field = (GUI_Ownable) fields[6];
        Player player1 = new Player("player1", 2000, 0, testUserIO);
        Player player2 = new Player("player2", 2000, 0, testUserIO);
        GUI_Car car = new GUI_Car();
        player1.setCar(car);
        car.setPrimaryColor(Color.red);
        GUI_Player pl = new GUI_Player(player1.getName(), player1.getKonto().getBalance(), car);
        player1.pl  = pl;
        player1.setPos(6);
        LandOnField landOnField = new LandOnField();


        // simulate player1 clicking "Yes" to buy the field
        landOnField.setUserIO(testUserIO);
        landOnField.testHitOwnable(player1, fields);
        assertEquals(player1.getName(), field.getOwnerName());
        assertEquals(player1.getKonto().update(2000-Integer.parseInt(Board_Creator.getFieldData().get(6)[3])), 0);


    }

    }

