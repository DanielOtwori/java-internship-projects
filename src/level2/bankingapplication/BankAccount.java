package level2.bankingapplication;

public class BankAccount {

    private double balance=0;

public void deposit(double amount) {
    if (amount <= 0) {
        System.out.println("Amount entered must be more than 0");
    } else {
        balance += amount;
        System.out.println("Your deposit is Successful.");
    }
}
    public void withdraw(double amount){
    if(amount<=0){
        System.out.println("Enter amount that is more then 0");
    } else if (amount>balance) {
        System.out.println("Insufficient Funds! It's not looking good bruv \uD83D\uDE2D");
        //Customized emoji to accompany insufficient balance statement
    }else{
        balance-=amount;
        System.out.println("Withdrawal is Successful");
    }
    }
    public double getBalance(){
    return balance;
    }
}
