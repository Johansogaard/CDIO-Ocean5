package spil_Version_2;


import gui_fields.*;
import spil_Version_2.cards.Parent_Card;

import java.util.ArrayList;


public class LandOnField {
    private UserIO userIO;
    //hitfield checks wich type of field you land on
    public void hitField(Player player, GUI_Field[] fields, UserIO gui) {
        this.userIO = gui;
        player.displayCard();
        if (GUI_Street.class.equals(fields[player.getPos()].getClass()) ||
                GUI_Brewery.class.equals(fields[player.getPos()].getClass()) ||
                GUI_Shipping.class.equals(fields[player.getPos()].getClass())) {
            hitOwnable(player, fields);
        } else if (GUI_Jail.class.equals(fields[player.getPos()].getClass())) {
            hitJail(player, fields);
        } else if (GUI_Chance.class.equals(fields[player.getPos()].getClass())) {
            hitChance(player);
        } else if (GUI_Tax.class.equals(fields[player.getPos()].getClass())) {
            hitTax(player);
        } else {

        }

    }
    //hitOwnable is used when landing on a ownable field that you either can buy or needs to pay rent if a player owns it
    private void hitOwnable(Player player, GUI_Field[] fields) {
        GUI_Field field = Game_Controller.getFields()[player.getPos()];
        GUI_Ownable ownable = (GUI_Ownable) field;

        Aktion1 aktion = new Aktion1();
        int cost = Integer.parseInt(Board_Creator.getFieldData().get(player.getPos())[3]);
        if (ownable.getOwnerName() == null) {
            if (userIO.getUserLeftButtonPressed(player.getName() + " Vil du købe denne grund", "Ja", "Nej")) {
                if (player.getKonto().getBalance() < cost) {
                    userIO.showMessage("Du har ikke råd til grunden");
                    aktion.korAktion(player, ownable, cost, userIO);
                } else {
                    ownable.setBorder(player.getCar().getPrimaryColor());
                    ownable.setOwnableLabel("Ejet af " + player.getName());
                    ownable.setOwnerName(player.getName());
                    player.buyField(cost, ownable.getTitle());
                }
            } else {
                aktion.korAktion(player, ownable, cost, userIO);
            }

        } else if (player.getName() != ownable.getOwnerName() && !ownable.getTitle().equals("PANTSAT") && !Game_Controller.getPlayer(ownable.getOwnerName(), userIO).isJail()) {
            Player owner = Game_Controller.getPlayer(ownable.getOwnerName(), userIO);
            if (ownable.getClass().equals(GUI_Brewery.class)) {
                hitBrewery(player, owner, fields);
            } else if (ownable.getClass().equals(GUI_Shipping.class)) {
                hitFerry(player, owner, fields);

            } else if (player.checkOwnerOwnAll()) {
                if (ownable.getClass().equals(GUI_Street.class)) {
                    hitStreet(player, owner, fields);
                }

            } else if (!ownable.getTitle().equals("PANTSAT") && !Game_Controller.getPlayer(ownable.getOwnerName(), userIO).isJail()) {

                int rent = Integer.parseInt(ownable.getRent());
                player.payRent(rent, owner, ownable.getTitle());

            } else {
            }

        }
    }

    public void testHitOwnable(Player player, GUI_Field[] fields) {
        hitOwnable(player, fields);
    }

    //hitStreet is activated when all of the fields in a group is owned by the owner
    //it checks if there is any houses on the field
    private void hitStreet(Player player, Player Owner, GUI_Field[] fields) {
        GUI_Field field = fields[player.getPos()];
        GUI_Street street = (GUI_Street) field;
        int index = Board_Creator.fieldIndexFromName(street.getTitle());
        ArrayList<String[]> data = Board_Creator.getFieldData();
        int numberOfHouses = Integer.parseInt(data.get(index)[12]);
        String titel = fields[player.getPos()].getTitle();
        player.payRent(Integer.parseInt(data.get(index)[5 + numberOfHouses]), Owner, titel);


    }
    //if all ferrys is owned this method decides how much rent you need to pay
    private void hitFerry(Player player, Player owner, GUI_Field[] fields) {
        String titel = fields[player.getPos()].getTitle();
        // sammenligneren ferry array og owneren hvor man får et array med fælles som man så tager længden af og putter ind i switch med priser
        player.payRent(howManyFerry(owner), owner, titel);
    }
    //checks if you own all brewery and how much you need to pay in rent
    private void hitBrewery(Player player, Player owner, GUI_Field[] fields) {

        if (player.checkOwnerOwnAll()) {
            player.payRent((200 * player.getTerningeSum()), owner, fields[player.getPos()].getTitle());
        }
        //ellers bare det normalt
        else {
           /* getGui().showMessage(player.getName() + "er landet på" + getTitle() + "hvilket ejes af" + getOwner().getName());
            getGui().getUserButtonPressed(player.getName() + "skal derfor betale" + (100 * sum()), "okay");*/
            player.payRent((100 * player.getTerningeSum()), owner, fields[player.getPos()].getTitle());


        }
    }
    //checks how many ferrys is owned
    private int howManyFerry(Player owner) {

        ArrayList<String> common = owner.getGrunde();
        ArrayList<String[]> data = Board_Creator.getFieldData();
        int numberOfFerrys = 0;
        ArrayList<String[]> ferryFields = Board_Creator.getGroupArray("ferry");
        for (int i = 0; i < common.size(); i++) {
            for (int p = 0; p < ferryFields.size(); p++) {
                int index = Board_Creator.fieldIndexFromName(common.get(i));
                if (Integer.parseInt(data.get(index)[13]) == 1) {

                } else if (common.get(i).equals(ferryFields.get(p)[0])) {
                    numberOfFerrys++;
                }
            }
        }

        int multi = 0;
        switch (numberOfFerrys) {
            case 1: {
                multi = 500;
            }
            break;
            case 2: {
                multi = 1000;
            }
            break;
            case 3: {
                multi = 2000;
            }
            break;
            case 4: {
                multi = 4000;
            }
            break;
        }


        return multi;
    }
    //takes the player to jail if a prinsoncard is not owned
    private void hitJail(Player player, GUI_Field[] fields) {
        if (player.getPos() == 30) {
            if (player.prisoncard) {
                userIO.showMessage(player.getName() + " du havde fængselskort, derfor kommer du ikke i fængsel");
                player.prisoncard = false;

            } else {
                player.goToJail();
            }
        } else {
            userIO.showMessage(player.getName() + " du er på besøg i fængsel");
        }
    }
    //gets a card from the cards and activates ont he player
    private void hitChance(Player player) {
        int card = 0;
        ArrayList<Parent_Card> cards = Game_Features.cards();
        cards.get(card).hit(player, userIO);
        card++;
        if (cards.size() <= card) {
            card = 0;
        }
    }

    private void hitTax(Player player) {
        if (player.getPos() == 4) {
            if (userIO.getUserLeftButtonPressed(player.getName() + " Du skal betale indkomstskat på 4000 Kr. eller 10 % af alt hvad du ejer", "4000 kr", "10 procent")) {
                player.updatePlayerBalance(-4000);
            } else {
                int num = (player.getKonto().getBalance() + player.getKonto().getFieldvalue()) / 10;
                int rounded = ((num + 99) / 100) * 100;
                userIO.showMessage(player.getName() + " du betaler " + rounded + " i skat");
                player.updatePlayerBalance(-rounded);//her skal vi tage 10% af en spillers værdi i felter og penge og han skal betale det
            }

        } else {
            userIO.showMessage(player.getName() + " Du skal betale ekstraordinær statsskat på 2000 kr.");
            player.updatePlayerBalance(-2000);
        }
    }
}

