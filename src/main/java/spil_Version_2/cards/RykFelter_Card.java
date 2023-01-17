package spil_Version_2.cards;

import spil_Version_2.Game_Controller;
import spil_Version_2.LandOnField;
import spil_Version_2.Player;
import spil_Version_2.UserIO;

//kort som rykker spilleren x antal felter frem/tilbage
public class RykFelter_Card extends Parent_Card {

//antal felter at rykke
    private int spacesToMove;

    //constructor
    public RykFelter_Card(int spacesToMove) {
        this.spacesToMove = spacesToMove;
        this.message = "Ryk felter";
    }

    //metode der sætter antal felter at rykke
    public void setSpacesToMove(int spacesToMove) {
        this.spacesToMove = spacesToMove;
    }

    //overskriver hit metode i parent_card
    @Override
    public void hit(Player player, UserIO userIO) {
        if (spacesToMove < 0) {
            message = "Ryk " + Math.abs(spacesToMove) + " felter tilbage";
            player.showchancecard(message);
        } else {
            message = "Ryk " + spacesToMove + " felter frem";
            player.showchancecard(message);
        }
        //tjekker om spilleren er har passeret start og opdaterer spillerens balance
        player.checkIfPassedStart(player.getPos() + spacesToMove);
        //flytter spilleren
        player.movePlayer((player.getPos() + spacesToMove) % 40);
        //lander på feltet
        LandOnField landOnField = new LandOnField();
        landOnField.hitField(player, Game_Controller.getFields(), userIO);
    }

}







