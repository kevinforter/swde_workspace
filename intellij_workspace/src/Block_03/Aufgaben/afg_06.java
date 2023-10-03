package Block_03.Aufgaben;
import java.util.Scanner;

public class afg_06 {
    public static void main(String[] args) {

        /* Variablen */
        int pZahl;

        /* Obekte */
        Scanner sc = new Scanner(System.in);

        /* Eingabe */
        System.out.print("\nBitte gib eine Zahl grösser als 1 an: ");
        pZahl = sc.nextInt();

        if (pZahl <= 1) {
            System.out.println("Eingabe nicht korrekt.");
        } else {
            /* Berechnung */
            if (pZahl % 3 == 0 || pZahl % 2 == 0 || pZahl % 5 == 0 || pZahl % 7 == 0 || pZahl % 9 == 0) {
                System.out.println("Die Zahl " + pZahl + " ist keine Primzahl.");
            } else {
                System.out.println("Die Zahl " + pZahl + " ist eine Primzahl.");
            }
        }
    }
}
