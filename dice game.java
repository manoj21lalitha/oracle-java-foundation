package gui.java;
import java.util.Random;
import java.util.Scanner;

public class DiceRollSimulation {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();

        System.out.println("Welcome to the Dice Roll Simulation!");

        while (true) {
            System.out.println("\nPress Enter to roll the dice (or type 'exit' to quit):");
            String input = scanner.nextLine().trim().toLowerCase();

            if (input.equals("exit")) {
                System.out.println("Exiting the program. Goodbye!");
                break;
            }

            // Roll the first die (1 to 6)
            int die1 = random.nextInt(6) + 1;

            // Roll the second die (1 to 6)
            int die2 = random.nextInt(6) + 1;

            // Calculate the sum of the two dice
            int sum = die1 + die2;

            // Display the results
            System.out.println("First die: " + die1);
            System.out.println("Second die: " + die2);
            System.out.println("Sum of the dice: " + sum);
        }

        scanner.close();
    }
}