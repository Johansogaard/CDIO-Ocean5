package spil_Version_2;

import gui_GameFields.GUI_Ferry;
import gui_fields.*;
import spil.GameController;

public class LandOnField {

    public void hitField(Player player, GUI_Field[] fields)
    {
        player.displayCard();
        if (GUI_Street.class.equals(fields[player.getPos()].getClass())) {
            hitStreet(player, fields);
        }
        else if (GUI_Brewery.class.equals(fields[player.getPos()].getClass()))
        {
            hitBrewery(player, fields);
        }
        else if (GUI_Shipping.class.equals(fields[player.getPos()].getClass()))
        {
            hitFerry(player, fields);
        } else if (GUI_Jail.class.equals(fields[player.getPos()].getClass())) {
            hitJail(player, fields);
        }
        else if (GUI_Chance.class.equals(fields[player.getPos()].getClass()))
        {
            hitChance(player, fields);
        } else if (GUI_Tax.class.equals(fields[player.getPos()].getClass())) {

        } else  {

        }

    }
    private void hitStreet(Player player, GUI_Field[] fields){
        GUI_Field field = GameController.getGui().getFields()[player.getPos()];
        GUI_Street street = (GUI_Street) field;
        if (fields[player.getPos()].get)
        {
            if(gameFeatures.makeYesNoButton(player.getName()+" Vil du købe denne grund")) {
                setOwner(player);
                player.buyField(price, getTitle());
                setDescription(getDescription() + "\nOwner:" + getOwner().getName());
            }
        }
        else if(player != getOwner())
        {
            getOwner().getKonto().update(price* player.checkDoubleCost());
            player.getKonto().update(-price*player.checkDoubleCost());
            player.payRent(player.getKonto().getBalance(),owner,getTitle());
            getOwner().getRent(getOwner().getKonto().getBalance());


        }


    }
    private void hitFerry(Player player, GUI_Field[] fields){

    }
    private void hitBrewery(Player player, GUI_Field[] fields){

    }
    private void hitJail(Player player, GUI_Field[] fields){

    }
    private void hitChance(Player player, GUI_Field[] fields){

    }
    private void hitTax(){

    }
}

