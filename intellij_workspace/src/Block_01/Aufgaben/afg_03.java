package Block_01.Aufgaben;

public class afg_03 {
    public static void main(String[] args) {
        // Variablen
        double guthaben = 1000;
        double zinsSatz = 3;
        double guthabenMitZinsen;
        // Verarbeitung
        guthabenMitZinsen = guthaben * (1 + zinsSatz / 100);
        // Ausgabe
        System.out.println(guthabenMitZinsen);
    }

}
