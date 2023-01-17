package spil_Version_2.cards;

import spil_Version_2.Game_Controller;
import spil_Version_2.LandOnField;
import spil_Version_2.Player;
import spil_Version_2.UserIO;

//moveplayercard er et chancekort som flytter spilleren til ny position
public class MovePlayerCard extends Parent_Card {
    private final int steps;

    //constructor
    public MovePlayerCard(int steps, String message) {
        this.steps = steps;
        this.message = message;


    }
//overskriver hit metoden i parent_card
    @Override
    public void hit(Player player, UserIO userIO) {
        player.showchancecard(message);

        //tidligere position
        int oldPos = player.getPos();
        // flyt spiller
        player.movePlayer(steps);
        //tjek om spiller passerer start
        checkIfPassedStart(oldPos, player);
        //bruger landonfield, så han lander på det nye felt
        LandOnField landOnField = new LandOnField();
        landOnField.hitField(player, Game_Controller.getFields(), userIO);
    }

    public void checkIfPassedStart(int oldPos, Player player) {
        int newPos = player.getPos();
        if ((newPos < oldPos && newPos != 30) || newPos == 10) {
            player.updatePlayerBalance(4000);
        }
    }
}
