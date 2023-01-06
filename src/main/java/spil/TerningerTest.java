package spil;

import static org.junit.jupiter.api.Assertions.*;

class TerningerTest {

    @org.junit.jupiter.api.Test
    void slaEnTerning() {
        Terninger terninger = new Terninger();
        assertTrue(terninger.slaEnTerning()<7&&0<terninger.slaEnTerning());
    }
}