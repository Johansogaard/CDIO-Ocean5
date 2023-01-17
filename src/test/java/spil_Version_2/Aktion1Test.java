package spil_Version_2;

import gui_fields.GUI_Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import gui_fields.GUI_Ownable;
import gui_fields.GUI_Field;

import java.io.FileNotFoundException;

import static org.junit.jupiter.api.Assertions.*;

class Aktion1Test {

    private Aktion1 aktion1;
    private Player player1;
    private Player player2;
    private Player player3;
    private GUI_Ownable field;

    private boolean choice;

    private GUIUserIOAdapterTest testUserIO0;

    private GUIUserIOAdapterTest testUserIO = new GUIUserIOAdapterTest(1);


    @BeforeEach
    public void setUp() throws FileNotFoundException {

        Board_Creator b = new Board_Creator();
        GUI_Field[] fields = b.istantiererFelter();
        Game_Controller.setFields(fields);

        aktion1 = new Aktion1();
        player1 = new Player("player1", 1000, 0, testUserIO);
        player2 = new Player("player1", 2000, 0, testUserIO);
        player3 = new Player("player1", 30000, 0, testUserIO);
        testUserIO.addPlayer(player1.pl);
        testUserIO.addPlayer(player2.pl);
        testUserIO.addPlayer(player3.pl);
    }

    @Test
    public void testKorAktion() throws FileNotFoundException {
        // Test case 1: player 2 wins the auction
        Board_Creator b = new Board_Creator();
        GUI_Field[] fields = b.istantiererFelter();
        field = (GUI_Ownable) fields[6];
        Game_Controller.setFields(fields);
        this.choice = true;


        //Tester at spiller 1 vinder auktion

        testUserIO.getUserLeftButtonPressed("", "yes", "no");
        assertTrue(aktion1.korAktion(player1, field, 100, testUserIO0));
        assertEquals(field.getOwnableLabel(), "Ejet af player2");
        assertEquals(player2.getKonto().getBalance(), 500);


    }
}