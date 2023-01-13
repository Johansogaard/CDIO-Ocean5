package spil_Version_2;

import gui_fields.GUI_Ownable;
import gui_main.GUI;

import java.util.ArrayList;

import java.util.Iterator;

public class Aktion2 {
    private int currentMax;

    private int minBud = 300;
    private int antalBydere = 0;

    private boolean bud = false;
    private int aktionVinder;

    private ArrayList<Player> Bidders = new ArrayList<>();

    private Player player;
    private Player[] players;
    private GUI gui;
    private GUI_Ownable field;


    public void korAktion(Player player, GUI_Ownable field) {
        int cost = Integer.parseInt(Board_Creator.getFieldData().get(player.getPos())[3]);
        this.player = player;
        this.gui = Game_Controller.getGui();
        this.players = Game_Controller.getPlayers();
        this.field = field;
        this.currentMax = cost;


        gui.showMessage("Aktion holdes for grunden" + field.getTitle());

        int i = 0;

        checkBidders();

        if (antalBydere == 1) {

            if (Bidders.get(i).getKonto().getBalance() >= currentMax) {
                if (gui.getUserLeftButtonPressed(Bidders.get(i).getName() + "har vundet aktionen. Vil du betale" + currentMax + "for grunden?", "betal", "betal ikke")) {
                    Vinder();
                } else {
                    gui.showMessage("Du have valgt ikke at købe grunden, spillet kører videre Dr Anders");
                    antalBydere = 0;
                    Vinder();
                }


            } else {
                gui.showMessage(Bidders.get(i).getName() + "har ikke nok penge og kan derfor ikke købe grunden");

            }

        }
        if (antalBydere > 1) {
            while (i < Bidders.size()) {

                if (Bidders.get(i).getKonto().getBalance() >= currentMax && antalBydere >= 1 && !players[i].equals(player)) {
                    if (gui.getUserLeftButtonPressed(Bidders.get(i).getName() + "Vil du stadig byde på" + field.getTitle() + "Nuværende pris er" + max, "ja", "nej")) {
                        int bid = gui.getUserInteger("Hvor meget vil du smide efter grunden? (minimum: " + (currentMax) + "):", (currentMax), Bidders.get(i).getKonto().getBalance());
                        if(bid > currentMax) currentMax = bid;
                        //update the currentMax with the new bid if it is higher than the current max

                        //skift denne spiller til den højeste bydene (lav funktion)
                    } else {
                        gui.showMessage(Bidders.get(i).getName() + "har valgt at udgå aktionen");
                        Bidders.remove(i);
                        antalBydere--;
                    }

                } else if (Bidders.get(i).getKonto().getBalance() <= currentMax) {
                    gui.getUserButtonPressed(Bidders.get(i).getName() + "har desværre ikke nok på kontoen til at deltage, og ryger derfor ud af aktionen", "dang it");
                    Bidders.remove(i);
                    antalBydere--;

                } else if (players[i].equals(player)) {
                    Bidders.remove(i);
                }

                i++;
                if (i >= Bidders.size()) i = 0;
                if (i >= players.length) i = 0;
                if (tjekVinder() == true) i = 7;
                else if (antalBydere == 0) i = 7;


            }

        }
        if (tjekVinder() == true) {
            Vinder();
        }


    }

    // opretter et nyt arraylist med spillerne i, hvorefter der fjernes de spillere som ikke vil deltage i aktionen
    public void checkBidders() {
        this.antalBydere = 0;
        this.player = player;
        for (int i = 0; i < players.length; i++) {
            Bidders.add(players[i]);

                if (Bidders.get(i).equals(player)) {
                    Bidders.remove(i);
                }

                if (!players[i].equals(player)) {

                    if (gui.getUserLeftButtonPressed(Bidders.get(i).getName() + "Hvil du være med i aktionen om" + field.getTitle(), "ja", "nej")) {
                        if (Bidders.get(i).getKonto().getBalance() >= max) {

                            antalBydere++;
                        }

                    } else {
                        Bidders.remove(i);
                    }
                }

        }

            }






        private boolean tjekVinder() {
            if (antalBydere == 1) {
                for (int i = 0; i < Bidders.size(); i++) {
                    if (Bidders.size() == 1) {
                        Bidders.get(i);
                        aktionVinder = i;
                        return true;
                    }
                }
            }
            return false;
        }

        private void hojestBud ( int h){
            for (int i = 0; i < players.length; i++) {

            }


        }

        private void Vinder () {
            if (antalBydere == 1) {
                gui.showMessage(players[aktionVinder].getName() + "Har budt højest og dermed vundet aktionen for" + field.getTitle());
                field.setOwnerName(players[aktionVinder].getName());
                players[aktionVinder].getKonto().update(-max);
            } else if (antalBydere <= 0) {
                gui.showMessage("Der var ingen der gad at byde på" + field.getTitle());
            }

        }
    }


