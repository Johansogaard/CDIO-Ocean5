package spil_Version_2.cards;

import spil_Version_2.Game_Controller;
import spil_Version_2.Player;
import spil_Version_2.UserIO;

public class GiftCard extends Parent_Card {

    private final int amount;

    //initialisering af amount og message
    public GiftCard(int amount, String message) {
        this.amount = amount;
        this.message = message;
    }

    //overskrivning af parent class
    @Override
    public void hit(Player player, UserIO userIO) {
        //viser spilleren chance kortet
        player.showchancecard(message);

        //alle de andre spillere
        for (Player otherPlayer : Game_Controller.getPlayers()) {
            if (otherPlayer != player) {
                //tilføjer penge til spilleren
                player.updatePlayerBalance(amount);
                //trækker penge fra de andre spillere
                otherPlayer.updatePlayerBalance(-amount);

            }


        }
    }
}

