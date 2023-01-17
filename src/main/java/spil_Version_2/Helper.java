package spil_Version_2;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

public class Helper {
    public static ArrayList<String> getElement2dArray(ArrayList<ArrayList<String>> twoDArrayList, int number) {
        ArrayList<String> Element = new ArrayList<>();
        for (ArrayList<String> innerList : twoDArrayList) {
            Element.add(innerList.get(number));
        }
        return Element;

    }
    static int currentIndex;
    public static int getNext(int[] list) {
        if (currentIndex == list.length) {
            currentIndex = 0;
        }
        return list[currentIndex++];
    }
}


