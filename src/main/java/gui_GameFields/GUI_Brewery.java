package gui_GameFields;

import spil_Version_2.Board_Creator;
import spil_Version_2.Game_Controller;
import spil_Version_2.Player;
import spil_Version_2.Terninger;


import java.awt.*;

import static spil_Version_2.Game_Controller.getGui;

public class GUI_Brewery extends GUI_Parentfield {

    int price;

    private int t1 = 0;
    private int t2 = 0;

    GUI_Parentfield[] gamefields;
    Game_Controller gameController;
    Player player;
    int[] rent;



    public GUI_Brewery(String title, String subText, String description, int price, int[] rent) {
        super(Color.red, Color.black, title, subText, description);
        this.price = price;
        this.rent = rent;


    }

    public int sum(){
        t1 = Terninger.slaEnTerning();
        t2 = Terninger.slaEnTerning();
        int i = t1 + t2;
        return i;}
//Køb grunden hvis ingen ejer den og man har penge nok
    @Override


    public void hit(Player player) {
        this.player = player;
        if (getOwner() == null) {
            if ((getGui().getUserLeftButtonPressed("Vil du købe grunden", "ja", "nej"))) {
                if (player.getKonto().getBalance() >= price) {
                    setOwner(player);
                    player.buyField(price, getTitle());
                    getGui().showMessage(player.getName() + "har købt" + getTitle());
                    setDescription(getDescription() + "\nOwner:" + getOwner().getName());


                }
            }
            //hvis en anden ejer grunden
        } else if (player != getOwner()) {

//tjek om du har begge bryggerier i deres inventory så kommer der dobbelt pris
            if ((getOwner().getGrunde()).contains(Board_Creator.getTypeArray("brewery"))) {
                getGui().showMessage(player.getName() + "er landet på" + getTitle() + "hvilket ejes af" + getOwner().getName());
                getGui().getUserButtonPressed(player.getName() + "skal derfor betale" + (200 * sum()), "okay");
                player.payRent((200 * sum()), getOwner(), getTitle());
                getOwner().getRent((200 * sum()));

            }
            //ellers bare det normalt
            else
            {
                getGui().showMessage(player.getName() + "er landet på" + getTitle() + "hvilket ejes af" + getOwner().getName());
                getGui().getUserButtonPressed(player.getName() + "skal derfor betale" + (100 * sum()), "okay");
                player.payRent((100 * sum()), getOwner(), getTitle());
                getOwner().getRent((100 * sum()));

            }
        }

    }
}





