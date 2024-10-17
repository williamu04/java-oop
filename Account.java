public class Account {
    private double balance;
    private String ownerName;

    private static final double FEE = 0.5;

    public Account(String ownerName, double balance) {
        this.ownerName = ownerName;
        this.balance = balance;
    }

    public double add(double amount) {
        return balance += (amount - FEE);
    }

    public double deduct(double amount){
        if (amount > balance) {
            System.out.println("Insufficient balance");
            return balance;
        } else {
            return balance -= (amount + FEE);
        }
    }

    public double getCurrentBalance(){
        return balance;
    }

    public  String getOwnerName(){
        return ownerName;
    }

    public double setInitialBalance(){
        return this.balance = 0;
    }

    public String setOwnerName(){
        return this.ownerName = "{None}";
    }
    
    public static void main(String[] args){
        Account acct;
        acct = new Account("John", 1000);
        acct.add(50);
        
        System.out.println(acct.getOwnerName());
        System.out.println(acct.getCurrentBalance());
    }

}
