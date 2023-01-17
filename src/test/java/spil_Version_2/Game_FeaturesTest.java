package spil_Version_2;

import gui_fields.GUI_Car;
import gui_fields.GUI_Field;
import gui_fields.GUI_Player;
import org.junit.jupiter.api.Test;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;

class Game_FeaturesTest {
    //c1
    @Test
    void Nextcardtest() throws Exception {
        int[] choice = {0};
        boolean[] bool = {true};
        GUIUserIOAdapterTest testUserIO = new GUIUserIOAdapterTest(choice,bool);
        Board_Creator b = new Board_Creator();
        GUI_Field[] fields = b.istantiererFelter();
        Game_Controller.setFields(fields);
        Player player = new Player("test",30000,0,testUserIO);

        GUI_Car car = new GUI_Car();
        player.setCar(car);

        car.setPrimaryColor(Color.red);
        GUI_Player pl = new GUI_Player(player.getName(), player.getKonto().getBalance(), car);
        player.pl = pl;

int cardbefore = LandOnField.card;


        player.setPos(2);
        player.landOnField.hitField(player,fields,testUserIO);

        assertEquals(cardbefore+1, LandOnField.card);
    }

}