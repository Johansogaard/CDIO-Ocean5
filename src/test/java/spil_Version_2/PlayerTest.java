package spil_Version_2;

import gui_fields.GUI_Field;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PlayerTest {

    @Test
    void getKonto() {
        List<Integer> choices = List.of(0, 2);
        UserIO io = new UserIO() {
            @Override
            public int getUserButtonPressed(String s, String... keys) {
                return choices.remove(0);
            }
        }
        Player player = new Player("he", 1, io, 0);
        player.spil(new GUI_Field[]{});
    }

    @Test
    void getName() {
    }

    @Test
    void checkIfPassedStart() {
    }

    @Test
    void updatePlayerBalance() {
    }
}