package spil_Version_2.cards;

import spil_Version_2.Game_Controller;
import spil_Version_2.LandOnField;
import spil_Version_2.Player;
import spil_Version_2.UserIO;

public class PrisonCard extends Parent_Card{

    public PrisonCard(String Message) {
        this.message= Message;
    }

    @Override
    public void hit(Player player, UserIO userIO)  {
        this.message =("I anledning af kongens fødselsdag benådes De herved for fængsel. Dette kort kan opbevares indtil De får brug for det, eller De kan sælge det.");
        player.showchancecard("I anledning af kongens fødselsdag benådes De herved for fængsel. Dette kort kan opbevares indtil De får brug for det, eller De kan sælge det.");

        player.prisoncard = true;
    }
}