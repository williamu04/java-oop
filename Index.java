import java.util.Scanner;

public class Index {
    public int getCount(String str, char c){
        int count = 0;
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) == c) {
                count++;
            }
        }
        return count;
    }

    public static void main(String args[]){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter a string:");
        String str = scanner.nextLine();
        System.out.println("Enter a character:");
        char c = scanner.next().charAt(0);

        int count = new Index().getCount(str, c);
        if (count != -1) {
            System.out.println("Character" + c + "in string is: " + count);
        } else {
            System.out.println("Character not found in string.");
        }
    }
}