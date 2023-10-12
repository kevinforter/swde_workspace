package ch.hslu.informatik.swde.finanzkalkulator;

/**
 * Stellt eine einfache Finanzkalkulator-Implementierung zur Verfügung.
 * 
 * @author Jordan Sucur
 * @version 1.0
 */
public class Finanzkalkulator{

	public static double getEndkapital(double startKapital, double zinssatz, double anzahlJahre) {
		if (startKapital <= 0 || zinssatz <= 0 || anzahlJahre <= 0) {
			throw new IllegalArgumentException("Negative oder Null-Werte sind nicht erlaubt.");
		} else {
			double q = 1 + zinssatz/100;
			return startKapital * Math.pow(q, anzahlJahre);
		}
	}

	public static double getBarwert(double endKapital, double zinssatz, double anzahlJahre) {
		if (endKapital <= 0 || zinssatz <= 0 || anzahlJahre <= 0) {
			throw new IllegalArgumentException("Negative oder Null-Werte sind nicht erlaubt.");
		} else {
			double q = 1 + zinssatz / 100;
			return endKapital * Math.pow(q, ((-1) * anzahlJahre));
		}
	}

	public static double getAnuitaet(double kapital, double zinssatz, double anzahlJahre) {
		if (kapital <= 0 || zinssatz <= 0 || anzahlJahre <= 0) {
			throw new IllegalArgumentException("Negative oder Null-Werte sind nicht erlaubt.");
		} else {
			double q = 1 + zinssatz / 100;
			return kapital * (Math.pow(q, anzahlJahre) * (q - 1))
					/ (Math.pow(q, anzahlJahre) - 1);
		}
	}
}
