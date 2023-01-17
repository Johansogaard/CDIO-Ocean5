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

        GUIUserIOAdapterTest testUserIO = new GUIUserIOAdapterTest(0);
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


        GUIUserIOAdapterTest testUserIO = new GUIUserIOAdapterTest(1);
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


        GUIUserIOAdapterTest testUserIO = new GUIUserIOAdapterTest(1);
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


        GUIUserIOAdapterTest testUserIO = new GUIUserIOAdapterTest(1);
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
        GUIUserIOAdapterTest testUserIO = new GUIUserIOAdapterTest(1);
        Board_Creator b = new Board_Creator();
        GUI_Field[] fields = b.istantiererFelter();

        Game_Controller.setFields(fields);
        GUI_Ownable field = (GUI_Ownable) fields[6];
        Player player1 = new Player("player1", 1000, 0, testUserIO);
        Player player2 = new Player("player2", 2000, 0, testUserIO);
        testUserIO.addPlayer(player1.pl);
        testUserIO.addPlayer(player2.pl);
        player1.setPos(6);
        boolean choice;
        choice = true;
        LandOnField landOnField = new LandOnField();

        // Test case 1: Player 1 buys the ownable field

        // simulate user clicking "Yes" to buy the field
        landOnField.testHitOwnable(player1, fields);
        assertEquals(player1, field.getOwnableLabel());
        assertEquals(1000 - Integer.parseInt(Board_Creator.getFieldData().get(0)[3]), player1.getKonto().getBalance());

        // Test case 2: Player 1 doesn't have enough money to buy the field
        player1.getKonto().update(100);
        landOnField.testHitOwnable(player1, fields);
        assertEquals("player2", field.getOwnerName());
        assertEquals(100, player1.getKonto().getBalance());

        // Test case 3: Player 1 lands on an owned field and has to pay rent
        field.setOwnerName("player2");
        field.setRent("100");
        landOnField.testHitOwnable(player1, fields);
        assertEquals(0, player1.getKonto().getBalance());
        assertEquals(2100, player2.getKonto().getBalance());
    }

    }

