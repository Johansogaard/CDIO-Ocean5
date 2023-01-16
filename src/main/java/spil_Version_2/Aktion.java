package spil_Version_2;

import gui_fields.GUI_Ownable;

import java.util.ArrayList;

public class Aktion {
    private final int minBud = 300;
    private final int antalBydere = 0;
    private final boolean bud = false;
    private final ArrayList<Player> bidders = new ArrayList<>();
    private int currentMax;
    private int aktionVinder;
    private Player player;
    private Player[] players;
    private UserIO userIO;
    private GUI_Ownable field;


    public boolean korAktion(Player player, GUI_Ownable field, int cost, UserIO userIO) {

        int size = Game_Controller.getPlayerList().size();
        this.players = Game_Controller.getPlayerList().toArray(new Player[size]);
        this.userIO = userIO;

        this.field = field;
        this.currentMax = cost;
        int runde = 0;
        //checking who wants to be a part of the aktion
        userIO.showMessage("Aktion holdes for grunden " + field.getTitle());
        for (int i = 0; i < players.length; i++) {
            if (!players[i].getName().equals(player.getName())) {
                if (userIO.getUserLeftButtonPressed(players[i].getName() + " Vil du byde på denne grund", "Ja", "Nej")) {
                    bidders.add(players[i]);
                }
            }
        }
        //Checking if bidders got enough money else they get removed
        for (int i = 0; i < bidders.size(); i = (i + 1) % bidders.size()) {
            if (bidders.get(i).getKonto().getBalance() < currentMax) {
                bidders.remove(i);
                userIO.showMessage(bidders.get(i).getName() + " Er smidt ud af aktionen da han ikke har penge til grunden lige nu");
                //Asking players if they want to buy the field for the currentprice wich goes up by 100 for each person who says yes
            } else if (!userIO.getUserLeftButtonPressed(bidders.get(i).getName() + " Vil du købe denne grund til pris: " + currentMax, "Ja", "Nej")) {
                bidders.remove(i);

            } else {
                currentMax = currentMax + 100;
            }
            if (bidders.size() == 0) {
                return false;
            }
            runde++;
            //checking how many bidders are left to see if there is a winner who will then pay the currentmax and get the field
            if (bidders.size() == 1 && runde > bidders.size()) {
                userIO.showMessage(bidders.get(0).getName() + " Vandt aktionen og ejer nu " + field.getTitle());
                bidders.get(0).buyField(currentMax, field.getTitle());
                field.setBorder(bidders.get(0).getCar().getPrimaryColor());
                field.setOwnableLabel("Ejet af " + player.getName());
                return true;


            }


        }
        return false;
    }
    //this action is ran when a player want to sell there
    public void runSellFieldAktion(Player player, GUI_Ownable field, int cost, UserIO userIO) {
        if (korAktion(player, field, cost, userIO)) {
            int index = 0;
            ArrayList<String> plfields = player.getGrunde();
            for (int i = 0; i < plfields.size(); i++) {
                if (field.getTitle().equals(plfields.get(i))) {
                    index = i;
                }
            }
            plfields.remove(index);
            player.setGrunde(plfields);


        }
    }

}


