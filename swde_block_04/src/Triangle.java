import org.junit.jupiter.api.BeforeEach;

public class Triangle {
    int a, b, c;
    public Triangle(int a, int b, int c) {
        this.a = a;
        this.b = b;
        this.c = c;
    }
    public static Triangle createInstance(int a, int b, int c) {
        // Überprüfen, ob die Seitenlängen gültige Werte haben
        if (a <= 0 || b <= 0 || c <= 0) {
            throw new IllegalArgumentException("Negative oder Null-Werte für Seitenlängen sind nicht erlaubt.");
        }

        // Überprüfen, ob die Dreiecksungleichung erfüllt ist
        if (!(a + b > c && b + c > a && a + c > b)) {
            throw new IllegalArgumentException("Die Seitenlängen ergeben kein gültiges Dreieck.");
        }

        return new Triangle(a, b, c);
    }

    public double calculateArea() {
        double s = (a + b + c)/2;
        double flaeche = Math.sqrt(s * (s - a) * (s - b) * (s - c));
        return flaeche;
    }
}
