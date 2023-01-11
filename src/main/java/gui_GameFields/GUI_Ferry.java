package gui_GameFields;
import java.util.ArrayList;
import java.util.List;
import spil.BoardCreator;
import spil.Player;

import java.awt.*;

import static spil.GameController.getGui;

public class GUI_Ferry extends GUI_Parentfield{
    int price;
    Player player;
    int[] rent;
    public GUI_Ferry(String title, String subText, String description,int price,int[] rent) {
        super(Color.gray, Color.black, title, subText, description);
        this.price = price;
        this.rent = rent;
    }

    // sammenligneren ferry array og owneren hvor man får et array med fælles som man så tager længden af og putter ind i switch med priser
    private int howManyFerry(){

        List<String> common = new ArrayList<>(getOwner().getGrunde());
        common.retainAll(BoardCreator.getTypeArray("ferry"));
        int multi=0;
        switch (common.size()){
            case 1: {
                multi=500;
            }
            break;
            case 2: {
                multi=1000;
            }
            break;
            case 3: {
                multi=2000;
            }
            break;
            case 4: {
                multi=4000;
            }
            break;
        }



    return multi;
        }


//købe
    @Override
    public void hit(Player player)
    {

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

        }
        //betale
        else if (player != getOwner()) {



                getGui().showMessage(player.getName() + "er landet på" + getTitle() + "hvilket ejes af" + getOwner().getName());
                getGui().getUserButtonPressed(player.getName() + "skal derfor betale" + howManyFerry(), "okay");
                player.payRent(howManyFerry(), getOwner(), getTitle());
                getOwner().getRent(howManyFerry());




        }

    }
    }

