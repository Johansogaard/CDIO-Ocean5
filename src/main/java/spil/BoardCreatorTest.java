package spil;

import gui_GameFields.GUI_Parentfield;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.*;

class BoardCreatorTest  {

    @Test
    public void getColorArray() throws FileNotFoundException{
        BoardCreator b = new BoardCreator();
        GUI_Parentfield[] fields = b.istantiererFelter();
        ArrayList<String> testorange = new ArrayList<>();
        testorange.add("Roskildevej");
        testorange.add("Valby Langgade");
        testorange.add("Allégade");

    assertIterableEquals(testorange, BoardCreator.getColorArray("orange"));
    }
}