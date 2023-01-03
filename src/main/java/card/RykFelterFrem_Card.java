package card;

import spil.Player;

public class RykFelterFrem_Card extends Parent_Card{
    @Override
    public void hit(Player player) {
        player.showchancecard("Ryk 5 felter frem");
        player.checkIfPassedStart(player.getPos()+5);
        player.movePlayer((player.getPos()+5)%24);
        player.hitField();
    }
}
