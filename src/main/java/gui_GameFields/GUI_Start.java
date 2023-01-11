package gui_GameFields;

import spil_Version_2.Player;

import java.awt.*;

public class GUI_Start extends GUI_Parentfield {
    public GUI_Start() {
        super(Color.GRAY, Color.black,mt.mp.get("start"), mt.mp.get("startS"), mt.mp.get("startD"));

    }



    @Override
    public void hit(Player player)
    {

    }
}
