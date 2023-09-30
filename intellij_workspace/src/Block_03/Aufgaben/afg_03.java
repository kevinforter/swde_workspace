package Block_03.Aufgaben;
import java.util.Scanner;

public class afg_03 {

    public static void main(String[] args) {

        /* Variablen */
        double bmi;
        double koerperGroesse;
        double koerperGewicht;
        String beschreibung;

        /* Objekte */
        Scanner sc = new Scanner(System.in);

        /* Eingabe */
        System.out.print("Bitte geben Sie ihre Körpergrösse (cm) an: ");
        koerperGroesse = sc.nextDouble();
        System.out.print("Bitte geben Sie ihr Körpergewicht (kg) an: ");
        koerperGewicht = sc.nextDouble();

        /* Berechnung */
        bmi = koerperGewicht / Math.pow((koerperGroesse / 100), 2);

        /* Ausgabe */
        if (bmi < 18.5) {
            beschreibung = "(Untergewicht)";
        } else if (bmi >= 18.5 && bmi < 25) {
            beschreibung = "(Normalgewicht)";
        } else if (bmi >= 25 && bmi < 30) {
            beschreibung = "(Übergewicht)";
        } else {
            beschreibung = "(Adipositas)";
        }

        System.out.println("\nIhr BMI: " + Math.round(bmi*100.0)/100 + " " + beschreibung);
    }
}
