package Block_03.Aufgaben;

public class afg_05 {
    public static void main(String[] args) {

        /* Variablen */
        int a = 4, b = 7, c = 2;
        boolean x = true, y = true;

        /* Selektionen */
        if(a > b || a < b/2 || (a+c) > b) {
            System.out.println("Genf");
        }

        if(a > b || (a-c) > 0 || x != y ) {
            System.out.println("Bern");
        }

        if((a/2)%2 != 0 || (b-c)%2 == 0 || a != b && c != a) {
            System.out.println("Lugano");
        }



    }
}
