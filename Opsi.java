import java.util.Scanner;

public class Opsi {
    private int getSelection(){
        int selection;
        System.out.println("selection: enter the shape number\n"+
                "  1 - Ellipse \n"+
                "  2 - Rectangle \n"+
                "  3 - Rounded rectangle \n");
        Scanner scan = new Scanner(System.in);
        selection = scan.nextInt();
        while (selection < 1 || selection > 3){
            System.out.println(
                "An invalid age was entered. Please try again."
                );
                System.out.println("Selection: Enter the shape number\n"+
                "  1 - Ellipse \n"+
                "  2 - Rectangle \n"+
                "  3 - Rounded rectangle \n");
                selection = scan.nextInt();
            }
            return selection;
        }
        public static void main(String[] args) {
            
        }
}