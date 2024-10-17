public class Bicycle {
    private String brand;
    
    public Bicycle(){
        this.brand = "{Unknown}";
    }
    
    public String getBrand() {
        return brand;
    }

    public void setBrand(String newBrand) {
        this.brand = newBrand;
    }

}