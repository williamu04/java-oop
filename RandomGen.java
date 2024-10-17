import java.util.Random;
import java.util.Scanner;

public class RandomGen {
    public static void main(String[] args) {
        int start;
        int count;
        int winning;
        int min, max;

        Random rand = new Random();

        start = rand.nextInt(20);
        System.out.println("Starting number: " + start);
        count = rand.nextInt(50 - start + 1);
        System.out.println("Total number: " + count);


        min = start + 1;
        max = start + count;

        winning = rand.nextInt(max - min + 1) + min;

        System.out.println("The winning number is: " + winning);
    }
}
