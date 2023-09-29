package Block_01.Aufgaben;

import java.util.Scanner;

public class afg_05 {
    public static void main(String[] args) {
        // Variablen
        double guthaben;
        double zinsSatz;
        double guthabenMitZinsen;
        double zinsen;
        // Eingabe
        Scanner sc = new Scanner(System.in);
        System.out.println("Geben Sie ein Guthaben ein: ");
        guthaben = sc.nextDouble();
        System.out.println("Geben Sie einen Zinssatz ein: ");
        zinsSatz = sc.nextDouble();
        // Verarbeitung
        guthabenMitZinsen = guthaben * (1 + zinsSatz / 100);
        zinsen = guthaben * zinsSatz / 100;
        // Ausgabe
        System.out.println("Zinsen im ersten Jahr: " + zinsen);
        System.out.println("Guthaben nach einem Jahr: " + guthabenMitZinsen);
    }
}
