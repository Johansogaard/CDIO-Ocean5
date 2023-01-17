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
    //tester om man kan komme ud af fængslet ved at slå 2 ens
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
    //tester om man kommer ud af fængslet hvis man har kortet
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
//tester om man betaler den rigtige skat 4000
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
    //tester om man betaler den rigtige skat 10%
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
    //tester om man betaler den rigtige skat 10% hvis man har grunde
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
    //ser om den anden tax på 2000 virker
    //f6
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

    }
    //f5
    //ser om det virker med færger
    @Test
    void checkferrytriple() throws FileNotFoundException {

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

        player.setPos(15);
        player.landOnField.hitField(player,fields,testUserIO);

        player.setPos(25);
        player.landOnField.hitField(player,fields,testUserIO);


        player2.setPos(5);
        player2.landOnField.hitField(player2,fields,testUserIO);

        assertEquals(30000-2000, player2.getKonto().getBalance());

}

    //f5
    //ser om det virker med færger når man har 3
    @Test
    void checkferry() throws FileNotFoundException {

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
    //f4
    //ser om det virker med bryggeri
    @Test
    void checkbrewery() throws FileNotFoundException {

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

        player.setPos(12);
        player.landOnField.hitField(player,fields,testUserIO);


        player2.setPos(12);
        player2.t1=1;
        player2.t2=1;
        player2.landOnField.hitField(player2,fields,testUserIO);


        assertEquals(30000-200, player2.getKonto().getBalance());


}
    //f4
    //ser om det virker med bryggeri når man har 2
    @Test
    void checkbrewerydouble() throws FileNotFoundException {

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

        player.setPos(12);
        player.landOnField.hitField(player,fields,testUserIO);

        player.setPos(28);
        player.landOnField.hitField(player,fields,testUserIO);

        player2.setPos(12);
        player2.t1=1;
        player2.t2=1;
        player2.landOnField.hitField(player2,fields,testUserIO);


        assertEquals(30000-400, player2.getKonto().getBalance());


    }

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

