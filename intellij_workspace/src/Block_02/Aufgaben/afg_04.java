package Block_02.Aufgaben;

public class afg_04 {
    public static void main(String[] args) {
        // Variablen
        boolean a = false, b = true, c = true, d = false, v = false;
        int p = 9, q = 10;

        // Ausdruck A
        d = !b;
        System.out.println(d);

        // Ausdruck B
        v = !a || b && d || !c && !b;
        System.out.println(v);

        // Ausdruck C
        v = a && b && (p++ < q);
        System.out.println(v);

        // Ausdruck D
        v = a || b && (p++ < q);
        System.out.println(v);

    }
}
