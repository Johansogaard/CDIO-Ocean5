package spil_Version_2.cards;

import spil_Version_2.Game_Controller;
import spil_Version_2.LandOnField;
import spil_Version_2.Player;

public class MovePlayerCard extends Parent_Card {
    private int steps;
    private boolean test;


    public MovePlayerCard(int steps, String message, boolean test) {
        this.steps = steps;
        this.message = message;
        this.test = test;
    }

    @Override
    public void hit(Player player) {
        player.showchancecard(message);

        int oldPos = player.getPos();
        player.movePlayer(steps);
        checkIfPassedStart(oldPos, player);
        if (!test) {
            LandOnField landOnField = new LandOnField();
            landOnField.hitField(player, Game_Controller.getFields());
        }
    }

    public void checkIfPassedStart(int oldPos, Player player) {
        int newPos = player.getPos();
        if ((newPos < oldPos && newPos != 30) || newPos == 10) {
            player.updatePlayerBalance(4000);
        }
    }
}

