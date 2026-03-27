import java.util.Scanner;

public class CalculatorApp {
    public static void main(String[] args){
        Scanner scanner=new Scanner(System.in);

        double num1;
        double num2;
        char operator;
        double result=0;
        boolean validOperations=true;

        System.out.print("Enter your first desired number: ");
        num1=scanner.nextDouble();
        System.out.print("Enter an operator(+, -, *, /, ^ : ");
        operator=scanner.next().charAt(0);
        System.out.print("Enter your second desired number: ");
        num2=scanner.nextDouble();

        scanner.close();

    }
}
