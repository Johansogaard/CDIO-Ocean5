package card;

import spil.Player;

public class MovePlayerCard extends Parent_Card {
    private int steps;
    private String message;

    public MovePlayerCard(int steps, String message) {
        this.steps = steps;
        this.message = message;
    }

    @Override
    public void hit(Player player) {
        player.showchancecard(message);

        int oldPos = player.getPos();
        player.movePlayer(steps);
        checkIfPassedStart(oldPos, player);

    }

    public void checkIfPassedStart(int oldPos, Player player) {
        int newPos = player.getPos();
        if (newPos < oldPos) {
            player.updatePlayerBalance(4000);
        }
    }
}
