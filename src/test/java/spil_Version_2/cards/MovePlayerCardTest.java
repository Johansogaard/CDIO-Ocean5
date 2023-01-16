package spil_Version_2.cards;

import static org.junit.jupiter.api.Assertions.*;

import static org.junit.Assert.assertEquals;

import gui_fields.GUI_Field;
import gui_main.GUI;
import org.junit.Test;
import spil_Version_2.*;

import spil_Version_2.Game_Controller;
import spil_Version_2.Player;

import java.awt.*;
import java.io.FileNotFoundException;

public class MovePlayerCardTest {

    @Test
    public void testHit() throws FileNotFoundException {

        Player player = new Player("player1", 500, 0);
        Board_Creator b = new Board_Creator();
        //
        GUI_Field fields[] = b.istantiererFelter();
        Game_Controller.setFields(fields);

        MovePlayerCard card = new MovePlayerCard(5, "Ryk til Helsingør",true);
        GUI gui = new GUI(fields,Color.cyan);
        Game_Controller.setGui(gui);
        player.tilføjspillerGui(gui, Color.blue);

        card.hit(player);
        assertEquals(5, player.getPos());

    }
}
