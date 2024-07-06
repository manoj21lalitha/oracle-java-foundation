package gui.java;
import java.util.Scanner;

public class ArcadeSimulation {

    public static class Card {
        private String cardId;
        private double balance;

        public Card(String cardId, double balance) {
            this.cardId = cardId;
            this.balance = balance;
        }

        public String getCardId() {
            return cardId;
        }

        public double getBalance() {
            return balance;
        }

        public void setBalance(double balance) {
            this.balance = balance;
        }

        public void addBalance(double amount) {
            if (amount > 0) {
                balance += amount;
            }
        }

        public boolean deductBalance(double amount) {
            if (amount > 0 && amount <= balance) {
                balance -= amount;
                return true;
            } else {
                return false;
            }
        }
    }

    public static class GameMachine {
        private String machineId;
        private String gameName;
        private double gameCost;

        public GameMachine(String machineId, String gameName, double gameCost) {
            this.machineId = machineId;
            this.gameName = gameName;
            this.gameCost = gameCost;
        }

        public String getMachineId() {
            return machineId;
        }

        public String getGameName() {
            return gameName;
        }

        public double getGameCost() {
            return gameCost;
        }

        public boolean swipeCard(Card card) {
            if (card.getBalance() >= gameCost) {
                card.deductBalance(gameCost);
                return true;
            } else {
                System.out.println("Insufficient balance to play " + gameName);
                return false;
            }
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Create a card for a player
        System.out.print("Enter card ID: ");
        String cardId = scanner.nextLine().trim();
        System.out.print("Enter initial balance: ");
        double initialBalance = scanner.nextDouble();
        scanner.nextLine(); // Consume newline character

        Card playerCard = new Card(cardId, initialBalance);

        // Create game machines
        GameMachine machine1 = new GameMachine("M001", "Pac-Man", 5.0);
        GameMachine machine2 = new GameMachine("M002", "Street Fighter", 8.0);

        // Example actions at the arcade
        System.out.println("\nInitial Balance on Card: $" + playerCard.getBalance());

        // Player swipes card on machine 1
        System.out.println("\nPlayer swipes card on " + machine1.getGameName() + " machine...");
        if (machine1.swipeCard(playerCard)) {
            System.out.println("Successful swipe! Remaining balance: $" + playerCard.getBalance());
        }

        // Player swipes card on machine 2
        System.out.println("\nPlayer swipes card on " + machine2.getGameName() + " machine...");
        if (machine2.swipeCard(playerCard)) {
            System.out.println("Successful swipe! Remaining balance: $" + playerCard.getBalance());
        } else {
            System.out.println("Cannot play " + machine2.getGameName() + " due to insufficient balance.");
        }

        // Add more balance to the card
        System.out.print("\nEnter amount to add to the card: ");
        double addAmount = scanner.nextDouble();
        scanner.nextLine(); // Consume newline character
        playerCard.addBalance(addAmount);
        System.out.println("Added $" + addAmount + " to the card. New balance: $" + playerCard.getBalance());

        // Player tries to swipe card again on machine 2
        System.out.println("\nPlayer swipes card on " + machine2.getGameName() + " machine...");
        if (machine2.swipeCard(playerCard)) {
            System.out.println("Successful swipe! Remaining balance: $" + playerCard.getBalance());
        } else {
            System.out.println("Cannot play " + machine2.getGameName() + " due to insufficient balance.");
        }

        scanner.close();
    }
}