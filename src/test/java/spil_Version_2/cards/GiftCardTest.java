package spil_Version_2.cards;

import static org.junit.jupiter.api.Assertions.*;


import gui_fields.GUI_Car;
import gui_fields.GUI_Field;
import gui_fields.GUI_Player;
import org.junit.jupiter.api.Test;
import spil_Version_2.*;

import java.awt.*;
import java.io.FileNotFoundException;

import static org.junit.Assert.assertEquals;

public class GiftCardTest {
    @Test
    public void testHitMethod() throws FileNotFoundException {
        int[] choice = {0};
        boolean[] bool = {true};
       GUIUserIOAdapterTest userIOTest = new GUIUserIOAdapterTest(choice,bool);
       Player[] players = userIOTest.istancierTest(userIOTest);
        Player player1 = players[0];
        Player player2 = players[1];
        Player player3 = players[2];
        player1.getKonto().setB(1000);
        player2.getKonto().setB(1000);
        player3.getKonto().setB(1000);




        // Create an instance of the GiftCard class with an amount of 500
        GiftCard card = new GiftCard(500, "You won 500");

        // Create a mock UserIO object



        // Call the hit method on the card object, passing in the player and userIO objects as arguments
        card.hit(player1, userIOTest);

        // Assert that the player's balance has been updated correctly
        assertEquals(2000, player1.getKonto().getBalance());
        assertEquals(500, player2.getKonto().getBalance());
        assertEquals(500, player3.getKonto().getBalance());
    }
}
