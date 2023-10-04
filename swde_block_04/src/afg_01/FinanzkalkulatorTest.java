package afg_01;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class FinanzkalkulatorTest {

    @Test
    void testNewFinanzkalkulator_shouldThrowException_StartkapitalIsNegativ() {
        assertThrows(IllegalArgumentException.class, ()
                -> Finanzkalkulator.getEndkapital(-10000, 3, 10));
        assertThrows(IllegalArgumentException.class, ()
                -> Finanzkalkulator.getBarwert(-10000, 3, 10));
        assertThrows(IllegalArgumentException.class, ()
                -> Finanzkalkulator.getAnuitaet(-10000, 3, 10));
    }
    @Test
    void testNewFinanzkalkulator_shouldThrowException_ZinssatzlIsNegativ() {
        assertThrows(IllegalArgumentException.class, ()
                -> Finanzkalkulator.getEndkapital(10000, -3, 10));
        assertThrows(IllegalArgumentException.class, ()
                -> Finanzkalkulator.getBarwert(10000, -3, 10));
        assertThrows(IllegalArgumentException.class, ()
                -> Finanzkalkulator.getAnuitaet(10000, -3, 10));
    }
    @Test
    void testNewFinanzkalkulator_shouldThrowException_AnzahlJahreIsNegativ() {
        assertThrows(IllegalArgumentException.class, ()
                -> Finanzkalkulator.getEndkapital(10000, 3, -10));
        assertThrows(IllegalArgumentException.class, ()
                -> Finanzkalkulator.getBarwert(10000, 3, -10));
        assertThrows(IllegalArgumentException.class, ()
                -> Finanzkalkulator.getAnuitaet(10000, 3, -10));
    }

    @Test
    void testGetEndkapital_shouldWork() {
        double endkapital = Finanzkalkulator.getEndkapital(10000, 3, 10);
        assertEquals(13439.163793441223, endkapital);
    }
    @Test
    void testGetBarwert_shouldWork() {
        double barwert = Finanzkalkulator.getBarwert(13439.163793441223, 3, 10);
        assertEquals(10000, barwert);
    }
    @Test
    void testGetAnuitaet_shouldWork() {
        double anuitaet = Finanzkalkulator.getAnuitaet(10000, 3, 10);
        assertEquals(1172.3050660515962, anuitaet);
    }
}
