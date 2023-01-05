package card;

import spil.Player;

public class RykTilStart_Card extends Parent_Card {
    private int reward;

    //nyt objekt som giver en reward
    public RykTilStart_Card(int reward) {
        this.reward = reward;
    }

    @Override
    public void hit(Player player) {
        // hvis ikke kortet giver en belønning, skrives der kun ryk til start
        if (reward == 0) {
            player.showchancecard("Ryk til start");
        } else {
            player.showchancecard("Ryk til start og modtag " + reward + "kr");
        }
        player.movePlayer(0);
        player.updatePlayerBalance(reward);
    }
}

