import java.util.Scanner;

public class TinggiTiang {
    public static void main(String[] args) {
        double d, a, b;
        Scanner sc = new Scanner(System.in);
        System.out.print("Masukkan nilai d: ");
        d = sc.nextDouble();
        System.out.print("Masukkan nilai a: ");
        a = sc.nextDouble();
        System.out.print("Masukkan nilai b: ");
        b = sc.nextDouble();

        double h = d * Math.sin(a) * Math.sin(b) / Math.sqrt(Math.sin(a+b)*Math.sin(a-b));

        System.out.printf("tinggi tiang adalah : %.2f", h);
    }
}
