package Block_03.Aufgaben;
import java.util.Scanner;

public class afg_02 {
    public static void main(String[] args) {

        /* Variablen */
        int uno, due, tre;
        String order;

        /* Objekte */
        Scanner sc = new Scanner(System.in);

        /* Eingabe */
        System.out.println("Bitte geben 3 ganze Zahlen ein (+/-):");
        System.out.print("Zahl 1: ");
        uno = sc.nextInt();
        System.out.print("Zahl 2: ");
        due = sc.nextInt();
        System.out.print("Zahl 3: ");
        tre = sc.nextInt();

        /* Sortierung */
        order = uno > due ?
                (due > tre ? tre + ", " + due + ", " + uno : uno > tre
                        ? due + ", " + tre + ", " + uno : due + ", " + uno + ", " + tre) :
                (uno > tre ? tre + ", " + uno + ", " + due : tre > due
                        ? uno + ", " + due + ", " + tre : uno + ", " + tre + ", " + due) ;

        /* Ausgabe */
        System.out.println("\n" + order);
    }
}
