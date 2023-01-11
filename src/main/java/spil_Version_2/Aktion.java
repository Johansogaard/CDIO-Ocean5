package spil_Version_2;

import gui_GameFields.GUI_Ownable;
import gui_main.GUI;


public class Aktion {
    private int max;
    private int antalBydere = 0;
    private boolean bud = false;
    private int aktionVinder;
    private int[] actualBidders;
    private Player player;
    private Player[] players;
    private GUI gui;
    private GUI_Ownable field;





    public Aktion(GUI gui, Player player, Player[] players, GUI_Ownable field){
        this.max = Integer.parseInt(field.getDescription().replaceAll("[^0-9]", ""));
        this.field = field;
        this.actualBidders = new int[players.length];
        this.player = player;
        this.players = players;
        this.gui = gui;


    }

    public void KorAktion(){
        gui.getUserButtonPressed("Der afholdes aktion for"+field.getTitle(),"Kør Aktion");

    }
}
