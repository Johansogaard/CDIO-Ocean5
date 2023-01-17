package spil_Version_2;

import gui_fields.GUI_Field;
import spil_Version_2.cards.Parent_Card;

import java.util.ArrayList;

public class Devmode {
    private Player[] players;
    private GUI_Field[] fields;
    private UserIO userIO;
    private Player player;
    //a mode where you can control what happens in the game
    public void playDevmode(Player[] players, GUI_Field[] fields, UserIO gui) {
        this.players = players;
        this.fields = fields;
        this.userIO = gui;
        String[] playerNames = new String[players.length];
        for (int i = 0; i < players.length; i++) {
            playerNames[i] = players[i].getName();
        }
        String choice = gui.getUserSelection("vælg spiller", playerNames);
        this.player = Game_Controller.getPlayer(choice, userIO);
        playerOptions();

    }
    //a menu where you can chose what you want to do
    private void playerOptions() {

        int c = userIO.getUserButtonPressed("Muligheder for " + player, "1. setpos", "2. setbalance", "3. hus/hotel Menu", "4. Chancekort", "5. Pantsæt menu", "6. Spil en tur", "7. Tilbage", "8. Skift Til normal mode", "9 sælg field");
        switch (c) {
            case 1: {

                setPosDev();
            }
            break;
            case 2: {
                setPlayersBalance();
            }
            break;
            case 4: {
                chancekort();
            }
            break;
            case 7: {
                playDevmode(players, fields, userIO);
            }
            break;
            case 3: {
                houseOrHotelMenu();

            }
            break;
            case 5: {
                pawnOwnable();
            }
            break;
            case 6: {
                player.spil(fields);
                playerOptions();
            }
            break;
            case 8: {
                Game_Controller.playGame(players, fields);
            }
            break;
            case 9: {
                player.sellField();
                playerOptions();
            }
        }

    }
    //The method makes it posible to chose a card to activate on a player
    private void chancekort() {
        ArrayList<String> cardStrings = new ArrayList<>();
        ArrayList<Parent_Card> cardsarr = Game_Features.cards();
        for (int i = 0; i < cardsarr.size(); i++) {
            cardStrings.add(cardsarr.get(i).getMessage());
        }
        String[] c = cardStrings.toArray(new String[cardStrings.size()]);
        String chosenString = userIO.getUserSelection("Hvilket kort vil du trække", c).replace("<BR>", "");
        int chosen = 0;
        for (int i = 0; i < cardsarr.size(); i++) {
            String s = cardStrings.get(i).replace("\n", "");

            if (s.equals(chosenString)) {
                chosen = i;
            }
        }
        cardsarr.get(chosen).hit(player, userIO);
        playerOptions();
    }

    private void pawnOwnable() {
        player.pawnMenu();
        playerOptions();
    }

    private void setPlayersBalance() {
        int balance = userIO.getUserInteger("Skriv " + player.getName() + " balance");
        player.getKonto().setB(balance);
        player.pl.setBalance(player.getKonto().getBalance());
        playerOptions();
    }

    private void houseOrHotelMenu() {
        player.hotelHouseMenu();
        playerOptions();
    }

    private void sellHouseOrHotel() {
        player.sellHouse();
        playerOptions();

    }

   public void setPosDev() {
        int nypos = userIO.getUserInteger("Skriv et felt mellem 1 og 40");
        player.setPos(nypos - 1);
        player.setFpos(userIO.setCar(player.getPos(), player.pl, player.fpos));
        player.landOnField.hitField(player, fields, userIO);

        playerOptions();

    }
}
