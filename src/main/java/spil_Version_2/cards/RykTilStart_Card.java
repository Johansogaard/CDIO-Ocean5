package spil_Version_2.cards;

import spil_Version_2.Player;

public class RykTilStart_Card extends Parent_Card {
    private int reward;

    //nyt objekt som giver en reward
    public RykTilStart_Card(int reward) {
        this.reward = reward;
        message = "Ryk til start";
    }

    @Override
    public void hit(Player player) {
        // hvis ikke kortet giver en belønning, skrives der kun ryk til start
        if (reward == 0) {
            message ="Ryk til start";
            player.showchancecard(message);
        } else {
            message = "Ryk til start og modtag " + reward + "kr";
            player.showchancecard(message);
        }
        player.movePlayer(0);
        player.updatePlayerBalance(reward);
    }
}

