package spil_Version_2;

import gui_fields.GUI_Ownable;
import gui_main.GUI;

import java.util.ArrayList;

public class Aktion2 {
        private int max;

        private int minBud = 300;
        private int antalBydere = 0;

        private boolean bud = false;
        private int aktionVinder;

        private ArrayList<Player> Bidders;

        private Player player;
        private Player[] players;
        private GUI gui;
        private GUI_Ownable field;


        public Aktion2(GUI gui, Player player, Player[] players, GUI_Ownable field) {
            //tjekke description af feltet og fjerne alt, så prisen er tilbage
            this.max = Integer.parseInt(field.getDescription().replaceAll("[^0-9]", ""));
            this.field = field;
            this.player = player;
            this.players = players;
            this.gui = gui;
            this.Bidders = new ArrayList<Player>();

        }



        public void korAktion(){

            gui.showMessage("Aktion holdes for grunden"+field.getTitle());

            int i = 0;

            checkBidders();

            if(antalBydere == 1){

                    if(Bidders.get(i).getKonto().getBalance()>=max){
                        gui.getUserButtonPressed(Bidders.get(i).getName()+"har vundet aktionen og skal betale"+max,"Betal");
                        Bidders.get(i).getKonto().update(-max);

                    }
                    else {
                        gui.showMessage(Bidders.get(i).getName()+"har ikke nok penge og kan derfor ikke købe grunden");

                    }

            }
            if(antalBydere>1){
                while(i<Bidders.size()){
                    if(Bidders.get(i).getKonto().getBalance()>=max && antalBydere >=1){
                        if(gui.getUserLeftButtonPressed("Kunne du tænke dig at byde på"+field.getTitle()+"min:"+(max+minBud),"ja","nej")){
                            max = gui.getUserInteger("Hvor meget vil du smide efter grunden? (minimum: " + (max + minBud) + "):", (max + minBud), Bidders.get(i).getKonto().getBalance());
                            //skift denne spiller til den højeste bydene (lav funktion)
                        }
                        else{
                            Bidders.remove(i);
                        }
                        if(Bidders.get(i).getKonto().getBalance()<=max){
                              gui.getUserButtonPressed(Bidders.get(i).getName()+"har desværre ikke nok på kontoen til at deltage, og ryger derfor ud af aktionen","dang it") ;
                            Bidders.remove(i);

                        }
                    }
                }

            }
            i++;
        }

        // opretter et nyt arraylist med spillerne i, hvorefter der fjernes de spillere som ikke vil deltage i aktionen
        public void checkBidders(){

            antalBydere = 0;
            for (int i = 0; i < Bidders.size(); i++){
                Bidders.add(players[i]);
                if(!players[i].equals(player)) {
                    if (gui.getUserLeftButtonPressed("Hvil du være med i aktionen om" + field.getTitle(), "ja", "nej")) {
                        if (Bidders.get(i).getKonto().getBalance() >= max) {

                            antalBydere++;
                        }
                        if(players[i].equals(player)){
                            Bidders.remove(i);
                        }
                    } else {
                        Bidders.remove(i);
                    }
                }

            }

        }
}
