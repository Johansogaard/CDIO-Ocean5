package spil_Version_2.cards;
import spil_Version_2.*;

public class RykTilStart_Card extends Parent_Card {
    @Override
    public void hit(Player player) {
        player.showchancecard("Ryk til start og modtag 2 M");
        player.movePlayer(0);
        player.updatePlayerBalance(2);

    }
}
