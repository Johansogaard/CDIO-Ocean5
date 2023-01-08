package gui_GameFields;

import java.awt.*;

public class GUI_Street extends GUI_Ownable {
    private int[] rent;
    String group;
    private int housePrice;

    public GUI_Street(Color bgColor, Color fgColor, String title, String subText, String description,int price, int housePrice, int[] rent,String group) {
        super(bgColor, fgColor, title, subText, description,Integer.toString(rent[0]));
        this.rent = rent;
        setPrice(price);
        this.housePrice = housePrice;
        this.group = group;

    }

    public String getColor(){
        return group;
    }

}

