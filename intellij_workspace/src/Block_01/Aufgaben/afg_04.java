package Block_01.Aufgaben;
import java.util.Scanner;
public class afg_04 {
    public static void main(String[] args) {
        // Variablen
        double guthaben;
        double zinsSatz = 1.5;
        double guthabenMitZinsen;
        // Eingabe
        Scanner sc = new Scanner(System.in);
        System.out.print("Geben Sie ein Guthaben ein: ");
        guthaben = sc.nextDouble();
        // Verarbeitung
        guthabenMitZinsen = guthaben * (1 + zinsSatz / 100);
        // Ausgabe
        System.out.println("Guthaben nach einem Jahr: " + guthabenMitZinsen);
    }
}
