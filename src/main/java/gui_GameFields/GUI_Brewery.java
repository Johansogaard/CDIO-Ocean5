package gui_GameFields;

import gui_fields.GUI_Field;
import spil.Konto;
import spil.Player;

import java.awt.*;

import static spil.GameController.getGui;

public class GUI_Brewery extends GUI_Parentfield{

    int price;

    GUI_Parentfield[] gamefields;

    int[] rent;

    public GUI_Brewery(String title, String subText, String description,int price,int[] rent) {
        super(Color.red, Color.black, title, subText, description);
        this.price = price;
        this.rent = rent;




    }

    @Override
    public void hit(Player player)
    {
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
                    player.payRant((100*spil.Player.),getOwner(),getTitle());
                    getOwner().getRent(100*spil.Player.(t1+t2),getOwner(),getTitle());

                }
                if(gamefields[22].getOwner() == player){
                    player.payRant((100*spil.Player.turn(t1+t2));
                    getOwner().getRent(100*spil.Player.turn(t1+t2));
                }
            else ()
                player.payRant();
            }

            getGui().showMessage(player.getName()+"er landet på"+getTitle()+"hvilket ejes af"+getOwner().getName());
            getGui().getUserButtonPressed(player.getName()+"skal derfor betale" + rent[0], "okay");
            player.payRant(rent[0], getOwner(), getTitle());
            getOwner().getRent(rent[0]);

        }

    }

    }


}
