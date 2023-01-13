package spil_Version_2.cards;

import spil_Version_2.Player;

public class Matador_Legat extends Parent_Card {
    private int amount;


    public Matador_Legat(int amount, boolean add, String message) {
        this.amount = amount;
        this.message = message;
    }

    @Override
    public void hit(Player player) {

        if (player.getKonto().getBalance() < 15000) {
            player.showchancecard("Tillykke, du har vundet Matador-Legatet; 40000kr!");
            player.updatePlayerBalance(40000);
        } else {
            player.showchancecard("Du har vundet Matador-legatet, men desværre har du for mange moneys, så du får ikke et Legat #velfærdsstaten");
        }
    }
}


