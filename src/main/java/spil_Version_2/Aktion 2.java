package spil_Version_2;


import gui_fields.GUI_Ownable;
import gui_main.GUI;


public class Aktion {
    private int max;

    private int minBud = 300;
    private int antalBydere = 0;

    private boolean bud = false;
    private int aktionVinder;
    private int[] Bidders;
    private Player player;
    private Player[] players;
    private GUI gui;
    private GUI_Ownable field;


    public Aktion(GUI gui, Player player, Player[] players, GUI_Ownable field) {
        //tjekke description af feltet og fjerne alt, så prisen er tilbage
        this.max = Integer.parseInt(field.getDescription().replaceAll("[^0-9]", ""));
        this.field = field;
        this.Bidders = new int[players.length];
        this.player = player;
        this.players = players;
        this.gui = gui;


    }

    public void KorAktion() {
        gui.getUserButtonPressed("Der afholdes aktion for" + field.getTitle() + "Kør Aktion");

        int i = 0;

        startAntalBydere();


        if (antalBydere == 0) {
            setVinder();
        }
        if(antalBydere == 1){
            while(i<players.length){
                if(actualBidders[i]==1 && players[i].getKonto().getBalance() >= max){
                    if(gui.getUserLeftButtonPressed(players[i].getName()+"Kunne du tænke dig at købe"+field.getTitle(), "ja", "nej");

                }

            }
        }

    }


    private void startAntalBydere() {
        this.antalBydere = 0;
        for (int i = 0; i < players.length; i++) {
            // tjekker at spilleren der sagde nej ikke får lov at byde
            if (players[i] != player) {
                // tjekker om hvor mange der byder på grunden
                if (actualBidders[i] == 0) {
                    actualBidders[i] = 0;
                }
                if (players[i].getKonto().getBalance() >= 0 && actualBidders[i] == 1) {
                    this.actualBidders[i] = 1;
                    this.antalBydere = 1;
                }
                if (players[i].getKonto().getBalance() >= 0 && actualBidders[i] == 2) {

                    this.antalBydere = 2;
                }
                if (players[i].getKonto().getBalance() >= 0 && actualBidders[i] == 3) {

                    this.antalBydere = 3;

                }
                if (players[i].getKonto().getBalance() >= 0 && actualBidders[i] == 4) {

                    this.antalBydere = 4;

                }
                if (players[i].getKonto().getBalance() >= 0 && actualBidders[i] == 5) {

                    this.antalBydere = 5;

                }
            }

        }

    }

    private boolean tjekAktionVinder(){
        if(antalBydere == 1) {
            for (int i = 0; i < players.length; i++) {
                if (actualBidders[i] == 2){
                    aktionVinder = i;
                    return true;
                }

            }
        }
        return false;
    }


    private void hojestBud(int h){
        int hojestBud = (max+minBud);
        switch(gethojestBud(hojestBud();))


    }



    private void Vinder() {
        if (antalBydere == 1) {
            gui.showMessage(players[aktionVinder].getName() + "Har budt højest og dermed vundet aktionen for" + field.getTitle());
            field.setOwnerName(players[aktionVinder].getName());
            players[aktionVinder].getKonto().update(-max);
        } else if (antalBydere <= 0) {
            gui.showMessage("Der var ingen der gad at byde på" + field.getTitle());
        }

    }

}
