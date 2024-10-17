import java.util.*;

public class Ch5Circle {
    public static final int INVALID_DIMENSION = -1;
    private double radius;

    public Ch5Circle(double r){
        setRadius(r);
    }

    public void setRadius(double r){
        if(r >= 0){
            radius = r;
        } else {
            radius = INVALID_DIMENSION;
        }
    }

    public double getRadius(){
        return radius;
    }

    public void setDiameter(double d){
        if(d >= 0){
            radius = d / 2;
        } else {
            radius = INVALID_DIMENSION;
        }
    }

    public double getDiameter(){
        double diameter = INVALID_DIMENSION;

        if(isRadiusValid()){
            diameter = 2 * radius;
        }

        return diameter;
    }

    public double getCircumference(){
        double result = INVALID_DIMENSION;

        if(isRadiusValid()){
            result = 2 * Math.PI * radius;
        }

        return result;
    }

    public double getArea(){
        double result = INVALID_DIMENSION;

        if(isRadiusValid()){
            result = Math.PI * radius * radius;
        }

        return result;
    }

    private boolean isRadiusValid(){
        return radius != INVALID_DIMENSION;
    }
}

class Ch5Sample {
    public static void main(String args[]){
        double radius, circumference, area;
        Ch5Circle circle;
        
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter radius: ");
        radius = scanner.nextDouble();

        circle = new Ch5Circle(radius);
        circumference = circle.getCircumference();
        area = circle.getArea();
        System.out.println("Input Radius: " + radius);
        System.out.println("Circumference: " + circumference);
        System.out.println("Area: " + area);
    }
}