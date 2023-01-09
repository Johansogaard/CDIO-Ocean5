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
            hitTax(player);
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
                ownable.setBorder(player.getCar().getPrimaryColor());
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

    }
    private void hitFerry(Player player, GUI_Field[] fields){

    }
    private void hitBrewery(Player player, GUI_Field[] fields){

    }
    private void hitJail(Player player, GUI_Field[] fields){
        if(player.getPos() == 30)
        {
            player.goToJail();
        }
        else
        {
            Game_Controller.getGui().getUserButtonPressed(player.getName() + " du er på besøg i fængsel", "Okay");
        }
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
    private void hitTax(Player player){
        if(player.getPos() == 4)
        {
            if(Game_Controller.getGui().getUserLeftButtonPressed(player.getName()+" Du skal betale indkomstskat på 4000 Kr. eller 10 % af alt hvad du ejer", "4000 kr","10 procent"))
            {
                player.updatePlayerBalance(-4000);
            }
            else
            {
                //her skal vi tage 10% af en spillers værdi i felter og penge og han skal betale det
            }

        }
        else
        {
            Game_Controller.getGui().getUserSelection(player.getName() + " Du skal betale ekstraordinær statsskat på 2000 kr.");
            player.updatePlayerBalance(-2000);
        }
    }
}
