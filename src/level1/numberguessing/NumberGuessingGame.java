package level1.numberguessing;

import java.util.Random;
import java.util.Scanner;

public class NumberGuessingGame {
    public static void main(String[] args) {


        Random random = new Random();
        Scanner scanner = new Scanner(System.in);

        int guess;
        int attempts=0;
        int min=1;
        int max=100;
        int randomNumber = random.nextInt(min, max+1);

        //First line is customized
        // It basically Prints out Welcome to Danny's Guessing Game
        System.out.println("\uD835\uDC7E\uD835\uDC86\uD835\uDC8D\uD835\uDC84\uD835\uDC90\uD835\uDC8E\uD835\uDC86 \uD835\uDC95\uD835\uDC90 \uD835\uDC6B\uD835\uDC82\uD835\uDC8F\uD835\uDC8F\uD835\uDC9A'\uD835\uDC94 \uD835\uDC6E\uD835\uDC96\uD835\uDC86\uD835\uDC94\uD835\uDC94\uD835\uDC8A\uD835\uDC8F\uD835\uDC88 \uD835\uDC6E\uD835\uDC82\uD835\uDC8E\uD835\uDC86");
        System.out.printf("Guess a number between %d-%d\n ",min, max);

        do {
            System.out.print("Enter a guess: ");
            guess=scanner.nextInt();
            attempts++;

            if(guess<randomNumber){
                System.out.println("The number is TOO LOW! Please Try Again!");
            }
else if(guess>randomNumber){
    System.out.println("The number is TOO HIGH! Please Try Again!");
            }
else{
    //This line has an emoji for congratulating the player after guessing correct
    System.out.println("You did it \uD83C\uDF89\uD83E\uDD73\uD83C\uDF8A The number was " + randomNumber+".");
    System.out.println("Your attempts to guess were "+ attempts+".");
            }
        } while (guess != randomNumber);

//System.out.println("\uD835\uDC6F\uD835\uDC96\uD835\uDC93\uD835\uDC93\uD835\uDC82\uD835\uDC9A! \uD835\uDC80\uD835\uDC90\uD835\uDC96 \uD835\uDC89\uD835\uDC82\uD835\uDC97\uD835\uDC86 \uD835\uDC98\uD835\uDC90\uD835\uDC8F!\uD83C\uDFC6");

        scanner.close();


    }
}
