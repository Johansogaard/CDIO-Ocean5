package spil;


import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.*;

class BoardCreatorTest  {

    @Test
    public void getColorArray() throws FileNotFoundException{
        BoardCreator b = new BoardCreator();
        b.istantiererFelter();
        ArrayList<String> testorange = new ArrayList<>();
        testorange.add("Roskildevej");
        testorange.add("Valby Langgade");
        testorange.add("Allégade");
        System.out.println(BoardCreator.getGroupArray("orange"));
    assertIterableEquals(testorange, BoardCreator.getGroupArray("orange"));
    }
}