package spil;

import org.junit.jupiter.api.Test;


import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class HelperTest {

    @Test
    void getElement2dArray()  {

        ArrayList<String> Title = new ArrayList<>();
        Title.add("Roskildevej");
        Title.add("Valby Langgade");
        Title.add("Allégade");

        ArrayList<ArrayList<String>> Firstelement2darray = new ArrayList<>();

        ArrayList<String> Roskildevej = new ArrayList<>();

        Roskildevej.add("Roskildevej");

        Roskildevej.add("1200");

        Roskildevej.add("0");


        ArrayList<String> Valby_Langgade = new ArrayList<>();

        Valby_Langgade.add("Valby Langgade");

        Valby_Langgade.add("2000");

        Valby_Langgade.add("1");


        ArrayList<String> Allégade = new ArrayList<>();

        Allégade.add("Allégade");
        Allégade.add("2800");
        Allégade.add("2");

        Firstelement2darray.add(Roskildevej);
        Firstelement2darray.add(Valby_Langgade);
        Firstelement2darray.add(Allégade);

        assertIterableEquals(Helper.getElement2dArray(Firstelement2darray, 0), Title);
    }
}