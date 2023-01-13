package spil_Version_2.cards;
import gui_fields.GUI_Field;
import spil_Version_2.Player;

public class MovePlayer extends Parent_Card {
    private GUI_Field[] spaces;

    public MovePlayer(GUI_Field[] spaces) {
        this.spaces = spaces;
    }

    public int moveToNearestField(int playerPosition, String type) {
        int nearestField = -1; // variable to store the index of the nearest field
        int nearestFieldDistance = Integer.MAX_VALUE; // variable to store the distance to the nearest field

        // Loop through all the spaces on the board
        for (int i = 0; i < spaces.length; i++) {
            if (spil_Version_2.Board_Creator.getFieldData().get(i)[2].equals(type)) {
                int distance = Math.abs(playerPosition - i); // calculate the distance from the player's current position to the field
                if (distance < nearestFieldDistance) { // check if this is the nearest field so far
                    nearestField = i; // update the nearest field and distance
                    nearestFieldDistance = distance;
                }
            }
        }

        return nearestField;
    }


    @Override
    public void hit(Player player) {
        int nearestField = moveToNearestField(player.getPos(), "fieldType");
        player.setPos(nearestField);
    }

}
