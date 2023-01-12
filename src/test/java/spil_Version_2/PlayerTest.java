package spil_Version_2;

import gui_fields.GUI_Field;
import gui_fields.GUI_Ownable;
import gui_main.GUI;
import org.junit.jupiter.api.Test;

import java.awt.*;
import java.io.FileNotFoundException;

import static org.junit.jupiter.api.Assertions.*;

import gui_fields.GUI_Field;
import gui_main.GUI;
import java.util.ArrayList;



import java.awt.*;
import java.io.FileNotFoundException;

class PlayerTest {


    @Test
    void checkOwnerOwnALL() throws FileNotFoundException {
        GUI_Field[] fields;
    // laver bræt
        Board_Creator b = new Board_Creator();
        fields = b.istantiererFelter();

        //laver spillere
        Player[] plA2 = new Player[2];
        plA2[0] = new Player("Spiller 1", 30000, 0);
        plA2[1] = new Player("Spiller 2", 30000, 0);
        Game_Controller.setPlayers(plA2);

    //adder grunde til spiller 1's array
        Game_Controller.getPlayer("Spiller 1").addGrunde("Roskildevej");
        Game_Controller.getPlayer("Spiller 1").addGrunde("Valby Langgade");
        Game_Controller.getPlayer("Spiller 1").addGrunde("Allégade");

       // sætter spiller 1 som owneren af en grund
        GUI_Field field = Game_Controller.getFields()[1];
        GUI_Ownable ownable = (GUI_Ownable) field;

        ownable.setOwnerName(String.valueOf(Game_Controller.getPlayer("Spiller 1")));

        System.out.println(ownable.getOwnerName());

        System.out.println(Game_Controller.getPlayer("Spiller 1").getGrunde());

        //spiller 2 lander på spiller 1 grund




    }
}