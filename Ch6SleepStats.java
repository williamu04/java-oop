import java.util.*;
import java.text.*;

public class Ch6SleepStats {
    private Scanner scanner;

    public static void main(String[] args) {
        Ch6SleepStats prog = new Ch6SleepStats();
        prog.start();
    }

    public Ch6SleepStats() {
        scanner = new Scanner(System.in);
        scanner.useDelimiter(System.getProperty("line.separator"));
    }

    public void start() {
        double sleepHour, sum = 0;
        int cnt = 0;

        System.out.print("Dorm name: ");
        String dorm = scanner.next();

        while (true) {
            sleepHour = getDouble("Enter sleep hours (0 - to stop):");

            if (sleepHour == 0) {
                break;
            }

            sum += sleepHour;
            cnt++;
        }

        if (cnt == 0) {
            System.out.println("No data entered.");
        } else {
            DecimalFormat df = new DecimalFormat("0.00");
            System.out.println(
                    "Average sleep time for " +
                    dorm + " is \n\n" +
                    df.format(sum / cnt) + " hours.");
        }
    }

    private double getDouble(String message) {
        while (true) {
            try {
                System.out.print(message);
                return scanner.nextDouble();
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a valid number.");
                scanner.next(); // Clear the invalid input
            }
        }
    }
}