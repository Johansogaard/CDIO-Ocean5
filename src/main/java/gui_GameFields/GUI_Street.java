package gui_GameFields;

import java.awt.*;

public class GUI_Street extends GUI_Parentfield {
    private int[] rent;
    String group;
    private int price, housePrice;

    public GUI_Street(Color bgColor, Color fgColor, String title, String subText, String description,int price, int housePrice, int[] rent,String group) {
        super(bgColor, fgColor, title, subText, description);
        this.rent = rent;
        this.price = price;
        this.housePrice = housePrice;
        this.group = group;

    }


}
