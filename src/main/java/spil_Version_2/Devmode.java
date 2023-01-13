package spil_Version_2;

import gui_fields.GUI_Field;
import gui_main.GUI;
import spil_Version_2.*;
import spil_Version_2.Player;
import spil_Version_2.cards.Parent_Card;

import java.util.ArrayList;

public class Devmode {
    private Player[] players;
    private GUI_Field[] fields;
    private GUI gui;
    private Player player;
    public void playDevmode(Player[] players, GUI_Field[] fields, GUI gui)
    {
        this.players = players;
        this.fields = fields;
        this.gui = gui;
        String[] playerNames = new String[players.length];
        for (int i = 0; i<players.length;i++)
        {
            playerNames[i] = players[i].getName();
        }
        String choice =gui.getUserSelection("vælg spiller",playerNames);
        this.player = Game_Controller.getPlayer(choice);
        playerOptions();

    }
    private  void playerOptions()
    {

        String c = gui.getUserButtonPressed("Muligheder for "+ player, "1. setpos", "2. setbalance","3. hus/hotel Menu","4. Chancekort","5. Pantsæt menu","6. Spil en tur","7. Tilbage");
        switch (player.getChoice(c)){
            case 1:
            {

                setPosDev();
            }
            break;
            case 2:
            {
                setPlayersBalance();
            }
            break;
            case 4:
            {
               chancekort();
            }
            break;
            case 7:
            {
                playDevmode(players,fields,gui);
            }
            break;
            case 3:
            {
                houseOrHotelMenu();

            }
            break;
            case 5:
            {
                pawnOwnable();
            }
            break;
            case 6:
            {
                player.spil(gui,fields);
                playerOptions();
            }
        }

    }
    private void chancekort()
    {
        ArrayList<String> cardStrings = new ArrayList<>();
        ArrayList<Parent_Card> cardsarr = Game_Features.cards();
        for(int i = 0;i<cardsarr.size();i++)
        {
            cardStrings.add(cardsarr.get(i).getMessage());
        }
        String[]  c = cardStrings.toArray(new String[cardStrings.size()]);
        String chosenString = gui.getUserSelection("Hvilket kort vil du trække",c).replace("<BR>","");
        int chosen=0;
        for(int i = 0;i<cardsarr.size();i++)
        {
            String s = cardStrings.get(i).replace("\n","");

            if (s.equals(chosenString))
            {
                chosen = i;
            }
        }
        cardsarr.get(chosen).hit(player);
        playerOptions();
    }
    private void pawnOwnable()
    {
        player.pawnField();
        playerOptions();
    }
    private void setPlayersBalance()
    {
        int balance = gui.getUserInteger("Skriv "+player.getName()+" balance");
        player.getKonto().setB(balance);
        player.pl.setBalance(player.getKonto().getBalance());
        playerOptions();
    }
    private void houseOrHotelMenu()
    {
        player.hotelHouseMenu();
        playerOptions();
    }
    private void sellHouseOrHotel()
    {
        player.sellHouse();
        playerOptions();

    }
    private void setPosDev()
    {
        int nypos = gui.getUserInteger("Skriv et felt mellem 1 og 40");
        player.setPos(nypos-1);
        player.setCar(player.getPos(), gui);
        player.landOnField.hitField(player,fields);

        playerOptions();

    }
}
