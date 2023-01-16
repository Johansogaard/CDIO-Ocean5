package spil_Version_2;

import java.util.ArrayList;

public class Helper {
    public static ArrayList<String> getElement2dArray(ArrayList<ArrayList<String>> twoDArrayList, int number) {
        ArrayList<String> Element = new ArrayList<>();
        for (ArrayList<String> innerList : twoDArrayList) {
            Element.add(innerList.get(number));
        }
        return Element;

    }
}
