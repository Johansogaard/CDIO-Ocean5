package spil_Version_2;


import org.junit.jupiter.api.Test;
import spil_Version_2.Board_Creator;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.*;

class BoardCreatorTest  {

    @Test
    public void getColorArray() throws FileNotFoundException{
        Board_Creator b = new Board_Creator();
        b.istantiererFelter();
        ArrayList<String> testorange = new ArrayList<>();
        testorange.add("Roskildevej");
        testorange.add("Valby Langgade");
        testorange.add("Allégade");
        System.out.println(Board_Creator.getGroupArray("orange"));
    assertIterableEquals(testorange, Board_Creator.getGroupArray("orange"));
    }
}