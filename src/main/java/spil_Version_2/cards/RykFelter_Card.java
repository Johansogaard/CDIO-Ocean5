package spil_Version_2.cards;

import spil_Version_2.Player;

public class RykFelter_Card extends Parent_Card {

    private int spacesToMove;

    public RykFelter_Card(int spacesToMove) {
        this.spacesToMove = spacesToMove;
    }

    @Override
    public void hit(Player player) {
        if (spacesToMove < 0) {
            // hvis negativ; anden tekst
            player.showchancecard("Ryk " + Math.abs(spacesToMove) + " felter tilbage");
        } else {
            player.showchancecard("Ryk " + spacesToMove + " felter frem");
        }
        //er spilleren passeret start?
        player.checkIfPassedStart(player.getPos() + spacesToMove);

        //evt action til spiller
        player.movePlayer((player.getPos() + spacesToMove) % 40);
        player.hitField();
    }

}


