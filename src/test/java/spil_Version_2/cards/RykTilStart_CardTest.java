package spil_Version_2.cards;

import static org.junit.jupiter.api.Assertions.*;

import static org.junit.Assert.assertEquals;

import gui_main.GUI;
import org.junit.Test;
import spil_Version_2.Player;

import java.awt.*;

public class RykTilStart_CardTest {
    @Test
    public void testHit() {
        GUI gui = new GUI();

        Player player = new Player("player1", 500, 0);
        player.tilføjspillerGui(gui, Color.blue);

        RykTilStart_Card card = new RykTilStart_Card(0);
        card.hit(player);
        assertEquals(0, player.getPos());
        assertEquals(500, player.getKonto().getBalance());

        card = new RykTilStart_Card(200);
        card.hit(player);
        assertEquals(0, player.getPos());
        assertEquals(700, player.getKonto().getBalance());
    }
}
