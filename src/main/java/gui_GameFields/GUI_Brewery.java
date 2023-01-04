package gui_GameFields;

import spil.Player;

import java.awt.*;

public class GUI_Brewery extends GUI_Parentfield{
    public GUI_Brewery(String title, String subText, String description,int price,int[] rent) {
        super(Color.red, Color.black, title, subText, description);

    }

    @Override
    public void hit(Player player)
    {

    }
}
