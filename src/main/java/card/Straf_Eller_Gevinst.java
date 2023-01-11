package card;
import spil_Version_2.Player;

public class Straf_Eller_Gevinst extends Parent_Card {
    private int amount;
    private boolean add;
    private String message;

    public Straf_Eller_Gevinst(int amount, boolean add, String message) {
        this.amount = amount;
        this.add = add;
        this.message = message;
    }

    @Override
    public void hit(Player player) {
        player.showchancecard(message);
        if (add) {
            player.updatePlayerBalance(amount);
        } else {
            player.updatePlayerBalance(-amount);
        }
    }
}


