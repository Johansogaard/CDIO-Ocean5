package spil_Version_2.cards;

import spil_Version_2.Player;
import spil_Version_2.UserIO;

// de andre chance cards arver fra parent_card
public abstract class Parent_Card {
    public String message;

    //getter af message
    public String getMessage() {
        return message;
    }

    //hit metoden, som overskrives i child classes
    public abstract void hit(Player player, UserIO gui);

}
