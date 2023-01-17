package spil_Version_2.cards;

import gui_fields.GUI_Field;
import gui_fields.GUI_Player;
import gui_main.GUI;
import org.junit.jupiter.api.Test;
import spil_Version_2.Game_Controller;
import spil_Version_2.Game_Features;
import spil_Version_2.Player;
import spil_Version_2.UserIO;

import java.awt.*;

import static org.junit.Assert.assertEquals;

public class GiftCardTest {
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
    };
        @Test
        void testHit() {

/*

        GiftCard card = new GiftCard(50, "You have received a gift of 50");
        Player player1 = new Player("player1", 100,io, 0);
        player1.tilføjspillerGui( Color.blue);
        Player player2 = new Player("player2", 100,io, 0);
        player2.tilføjspillerGui( Color.green);
        Player player3 = new Player("player3", 100, io,0);
        player3.tilføjspillerGui( Color.red);


        card.hit(player1,io);

        assertEquals(150, player1.getKonto().getBalance());
        assertEquals(50, player2.getKonto().getBalance());
        assertEquals(50, player3.getKonto().getBalance());*/

    }

}
