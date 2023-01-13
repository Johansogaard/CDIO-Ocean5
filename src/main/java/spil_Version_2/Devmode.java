package spil_Version_2;

import gui_fields.GUI_Field;
import gui_main.GUI;
import spil_Version_2.*;
import spil_Version_2.Player;

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

        String c = gui.getUserButtonPressed("Muligheder for "+ player, "1. setpos", "2. setbalance","3. Sæt hus/Hotel","4. sælg Hus/Hotel","5. Pantsæt menu","6. Tilbage");
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
               sellHouseOrHotel();
            }
            break;
            case 6:
            {
                playDevmode(players,fields,gui);
            }
            break;
            case 3:
            {
                setHouseOrHotel();

            }
            break;
            case 5:
            {
                pawnOwnable();
            }
        }

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
    private void setHouseOrHotel()
    {
        player.buyHouse();
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
