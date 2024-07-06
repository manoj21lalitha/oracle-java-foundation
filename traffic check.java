package gui.java;
import java.util.Scanner;

public class TrafficLight {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Traffic Light Simulation");
        System.out.println("Enter a traffic light color (red, yellow, green): ");
        String userInput = scanner.nextLine().trim().toLowerCase();

        switch (userInput) {
            case "red":
                System.out.println("STOP! The light is red.");
                break;
            case "yellow":
                System.out.println("CAUTION! The light is yellow.");
                break;
            case "green":
                System.out.println("GO! The light is green.");
                break;
            default:
                System.out.println("Invalid input. Please enter 'red', 'yellow', or 'green'.");
                break;
        }
        scanner.close();
    }
}