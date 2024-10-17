import java.util.*;

public class Temperature {
    double fahrenheit = 0;
    double celsius = 0;

    public double setFarenheit(double newFahrenheit) {
        return this.fahrenheit = newFahrenheit;
    }
    public double setCelsius(double newCelsius) {
        return this.celsius = newCelsius;
    }

    public double toFarenheit(double celsius) {
        return (celsius * 9 / 5) + 32;
    }
    public double toCelsius(double fahrenheit) {
        return (fahrenheit - 32) * 5 / 9;
    }

    public static void main(String[] args){
        Temperature temp = new Temperature();

        Scanner sc1 = new Scanner(System.in);
        System.out.println("1 to convert from  Celsius to Fahrenheit\n2 to convert from Fahrenheit to Celsius");
        int pilih = sc1.nextInt();
        switch (pilih) {
            case 1:
            Scanner sc2 = new Scanner(System.in);
            System.out.println("Enter temperature in Celsius: ");
        
            double celsius;
            while(true) {
                try {
                    celsius = sc2.nextDouble();
                    break;
                } catch (InputMismatchException e) {
                    System.out.println("Invalid input. Please enter a number.");
                    sc2.next();
                }
            }
            temp.setCelsius(celsius);
            System.out.println(celsius + " Celsius is equal to " + temp.toFarenheit(celsius) + " Farenheit");
            break;
            
            case 2:
            Scanner sc3 = new Scanner(System.in);
            System.out.println("Enter temperature in Fahrenheit: ");

            double fahrenheit;
            while(true) {
                try {
                    fahrenheit = sc3.nextDouble();
                    break;
                } catch (InputMismatchException e) {
                    System.out.println("Invalid input. Please enter a number.");
                    sc3.next();
                }
            }  

            temp.setFarenheit(fahrenheit);
            System.out.println(fahrenheit + " Fahrenheit is equal to " + temp.toCelsius(fahrenheit) + " Celsius");
            break;
        }
    }
}
