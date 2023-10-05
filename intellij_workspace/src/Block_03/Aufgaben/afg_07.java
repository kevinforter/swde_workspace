package Block_03.Aufgaben;
import java.util.Arrays;
import java.util.Scanner;
public class afg_07 {

    public static void main(String[] args) {
        /* Variablen */
        int arrLaenge;
        int min = 1001;
        int sum = 0;
        int odd = 0;

        /* Objekte */
        Scanner sc = new Scanner(System.in);

        /* Array erstellen */
        System.out.print("Bitte geben sie die Länge des Array an: ");
        arrLaenge = sc.nextInt();
        int[] arr = new int[arrLaenge];
        arrLaenge = 0;

        /* Initialisierung Array */
        for(int i = 0; i < arr.length; i++) {
            arr[i] = 1 + (int)(Math.random() * 1000);
            sum += arr[i];
            if (min > arr[i]) {
                    min = arr[i];
            }
            /* länge für neues Array */
            if (arr[i]%2 == 0) {
                arrLaenge++;
            }
            /* letzte ungerade Zahl */
            if (arr[i]%2 != 0) {
                odd = arr[i];
            }
        }

        /* Zweites Array */
        int[] arrEven = new int[arrLaenge];
        for(int i = 0, y = 0; i < arr.length; i++) {
            if (arr[i]%2 == 0) {
                arrEven[y] = arr[i];
                y++;
            }
        }

        /* Ausgabe Array */
        System.out.println(Arrays.toString(arr));
        for (int element : arrEven) {
            System.out.print(element + " ");
        }
        System.out.println("\n\nDie kleinste Zahl ist: " + "\t\t\t\t" + min);
        System.out.println("Die Summer aller Zahlen lautet: " + "\t" + sum);
        System.out.println("Die letzte ungerade Nummer lautet:" + "\t" + odd);

        System.out.print("Alle Primzahlen im Array: " + "\t\t\t");
        for (int element : arr) {
            if (element % 3 != 0 && element % 2 != 0 && element % 5 != 0 && element % 7 != 0 && element % 9 != 0) {
                System.out.print(element + " ");
            }
        }
    }
}
