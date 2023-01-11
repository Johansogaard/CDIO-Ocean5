package spil_Version_2;


import gui_fields.*;

import spil_Version_2.cards.Parent_Card;
;

import java.util.ArrayList;
import java.util.List;

import static spil_Version_2.Game_Controller.getGui;


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

            }
        }
        else if (player.getName() != ownable.getOwnerName())
    {
        Player owner = Game_Controller.getPlayer(ownable.getOwnerName());
        if(ownable.getClass().equals(GUI_Brewery.class))
        {
            hitBrewery(player,owner, fields);
        }
        if (player.checkOwnerOwnAll())
        {
            if (ownable.getClass().equals(GUI_Street.class))
            {
                hitStreet(player,fields);
            } else if (ownable.getClass().equals(GUI_Shipping.class)) {
                hitFerry(player,owner,fields);

            }


        }
        else {

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
    private void hitFerry(Player player,Player owner, GUI_Field[] fields){
        String titel=fields[player.getPos()].getTitle();
        // sammenligneren ferry array og owneren hvor man får et array med fælles som man så tager længden af og putter ind i switch med priser
        player.payRent(howManyFerry(owner), owner, titel);
    }
    private void hitBrewery(Player player,Player owner, GUI_Field[] fields){

    if (player.checkOwnerOwnAll()) {
            player.payRent((200 * player.getTerningeSum()), owner,fields[player.getPos()].getTitle());
        }
        //ellers bare det normalt
        else
        {
           /* getGui().showMessage(player.getName() + "er landet på" + getTitle() + "hvilket ejes af" + getOwner().getName());
            getGui().getUserButtonPressed(player.getName() + "skal derfor betale" + (100 * sum()), "okay");*/
            player.payRent((100 * player.getTerningeSum()), owner, fields[player.getPos()].getTitle());


        }
    }
    private int howManyFerry(Player owner){

        List<String> common = new ArrayList<>(owner.getGrunde());
        common.retainAll(Board_Creator.getGroupArray("ferry"));
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
                int num = player.getKonto().getBalance()+player.getKonto().getFieldvalue();
                int rounded =((num+99)/100)*100;
                Game_Controller.getGui().getUserButtonPressed(player.getName()+" du betaler "+rounded+" i skat");
                player.updatePlayerBalance(-rounded);//her skal vi tage 10% af en spillers værdi i felter og penge og han skal betale det
            }

        }
        else
        {
            Game_Controller.getGui().getUserButtonPressed(player.getName() + " Du skal betale ekstraordinær statsskat på 2000 kr.");
            player.updatePlayerBalance(-2000);
        }
    }
}

