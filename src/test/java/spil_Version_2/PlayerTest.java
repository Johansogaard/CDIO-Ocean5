package spil_Version_2;

import com.sun.jdi.Method;
import gui_fields.GUI_Field;
import gui_fields.GUI_Player;
import gui_main.GUI;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import spil_Version_2.Player;
import java.awt.*;
import java.io.FileNotFoundException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PlayerTest {
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
        Board_Creator b = new Board_Creator();
        GUI_Field[] fields = b.istantiererFelter();
        UserIO userIO = new UserIO(){
            Player player = new Player("TestSpiller", 30000, 0, new UserIO() {
                player.
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
            });


    @Test
    void runATurn() throws FileNotFoundException {


        Board_Creator b = new Board_Creator();
        GUI_Field[] fields = b.istantiererFelter();
        GUIUserIOAdapter adapter = new GUIUserIOAdapter(new GUI(fields, Color.cyan));

        player.runA
        assertTrue(player1.getTerningeSum() == player1.getPos());
        System.out.println(player1.getPos());
        assertTrue(player1.getPos()>=1 && player1.getPos()<=11);

    }

    @Test
    void simpleTurn()
    {




    }

    @Test
    void getKonto() {
     /*   List<Integer> choices = List.of(0, 2);

            @Override
            public int getUserButtonPressed(String s, String... keys) {
                return choices.remove(0);
            }
        }

      player.spil(new GUI_Field[]{});*/
    }

    @Test
    void getName() {
    }

    @Test
    void checkIfPassedStart() {
    }

    @Test
    void updatePlayerBalance() {
    }
}