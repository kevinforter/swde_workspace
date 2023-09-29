package Block_02.Aufgaben;
import java.util.Scanner;

public class afg_01 {
    public static void main(String[] args) {
        // Variablen
        double maschinenLeistung;
        double stueckzahl;
        double fixKosten;
        double lohnKosten;
        double maschinenKosten;
        double produktionsKosten;
        double aufwand;

        // Eingabe
        Scanner sc = new Scanner(System.in);
        System.out.println("Bitte geben Sie die Stückzahl an: ");
        stueckzahl = sc.nextDouble();
        System.out.println("Bitte geben Sie die Maschinenleistung an (Stück/Stunde): ");
        maschinenLeistung = sc.nextDouble();
        System.out.println("Bitte geben Sie die Fixkosten an (CHF): ");
        fixKosten = sc.nextDouble();
        System.out.println("Bitte geben Sie die Lohnkosten an (CHF/Stunde): ");
        lohnKosten = sc.nextDouble();
        System.out.println("Bitte geben Sie die Maschinenkosten an (CHF/Stunde): ");
        maschinenKosten = sc.nextDouble();

        // Berechnung
        aufwand = stueckzahl / maschinenLeistung;

        produktionsKosten = aufwand * (lohnKosten + maschinenKosten) + fixKosten;
        produktionsKosten = (stueckzahl < 100)
                ? (produktionsKosten * 1.2) : (stueckzahl >= 1000)
                ? (produktionsKosten * 0.95) : produktionsKosten;

        // Ausgabe
        System.out.println("Der Aufwand beträgt: " + aufwand + " Stunden");
        System.out.println("Die Produktionskosten betragen: " + produktionsKosten + " CHF");

    }
}
