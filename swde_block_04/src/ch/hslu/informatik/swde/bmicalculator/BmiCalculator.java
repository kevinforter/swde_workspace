package ch.hslu.informatik.swde.bmicalculator;

public class BmiCalculator {

    public static double getBmi (double gewicht, double groesse) {
        if (groesse <= 0 || gewicht <= 0) {
            throw new IllegalArgumentException("Negative oder Null-Werte sind nicht erlaubt");
        } else {
            return gewicht / Math.pow(groesse, 2);
        }
    }
}
