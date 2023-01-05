package card;

import spil.Player;

public class RykFelter_Card extends Parent_Card {

    private int spacesToMove;

    public RykFelter_Card(int spacesToMove) {
        this.spacesToMove = spacesToMove;
    }

    @Override
    public void hit(Player player) {
        if (spacesToMove < 0) {
            player.showchancecard("Ryk " + Math.abs(spacesToMove) + " felter tilbage");
        } else {
            player.showchancecard("Ryk " + spacesToMove + " felter frem");
        }
        player.checkIfPassedStart(player.getPos() + spacesToMove);
        player.movePlayer((player.getPos() + spacesToMove) % 24);
        player.hitField();
    }

}

