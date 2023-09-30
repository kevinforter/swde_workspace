package Block_03.Aufgaben;
import java.util.Scanner;

public class afg_04 {

    public static void main(String[] args) {

        /* Variablen */
        int obereG;
        int untereG;

        /* Objekte */
        Scanner sc = new Scanner(System.in);

        /* Eingabe */
        System.out.print("Bitte die Obergrenze eingeben: ");
        obereG = sc.nextInt();
        System.out.print("Bitte die Untergrenze eingeben: ");
        untereG = sc.nextInt();

        /* Ausgabe */
        System.out.println("\nAusgabe:");
        for (int i = obereG; i >= untereG; i--) {
            System.out.print(i + " ");
        }
    }
}
