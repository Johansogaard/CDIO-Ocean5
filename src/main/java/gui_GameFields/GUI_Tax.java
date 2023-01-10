package gui_GameFields;

import spil.Player;

import java.awt.*;

public class GUI_Tax extends GUI_Parentfield{
    public GUI_Tax(String title, String price,String description)
    {
        super(Color.gray,Color.black,"Tax",price,"Betal skat");

    }

    @Override
    public void hit(Player player)
    {

    }
}
