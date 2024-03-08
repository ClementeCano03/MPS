package bank;

public class BankAccount {
    private int balance = 0;
    public BankAccount(int startingBalance) {
        this.balance = startingBalance;
    }
    public boolean withdraw(int amount) {
        if(amount <= 0 || amount > this.balance){
            throw new IllegalArgumentException("Amount cannot be negative or bigger than balance");
        }
        balance -= amount;
        return true;
    }
    public int deposit(int amount) {
        if (amount <0)
            throw new IllegalArgumentException("Amount cannot be negative");
        balance += amount;
        return balance;
    }
    public int getBalance() {
        return balance;
    }

    // Calculate the payment per month for a loan
    public double payment(double total_amount, double interest, int npayments){
        if(total_amount <= 0 || interest < 0 || npayments <= 0){
            throw new IllegalArgumentException("Cannot be negative parameter");
        }
        return total_amount*(interest*Math.pow((1+interest), npayments)/(Math.pow((1+interest), npayments)-1));
    }

    // Calculate the pending amount for a loan in a month
    public double pending (double amount, double inte, int npayments, int month){
        double res;
        if(amount<=0 || inte<=0 || npayments<=0 || month<0){
            throw new IllegalArgumentException("Cannot be negative parameter");
        }
        if(month==0){
            res=amount;
        }else{
            double ant=pending(amount, inte, npayments, month-1);
            res = ant - (payment(amount,inte,npayments) - inte*ant);
        }
        return res;
    }
}
