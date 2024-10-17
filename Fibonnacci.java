import java.util.*;;

public class Fibonnacci {

    public int count(int n){
        if (n == 0){
            return 0;
        } else if (n == 1){
            return 1;
        } else {
            return count(n-1)+ count(n-2);
        }
    }
    
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();

        int count = new Fibonnacci().count(n);
        System.out.println("Bilangan fibo ke " + n + " adalah: " + count);

    }
}
