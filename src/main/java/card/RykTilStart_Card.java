package card;

import spil.Player;

public class RykTilStart_Card extends Parent_Card {
    private int reward;

    public RykTilStart_Card(int reward) {
        this.reward = reward;
    }

    @Override
    public void hit(Player player) {
        if (reward == 0) {
            player.showchancecard("Ryk til start");
        } else {
            player.showchancecard("Ryk til start og modtag " + reward + "kr");
        }
        player.movePlayer(0);
        player.updatePlayerBalance(reward);
    }
}

