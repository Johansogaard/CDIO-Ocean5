package spil_Version_2.cards;

import spil_Version_2.Player;
import spil_Version_2.UserIO;

//chance kort som enten giver spilleren penge, eller tager penge fra spilleren
public class Straf_Eller_Gevinst extends Parent_Card {
    private final int amount;
    private final boolean add;

//constructor
    public Straf_Eller_Gevinst(int amount, boolean add, String message) {
        this.amount = amount;
        this.add = add;
        this.message = message;
    }

    //overskriver hit metoden i parent_card
    @Override
    public void hit(Player player, UserIO userIO) {
        player.showchancecard(message);
        //hvis add parameteren er true, så tilføjer den penge til spillerens konto
        if (add) {
            player.updatePlayerBalance(amount);
            //hvis add er false, så trækker den penge fra spillerens konto
        } else {
            player.updatePlayerBalance(-amount);
        }
    }
}


