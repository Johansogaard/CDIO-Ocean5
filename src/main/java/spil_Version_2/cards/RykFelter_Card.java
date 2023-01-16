package spil_Version_2.cards;

import spil_Version_2.Game_Controller;
import spil_Version_2.LandOnField;
import spil_Version_2.Player;
import spil_Version_2.UserIO;

public class RykFelter_Card extends Parent_Card {


    private int spacesToMove;

    public RykFelter_Card(int spacesToMove) {
        this.spacesToMove = spacesToMove;
        this.message = "Ryk felter";
    }

    public void setSpacesToMove(int spacesToMove) {
        this.spacesToMove = spacesToMove;
    }

    @Override
    public void hit(Player player, UserIO userIO) {
        if (spacesToMove < 0) {
            message = "Ryk " + Math.abs(spacesToMove) + " felter tilbage";
            player.showchancecard(message);
        } else {
            message = "Ryk " + spacesToMove + " felter frem";
            player.showchancecard(message);
        }
        player.checkIfPassedStart(player.getPos() + spacesToMove);
        player.movePlayer((player.getPos() + spacesToMove) % 40);
        LandOnField landOnField = new LandOnField();
        landOnField.hitField(player, Game_Controller.getFields(), userIO);
    }

}







