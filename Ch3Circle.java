import java.util.Random;
import java.util.Scanner;

public class Ch3Circle {
    public static void main(String[] args) {
        double radius, area, circumference;

        Scanner input = new Scanner(System.in);
        Random rand = new Random();

        radius = rand.nextDouble(30);

        area = Math.PI * radius * radius;
        circumference = 2 * Math.PI * radius;

        System.out.println("Given radius: " + radius);
        System.out.println("The area of the circle is " + area);
        System.out.println("The circumference of the circle is " + circumference);
    }
}
