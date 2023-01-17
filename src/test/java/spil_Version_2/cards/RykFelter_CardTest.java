package spil_Version_2.cards;

import static org.junit.jupiter.api.Assertions.*;


import gui_fields.GUI_Field;
import gui_main.GUI;
import org.junit.jupiter.api.Test;
import spil_Version_2.*;
import spil_Version_2.cards.RykFelter_Card;

import java.io.FileNotFoundException;

public class RykFelter_CardTest {
    @Test
    public void testHitMethod() throws FileNotFoundException {
        GUIUserIOAdapterTest testuserIO = new GUIUserIOAdapterTest(0);


        // Create a player object with an initial position of 0
        Player player = new Player("Player 1", 1000, 0, testuserIO);
        Board_Creator b = new Board_Creator();
        GUI_Field[] fields = b.istantiererFelter();
        Game_Controller.setFields(fields);

        // Create an instance of the RykFelter_Card class with 5 spaces to move
        RykFelter_Card card = new RykFelter_Card(5);

        // Call the hit method on the card object, passing in the player object as an argument
        card.hit(player, testuserIO);

        // Assert that the player's position has been updated correctly
        assertEquals(5, player.getPos());
    }
}
