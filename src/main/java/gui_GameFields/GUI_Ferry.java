package gui_GameFields;

import spil.Player;

import java.awt.*;

public class GUI_Ferry extends GUI_Parentfield{
    public GUI_Ferry(String title, String subText, String description,int price,int[] rent) {
        super(Color.gray, Color.black, title, subText, description);
    }
    @Override
    public void hit(Player player)
    {

    }
}
