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

        GUIUserIOAdapterTest testuserIO = new GUIUserIOAdapterTest(0);


        // Create a player object with an initial balance of 1000
        Player player1 = new Player("Player 1", 1000,0, testuserIO);
        Player player2 = new Player("Player 2", 1000,0, testuserIO);
        Player player3 = new Player("Player 3", 1000,0, testuserIO);

        Board_Creator b = new Board_Creator();
        GUI_Field[] fields = b.istantiererFelter();
        Game_Controller.setFields(fields);
        GUI_Car car = new GUI_Car();
        car.setPrimaryColor(Color.red);
        GUI_Player pl1 = new GUI_Player(player1.getName(), player1.getKonto().getBalance(), car);
        player1.pl = pl1;
        GUI_Player pl2 = new GUI_Player(player2.getName(), player2.getKonto().getBalance(), car);
        player2.pl = pl2;
        GUI_Player pl3 = new GUI_Player(player2.getName(), player2.getKonto().getBalance(), car);
        player3.pl = pl3;



        // Create an instance of the GiftCard class with an amount of 500
        GiftCard card = new GiftCard(500, "You won 500");

        // Create a mock UserIO object
        testuserIO = new GUIUserIOAdapterTest(0);


        // Call the hit method on the card object, passing in the player and userIO objects as arguments
        card.hit(player1, testuserIO);

        // Assert that the player's balance has been updated correctly
        assertEquals(1500, player1.getKonto().getBalance());
        assertEquals(500, player2.getKonto().getBalance());
        assertEquals(500, player3.getKonto().getBalance());
    }
}
