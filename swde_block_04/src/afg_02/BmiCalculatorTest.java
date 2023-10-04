package afg_02;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BmiCalculatorTest {

    @Test
    void testBmiCalculator_shouldWork_01() {
        double bmi = BmiCalculator.getBmi(80, 1.75);
        assertEquals(26.12, bmi, 0.01);
    }

    @Test
    void testBmiCalculator_shouldWork_02() {
        double bmi = BmiCalculator.getBmi(100000, 1.95);
        assertEquals(26298.49, bmi,0.01);
    }

    @Test
    void testBmiCalculator_shouldThrowException_GewichtIsNegativ() {
        assertThrows(IllegalArgumentException.class, ()
                -> BmiCalculator.getBmi(-80, 1.75));
    }

    @Test
    void testBmiCalculator_shouldThrowException_GroesseIsNegativ() {
        assertThrows(IllegalArgumentException.class, ()
                -> BmiCalculator.getBmi(80, -1.85));
    }

    @Test
    void testBmiCalculator_shouldThrowException_GewichtUndGroesseIsNegativ() {
        assertThrows(IllegalArgumentException.class, ()
                -> BmiCalculator.getBmi(-90, -1.65));
    }
}