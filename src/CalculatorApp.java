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
        System.out.print("Enter an operator(+, -, *, /, ^ ):  ");

        operator=scanner.next().charAt(0);
        System.out.print("Enter your second desired number: ");
        num2=scanner.nextDouble();

switch(operator){
    case '+'->result=num1 + num2;
    case '-'->result=num1-num2;
    case '*'->result=num1*num2;
    case '/'-> {
        if (num2 == 0) {
            System.out.println("You cannot divide by zero.");
            validOperations = false;
        } else {
            result = num1 / num2;
        }
    }
    case '^'->result=Math.pow(num1,num2);
    default ->{ System.out.println("Invalid operator");
        validOperations=false;
    }
}
if(validOperations) {
    System.out.println(result);
}

        scanner.close();

    }
}
