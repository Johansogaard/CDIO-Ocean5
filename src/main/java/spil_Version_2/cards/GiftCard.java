package spil_Version_2.cards;

import spil_Version_2.Game_Controller;
import spil_Version_2.Player;
import spil_Version_2.UserIO;

public class GiftCard extends Parent_Card {

    private int amount;

    public GiftCard(int amount,String message) {
        this.amount = amount;
        this.message=message;
    }

    @Override
    public void hit(Player player, UserIO userIO)  {
        player.showchancecard(message);

        for (Player otherPlayer : Game_Controller.getPlayers()) {
            if (otherPlayer != player) {
                player.updatePlayerBalance(amount);
                otherPlayer.updatePlayerBalance(-amount);

            }



        }
    }
}

