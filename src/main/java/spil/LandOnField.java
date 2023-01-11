package spil;

import gui_GameFields.GUI_Ferry;
import gui_fields.*;
import spil.cards.*;
;

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
        GUI_Field field = GameController.getGui().getFields()[player.getPos()];
        GUI_Ownable ownable = (GUI_Ownable) field;
        if (ownable.getOwnerName() == null && player.getKonto().getBalance() >=0)
        {
            if(GameFeatures.makeYesNoButton(player.getName()+" Vil du købe denne grund")) {
                ownable.setOwnerName(player.getName());
                ownable.setBorder(player.getCar().getPrimaryColor());
                ownable.setOwnableLabel("Ejet af "+player.getName());

            }
        }
        else if (player.getName() != ownable.getOwnerName())
    {
        if (player.checkOwnerOwnAll())
        {
            if (ownable.getClass().equals(GUI_Street.class))
            {
                hitStreet(player,fields);
            } else if (ownable.getClass().equals(GUI_Ferry.class)) {
                hitFerry(player,fields);

            }
            else {
                hitBrewery(player,fields);
            }

        }
        else {
            Player owner = GameController.getPlayer(ownable.getOwnerName());
            int rent = Integer.parseInt(ownable.getRent());
            player.payRent(rent,owner,ownable.getTitle());

        }

    }
    }
    //DE her metoder skal indeholde hvad en spiller skal betale hvis de lander på et felt hvor spilleren ejer alle felter
    private void hitStreet(Player player, GUI_Field[] fields){
        GUI_Field field = fields[player.getPos()];
        GUI_Street street = (GUI_Street) field;

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
            GameController.getGui().getUserButtonPressed(player.getName() + " du er på besøg i fængsel", "Okay");
        }
    }
    private void hitChance(Player player, GUI_Field[] fields){
        int card =0;
        ArrayList<Parent_Card> cards = GameFeatures.cards();
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
            if(GameController.getGui().getUserLeftButtonPressed(player.getName()+" Du skal betale indkomstskat på 4000 Kr. eller 10 % af alt hvad du ejer", "4000 kr","10 procent"))
            {
                player.updatePlayerBalance(-4000);
            }
            else
            {
                int num = player.getKonto().getBalance()+player.getKonto().getFieldvalue();
                int rounded =((num+99)/100)*100;
                Game_Controller.getGui().getUserButtonPressed(player.getName()+" du betaler "+rounded+" i skat");
                player.updatePlayerBalance(-rounded);//her skal vi tage 10% af en spillers værdi i felter og penge og han skal betale det
            }

        }
        else
        {
            GameController.getGui().getUserButtonPressed(player.getName() + " Du skal betale ekstraordinær statsskat på 2000 kr.");
            player.updatePlayerBalance(-2000);
        }
    }
}

