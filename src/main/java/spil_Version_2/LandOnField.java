package spil_Version_2;

import card.*;
import gui_GameFields.GUI_Ferry;
import gui_fields.*;
import spil.GameFeatures;
import spil_Version_2.cards.Parent_Card;

import java.util.ArrayList;


public class LandOnField {


    public void hitField(Player player, GUI_Field[] fields)
    {
        player.displayCard();
        if (GUI_Street.class.equals(fields[player.getPos()].getClass()) ||
                GUI_Brewery.class.equals(fields[player.getPos()].getClass()) ||
                GUI_Shipping.class.equals(fields[player.getPos()].getClass())) {
            hitOwnable(player, fields);
        }
         else if (GUI_Jail.class.equals(fields[player.getPos()].getClass())) {
            hitJail(player, fields);
        }
        else if (GUI_Chance.class.equals(fields[player.getPos()].getClass()))
        {
            hitChance(player, fields);
        } else if (GUI_Tax.class.equals(fields[player.getPos()].getClass())) {

        } else  {

        }

    }
    private  void hitOwnable(Player player, GUI_Field[] fields)
    {
        GUI_Field field = Game_Controller.getGui().getFields()[player.getPos()];
        GUI_Ownable ownable = (GUI_Ownable) field;
        if (ownable.getOwnerName() == null && player.getKonto().getBalance() >=0)
        {
            if(Game_Features.makeYesNoButton(player.getName()+" Vil du købe denne grund")) {
                ownable.setOwnerName(player.getName());
                ownable.setOwnableLabel("Ejet af "+player.getName());
                player.buyField(Integer.parseInt(ownable.getDescription().replaceAll("[^0-9]", "")), ownable.getTitle());
            }
        }
        else if (player.getName() != ownable.getOwnerName())
    {
        Player owner = Game_Controller.getPlayer(ownable.getOwnerName());
        int rent = Integer.parseInt(ownable.getRent());
        owner.getKonto().update(rent); //Her skal der tilføjes når vi får omkostninger hvis man ejer flere grunde
        player.getKonto().update(-rent);
        player.payRent(player.getKonto().getBalance(),owner,ownable.getTitle());
        owner.getRent(owner.getKonto().getBalance());

    }
    }
    private void hitStreet(Player player, GUI_Field[] fields){
        GUI_Field field = Game_Controller.getGui().getFields()[player.getPos()];
        GUI_Street street = (GUI_Street) field;
        if (street.getOwnerName() == null && player.getKonto().getBalance() >=0)
        {

            if(Game_Features.makeYesNoButton(player.getName()+" Vil du købe denne grund")) {
                street.setOwnerName(player.getName());
                street.setOwnableLabel("Ejet af "+ player.getName());
                player.buyField(Integer.parseInt(street.getDescription()), street.getTitle());
                //setDescription(getDescription() + "\nOwner:" + getOwner().getName());
            }
        }
        else if (player.getName() != street.getOwnerName())
        {
            Player owner = Game_Controller.getPlayer(street.getOwnerName());
            int rent = Integer.parseInt(street.getRent());
            owner.getKonto().update(rent); //Her skal der tilføjes når vi får omkostninger hvis man ejer flere grunde
            player.getKonto().update(-rent);
            player.payRent(player.getKonto().getBalance(),owner,street.getTitle());
            owner.getRent(owner.getKonto().getBalance());

        }



    }
    private void hitFerry(Player player, GUI_Field[] fields){

    }
    private void hitBrewery(Player player, GUI_Field[] fields){

    }
    private void hitJail(Player player, GUI_Field[] fields){

    }
    private void hitChance(Player player, GUI_Field[] fields){
        int card =0;
        ArrayList<Parent_Card> cards = Game_Features.cards();
        cards.get(card).hit(player);
        card++;
        if (cards.size()<=card)
        {
            card=0;
        }
    }
    private void hitTax(){

    }
}

