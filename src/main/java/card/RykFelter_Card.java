package card;

import spil_Version_2.Player;

public class RykFelter_Card extends Parent_Card {

    private int spacesToMove;

    public RykFelter_Card(int spacesToMove) {
        this.spacesToMove = spacesToMove;
    }


    @Override
    public void hit(Player player) {
        if (spacesToMove < 0) {
            spacesToMove = Math.abs(spacesToMove);
        }
        player.showchancecard("Ryk " + spacesToMove + " felter");
        player.checkIfPassedStart(player.getPos() + spacesToMove);
        player.movePlayer((player.getPos() + spacesToMove) % 40);
        player.hitField();
    }


}


