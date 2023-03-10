package spil_Version_2.cards;

import spil_Version_2.Player;
import spil_Version_2.UserIO;

public class Matador_Legat extends Parent_Card {
    private final int amount;

//constructor
    public Matador_Legat(int amount, boolean add, String message) {
        this.amount = amount;
        this.message = message;
    }
//overskriver hit metoden fra parent_card
    @Override
    public void hit(Player player, UserIO userIO) {

        //tjekker om spilleren har under 15000
        if (player.getKonto().getBalance() < 15000) {
            player.showchancecard("Tillykke, du har vundet Matador-Legatet; 40000kr!");
            player.updatePlayerBalance(40000);
        } else {
            player.showchancecard("Du har vundet Matador-legatet, men desværre har du for mange moneys, så du får ikke et Legat #velfærdsstaten");
        }
    }
}


