package spil;

import java.util.Random;

public class Terninger {

    public Terninger()
    {

    }
    public static int slaEnTerning()
    {
        Random rand = new Random();
        return rand.nextInt(1,7);


    }


}
