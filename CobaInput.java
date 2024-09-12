import java.util.Scanner;

public class CobaInput {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int age;
        System.out.print("Enter your age: ");

        age = sc.nextInt();

        Scanner sc1 = new Scanner(System.in);
        int height;
        float gpa;
        System.out.print("Enter your height: ");
        height = sc1.nextInt();
        System.out.print("Enter your gpa: ");
        gpa = sc1.nextFloat();
    }
}
