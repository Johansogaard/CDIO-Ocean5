package spil_Version_2;

import gui_fields.GUI_Field;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.lang.reflect.Method;

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

    }

    @Test
    void runATurn()  throws Exception {
        int i = 0;
        while (i<100) {
            GUIUserIOAdapterTest testUserIO = new GUIUserIOAdapterTest(1);
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
    void getKonto() {
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