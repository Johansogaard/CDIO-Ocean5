package spil_Version_2.cards;

import spil_Version_2.Game_Controller;
import spil_Version_2.LandOnField;
import spil_Version_2.Player;
import spil_Version_2.UserIO;

// arver fra parent_card - kort som giver spilleren et get out of jail kort
public class PrisonCard extends Parent_Card{

    public PrisonCard(String Message) {
        this.message= Message;
    }

    //overskriver hit metoden
    @Override
    public void hit(Player player, UserIO userIO)  {
        this.message =("I anledning af kongens fødselsdag benådes De herved for fængsel. Dette kort kan opbevares indtil De får brug for det.");
        player.showchancecard("I anledning af kongens fødselsdag benådes De herved for fængsel. Dette kort kan opbevares indtil De får brug for det");
//giver vedkommende et get-out-of-jail-card
        player.prisoncard = true;
    }
}