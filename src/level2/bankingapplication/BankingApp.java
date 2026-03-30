package level2.bankingapplication;

import java.util.Scanner;

public class BankingApp {
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {

        BankAccount account = new BankAccount();
        boolean isRunning = true;
        int choice;

        while (isRunning) {

            System.out.println("-------------------------------------");
            System.out.println("\uD835\uDC7A\uD835\uDC84\uD835\uDC9A\uD835\uDC8D\uD835\uDC8D\uD835\uDC82 \uD835\uDC69\uD835\uDC82\uD835\uDC8F\uD835\uDC8C\uD835\uDC8A\uD835\uDC8F\uD835\uDC88 \uD835\uDC7A\uD835\uDC9A\uD835\uDC94\uD835\uDC95\uD835\uDC86\uD835\uDC8E");
            System.out.println("-------------------------------------");
            System.out.println("1. Show Balance");
            System.out.println("2. Deposit");
            System.out.println("3. Withdraw");
            System.out.println("4. Exit");
            System.out.println("-------------------------------------");

            System.out.print("Enter your choice (1-4): ");
            choice = scanner.nextInt();

            switch (choice) {
                case 1 -> showBalance(account);
                case 2 -> deposit(account);
                case 3 -> withdraw(account);
                case 4 -> isRunning = false;
                default -> System.out.println("INVALID CHOICE");
            }
        }
//Personalized font for printing out thank you statement
        System.out.println("------------------------------------------");
        System.out.println("\uD835\uDC7B\uD835\uDC89\uD835\uDC82\uD835\uDC8F\uD835\uDC8C \uD835\uDC9A\uD835\uDC90\uD835\uDC96 \uD835\uDC87\uD835\uDC90\uD835\uDC93 \uD835\uDC96\uD835\uDC94\uD835\uDC8A\uD835\uDC8F\uD835\uDC88 \uD835\uDC7A\uD835\uDC84\uD835\uDC9A\uD835\uDC8D\uD835\uDC8D\uD835\uDC82 \uD835\uDC69\uD835\uDC82\uD835\uDC8F\uD835\uDC8C\uD835\uDC8A\uD835\uDC8F\uD835\uDC88 \uD835\uDC7A\uD835\uDC9A\uD835\uDC94\uD835\uDC95\uD835\uDC86\uD835\uDC8E");
        System.out.println("------------------------------------------");
        scanner.close();
    }

    // Show balance using BankAccount
    static void showBalance(BankAccount account) {
        System.out.println("-------------------------------------");
        System.out.printf("Balance: $%.2f\n", account.getBalance());
    }

    // Deposit using BankAccount
    static void deposit(BankAccount account) {
        System.out.print("Enter amount to be deposited: ");
        double amount = scanner.nextDouble();
        account.deposit(amount);
    }

    // Withdraw using BankAccount
    static void withdraw(BankAccount account) {
        System.out.print("Enter amount to withdraw: ");
        double amount = scanner.nextDouble();
        account.withdraw(amount);
    }
}