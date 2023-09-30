package Block_02.Aufgaben;

public class afg_03 {
    public static void main(String[] args) {
        int x = 10, y = 20, z = 30;
        System.out.println("y: " + y++);
        System.out.println("z: " + --z);

        int p = x-- + y-- + --z;
        System.out.println("p: " + p);

        x = 10;
        y = 133;
        System.out.println(x + "%" + y + " = " + x % y);
        System.out.println(y + "%" + x + " = " + y % x);

        int a = 3, b = 8, c = 12, res = 4;
        res += a * b;
        System.out.println("res: " + res);
    }
}
