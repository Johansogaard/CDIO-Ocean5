package gui_GameFields;

import gui_fields.GUI_Field;
import spil.BoardCreator;
import spil.GameController;
import spil.Konto;
import spil.Player;
import java.util.ArrayList;


import java.awt.*;

import static spil.GameController.getGui;

public class GUI_Brewery extends GUI_Parentfield {

    int price;

    private int t1 = 0;
    private int t2 = 0;

    GUI_Parentfield[] gamefields;
    GameController gameController;
    Player player;
    int[] rent;

    public GUI_Brewery(String title, String subText, String description, int price, int[] rent) {
        super(Color.red, Color.black, title, subText, description);
        this.price = price;
        this.rent = rent;


    }
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
            if ((getOwner().getGrunde()).contains(BoardCreator.getTypeArray("brewery"))) {
                getGui().showMessage(player.getName() + "er landet på" + getTitle() + "hvilket ejes af" + getOwner().getName());
                getGui().getUserButtonPressed(player.getName() + "skal derfor betale" + (200 * player.sum()), "okay");
                player.payRant((200 * player.sum()), getOwner(), getTitle());
                getOwner().getRent((200 * player.sum()));
                System.out.println((getOwner().getGrunde()).contains(BoardCreator.getTypeArray("brewery")));
            }
            //ellers bare det normalt
            else
            {
                getGui().showMessage(player.getName() + "er landet på" + getTitle() + "hvilket ejes af" + getOwner().getName());
                getGui().getUserButtonPressed(player.getName() + "skal derfor betale" + (100 * player.sum()), "okay");
                player.payRant((100 * player.sum()), getOwner(), getTitle());
                getOwner().getRent((100 * player.sum()));
                System.out.println((getOwner().getGrunde()).contains(BoardCreator.getTypeArray("brewery")));
            }
        }

    }
}





