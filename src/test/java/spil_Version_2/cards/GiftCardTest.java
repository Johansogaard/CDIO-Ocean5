package spil_Version_2.cards;

import gui_main.GUI;
import org.junit.jupiter.api.Test;
import spil_Version_2.Game_Controller;
import spil_Version_2.Game_Features;
import spil_Version_2.Player;

import java.awt.*;

import static org.junit.Assert.assertEquals;

public class GiftCardTest {
    @Test
    public void testHit() {
        GUI gui = new GUI();

        GiftCard card = new GiftCard(50, "You have received a gift of 50");
        Player player1 = new Player("player1", 100, 0);
        player1.tilføjspillerGui(gui, Color.blue);
        Player player2 = new Player("player2", 100, 0);
        player2.tilføjspillerGui(gui, Color.green);
        Player player3 = new Player("player3", 100, 0);
        player3.tilføjspillerGui(gui, Color.red);


        card.hit(player1);

        assertEquals(150, player1.getKonto().getBalance());
        assertEquals(50, player2.getKonto().getBalance());
        assertEquals(50, player3.getKonto().getBalance());

    }
}
