package spil_Version_2;

import gui_fields.GUI_Brewery;
import gui_fields.GUI_Field;
import gui_fields.GUI_Street;

public class LandOnField {
    public void hitField(Player player, GUI_Field[] fields)
    {
        player.displayCard();
        if (GUI_Street.class.equals(fields[player.getPos()].getClass())) {
            hitStreet();
        }
        else if (GUI_Brewery.class.equals(fields[player.getPos()].getClass()))
        {

        }
    }
    private void hitStreet(){

    }
    private void hitFerry()
}

