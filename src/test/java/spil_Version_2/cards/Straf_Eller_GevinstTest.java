package spil_Version_2.cards;

import gui_fields.GUI_Car;
import gui_fields.GUI_Field;
import gui_fields.GUI_Player;
import org.junit.jupiter.api.Test;
import spil_Version_2.Board_Creator;
import spil_Version_2.GUIUserIOAdapterTest;
import spil_Version_2.Game_Controller;
import spil_Version_2.Player;

import java.awt.*;
import java.io.FileNotFoundException;

import static org.junit.Assert.assertEquals;

public class Straf_Eller_GevinstTest {
    @Test
    public void testHitMethod() throws FileNotFoundException {

        // Create a player object with an initial balance of 1000
        int[] choice = {0};
        boolean[] bool = {true};
        GUIUserIOAdapterTest testUserIO = new GUIUserIOAdapterTest(choice,bool);
        Player player = new Player("Player 1", 1000, 0, testUserIO);
        Board_Creator b = new Board_Creator();
        GUI_Field[] fields = b.istantiererFelter();
        Game_Controller.setFields(fields);
        GUI_Car car = new GUI_Car();
        car.setPrimaryColor(Color.red);
        GUI_Player pl = new GUI_Player(player.getName(), player.getKonto().getBalance(), car);
        player.pl = pl;

        // Create an instance of the Straf_Eller_Gevinst class with an amount of 500 and add set to true
        Straf_Eller_Gevinst card = new Straf_Eller_Gevinst(500, true, "You won 500");
        // Create an instance of the GUIUserIOAdapterTest class



        // Call the hit method on the card object, passing in the player and userIO objects as arguments
        card.hit(player, testUserIO);

        // Assert that the player's balance has been updated correctly
        assertEquals(1500, player.getKonto().getBalance());
    }
}
