package spil_Version_2.cards;


import static org.junit.Assert.assertEquals;

import gui_main.GUI;
import org.junit.Test;
import spil_Version_2.Player;

import java.awt.*;

public class Straf_Eller_GevinstTest {
    @Test
    public void testHit() {
        GUI gui = new GUI();

        Straf_Eller_Gevinst card = new Straf_Eller_Gevinst(50, true, "Du har vundet 50");
        Player player = new Player("testplayer",100,0);
        player.tilføjspillerGui(gui, Color.blue);


        card.hit(player);
        assertEquals( 150, player.getKonto().getBalance());


        card = new Straf_Eller_Gevinst(25, false, "Du har tabt 25");
        card.hit(player);
        assertEquals( 125, player.getKonto().getBalance());
    }
}
