package spil_Version_2.cards;
import spil_Version_2.Player;
public abstract class Parent_Card {
    public String getMessage() {
        return message;
    }

    public String message;

    public abstract void hit(Player player);

}
