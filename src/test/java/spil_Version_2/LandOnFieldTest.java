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

    @Test
    void landOnGåIFængsel() throws Exception {

        GUIUserIOAdapterTest testUserIO = new GUIUserIOAdapterTest(0,true);
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


        GUIUserIOAdapterTest testUserIO = new GUIUserIOAdapterTest(1,true);
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


        GUIUserIOAdapterTest testUserIO = new GUIUserIOAdapterTest(1,false);
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


        GUIUserIOAdapterTest testUserIO = new GUIUserIOAdapterTest(1,true);
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

    }

