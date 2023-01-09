package gui_GameFields;

import java.awt.*;

public class GUI_Street extends GUI_Parentfield {

    private int[] rent;
    private String group;
    private int housePrice;





    public GUI_Street(Color bgColor, Color fgColor, String title, String subText, String description,int price, int housePrice, int[] rent,String group) {
        super(bgColor, fgColor, title, subText, description);

        setPrice(price);
        this.housePrice = housePrice;
        this.group = group;
        setRent(rent);

    }


    public String getColor(){
        return group;
    }

}
