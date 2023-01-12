package spil_Version_2.cards;

import spil_Version_2.Game_Controller;
import spil_Version_2.Player;

public class GiftCard extends Parent_Card {
    private final String message;
    private int amount;

    public GiftCard(int amount,String message) {
        this.amount = amount;
        this.message=message;
    }

    @Override
    public void hit(Player player) {
        player.showchancecard(message);

        for (Player otherPlayer : Game_Controller.getPlayers()) {
            if (otherPlayer != player) {
                player.updatePlayerBalance(amount);
                otherPlayer.updatePlayerBalance(-amount);

            }



        }
    }
}

