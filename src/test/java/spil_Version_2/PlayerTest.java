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

        assertEquals(player2.getKonto().getBalance(), 29900);


        }
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


    /*
    private final UserIO io = new UserIO() {
        @Override
        public int getUserButtonPressed(String s, String... keys) {
            return 0;
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
        public GUI_Field setCar(int tsum, GUI_Player pl, GUI_Field fpos) {
            return null;
        }

        @Override
        public boolean getUserLeftButtonPressed(String msg, String bt1, String bt2) {
            return false;
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
    };*/
    @Test
    @DisplayName("Testing spil method")
    void spil() throws FileNotFoundException {

    }

    @Test
    void runATurn()  throws Exception {
        int i = 0;
        while (i<100) {
            int[] choice = {1};
            boolean[] bool = {true};
            GUIUserIOAdapterTest testUserIO = new GUIUserIOAdapterTest(choice,bool);
            Board_Creator b = new Board_Creator();
            GUI_Field[] fields = b.istantiererFelter();
            Game_Controller.setFields(fields);
            Player pl = new Player("test", 30000, 0, testUserIO);

            pl.spil(fields);

            assertTrue(pl.getTerningeSum() == pl.getPos());
            System.out.println(pl.getPos());
            assertTrue(pl.getPos() >= 2 && pl.getPos() <= 12);
            assertFalse(pl.getPos() > 12 || pl.getPos() < 2);
        }

    }

    @Test
    void simpleTurn()
    {




    }

    @Test
    void checkIfPassedStart() throws FileNotFoundException {
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
    }

    @Test
    void updatePlayerBalance() {
    }
}