package Block_03.Aufgaben;
import java.util.Scanner;

public class afg_01 {
    public static void main(String[] args) {

        /* Variablen */
        double startKapital;
        double anzahlJahre;
        double zinsSatzInProzent;
        double endKapital;

        Scanner sc = new Scanner(System.in);

        /* Eingabe" */
        System.out.print("Startkapital eingeben: ");
        startKapital = sc.nextDouble();
        System.out.print("Anzahl Jahre eingeben: ");
        anzahlJahre = sc.nextDouble();

        /* Endkapital berechnen */
        if (startKapital < 10000) {
            zinsSatzInProzent = 0.75;
        } else if (startKapital >= 10000 && startKapital <= 100000) {
            zinsSatzInProzent = 0.5;
        } else {
            zinsSatzInProzent = 0.25;
        }

        endKapital =
                startKapital * Math.pow((1 + zinsSatzInProzent / 100), anzahlJahre);

        /* Ausgabe */
        System.out.println("\n Endkapital: " + endKapital);
    }
}
