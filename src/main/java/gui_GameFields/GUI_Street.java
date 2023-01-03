package gui_GameFields;

import java.awt.*;

public class GUI_Street extends GUI_Parentfield {


    public GUI_Street(Color bgColor, Color fgColor, String title, String subText, String description) {
        super(bgColor, fgColor, title, subText, description);
    }

    @Override
    public int cost() {
        return 0;
    }
}
