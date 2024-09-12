import java.util.Random;

public class contoh1 {
    public static void main(String[] args) {
        double radius, area, circumference;

        Random rand = new Random();
        radius = rand.nextInt(1, 50);

        area = Math.PI * radius * radius;
        circumference = 2 * Math.PI * radius;

        System.out.println("Given radius: " + radius);
        System.out.println("The area of the circle is " + area);
        System.out.println("The circumference of the circle is " + circumference);

    }
}