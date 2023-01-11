package spil_Version_2;

import spil_Version_2.Terninger;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class TerningerTest {

    @org.junit.jupiter.api.Test
    void terningMellem0og7() {
        Terninger terninger = new Terninger();
        assertTrue(terninger.slaEnTerning()<7&&0<terninger.slaEnTerning());
    }
    @org.junit.jupiter.api.Test
    void RigtigMængdeTerning() {
        int i = 0;
        Terninger terninger = new Terninger();
        ArrayList<Integer> numbers = new ArrayList<>();
        int one,two,three,four,five,six;
        one=two=three=four=five=six=0;
            while (i<600000){
                switch (terninger.slaEnTerning()){
                    case 1: {
                        one+=1;
                    }
                    break;
                    case 2: {
                        two+=1;
                    }
                    break;
                    case 3: {
                        three+=1;
                    }
                    break;
                    case 4: {
                        four+=1;
                    }
                    break;
                    case 5: {
                        five+=1;
                    }
                    break;
                    case 6: {
                        six+=1;
                    }
                    break;


                }
                i+=1;
            }
        assertTrue(six>95000&&six<105000&&five>95000&&five<105000&&four>95000&&four<105000
                &&three>95000&&three<105000&&two>95000&&two<105000&&one>95000&&one<105000);
    }
}