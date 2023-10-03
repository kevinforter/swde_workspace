package Block_03.Aufgaben;
import java.util.Arrays;
import java.util.Scanner;
public class afg_07 {

    public static void main(String[] args) {
        /* Variablen */
        int arrLaenge;
        int min = 1001;
        int sum = 0;

        /* Objekte */
        Scanner sc = new Scanner(System.in);

        /* Array erstellen */
        System.out.print("Bitte geben sie die Länge des Array an: ");
        arrLaenge = sc.nextInt();
        int[] arr = new int[arrLaenge];

        /* Initialisierung Array */
        for(int i = 0; i < arr.length; i++) {
            arr[i] = 1 + (int)(Math.random() * 1000);
            sum += arr[i];
            if (min > arr[i]) {
                    min = arr[i];
            }
        }

        /* Ausgabe Array */
        System.out.println(Arrays.toString(arr));
        System.out.println("Die kleinste Zahl ist: " + min);
        System.out.println("Die Summer aller Zahlen lautet: " + sum);
    }
}
