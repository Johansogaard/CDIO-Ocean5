package gui_GameFields;

import gui_fields.GUI_Field;
import spil.GameController;
import spil.Konto;
import spil.Player;

import java.awt.*;

import static spil.GameController.getGui;

public class GUI_Brewery extends GUI_Parentfield{

    int price;

    private int t1 = 0;
    private int t2 = 0;

    GUI_Parentfield[] gamefields;

    Player player;
    int[] rent;

    public GUI_Brewery(String title, String subText, String description,int price,int[] rent) {
        super(Color.red, Color.black, title, subText, description);
        this.price = price;
        this.rent = rent;



    }

    @Override
    public void hit(Player player)
    {
        this.player = player;
        if(getOwner() == null){
            if ((getGui().getUserLeftButtonPressed("Vil du købe grunden", "ja", "nej"))) {
                if(player.getKonto().getBalance() >= price){
                    setOwner(player);
                    player.buyField(price, getTitle());
                    getGui().showMessage(player.getName()+"har købt"+getTitle());
                    setDescription(getDescription() + "\nOwner:" + getOwner().getName());

                }
            }
            }
        else if(player != getOwner())
        {
            if(this instanceof GUI_Brewery){
                if(gamefields[12].getOwner() == player){
                    getGui().showMessage(player.getName()+"er landet på"+getTitle()+"hvilket ejes af"+getOwner().getName());
                    getGui().getUserButtonPressed(player.getName()+"skal derfor betale" + (100*player.sum()), "okay");
                    player.payRant((100*player.sum()),getOwner(),getTitle());
                    getOwner().getRent(100*player.sum());

                }
                if(gamefields[28].getOwner() == player){
                    getGui().showMessage(player.getName()+"er landet på"+getTitle()+"hvilket ejes af"+getOwner().getName());
                    getGui().getUserButtonPressed(player.getName()+"skal derfor betale" + (100*player.sum()), "okay");
                    player.payRant((100*player.sum()),getOwner(),getTitle());
                    getOwner().getRent(100*player.sum());
                }
                if(gamefields[28].getOwner() == player && gamefields[12].getOwner() == player)
                    getGui().showMessage(player.getName()+"er landet på"+getTitle()+"hvilket ejes af"+getOwner().getName() + "og du skal betale dobbelt da begge bryggerier ejes");
                getGui().getUserButtonPressed(player.getName()+"skal derfor betale" + (200*player.sum()), "okay");
                    player.payRant((200*player.sum()),getOwner(),getTitle());
                getOwner().getRent((200*player.sum()));
            }

        }

    }


    }



