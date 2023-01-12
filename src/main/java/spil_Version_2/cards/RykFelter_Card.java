package spil_Version_2.cards;

import spil_Version_2.Game_Controller;
import spil_Version_2.LandOnField;
import spil_Version_2.Player;

public class RykFelter_Card extends Parent_Card {

    private int spacesToMove;

    public RykFelter_Card(int spacesToMove) {
        this.spacesToMove = spacesToMove;
    }

    public void setSpacesToMove(int spacesToMove) {
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
        player.movePlayer((player.getPos()+spacesToMove)%40);
        LandOnField landOnField = new LandOnField();
        landOnField.hitField(player, Game_Controller.getFields());
    }

}







