package gui.java;
import java.util.Random;
import java.util.Scanner;

public class JavaLibGame {

    // Define constants for game parameters
    private static final int NUM_ROOMS = 3;
    private static final int MAX_ENEMY_HEALTH = 50;
    private static final int MAX_ENEMY_ATTACK_POWER = 15;
    private static final int MAX_PLAYER_HEALTH = 100;
    private static final int PLAYER_ATTACK_POWER = 20;

    private Player player;
    private Room[] rooms;
    private boolean gameOver;
    private Random random;
    private Scanner scanner;

    public JavaLibGame() {
        player = new Player(MAX_PLAYER_HEALTH, PLAYER_ATTACK_POWER);
        rooms = new Room[NUM_ROOMS];
        gameOver = false;
        random = new Random();
        scanner = new Scanner(System.in);

        // Initialize rooms with enemies and items
        for (int i = 0; i < NUM_ROOMS; i++) {
            rooms[i] = new Room();
            int numEnemies = random.nextInt(3); // Up to 2 enemies per room
            for (int j = 0; j < numEnemies; j++) {
                rooms[i].addEnemy(generateRandomEnemy());
            }
            if (i > 0) {
                rooms[i].addItem(new Item("Potion")); // Add potion in rooms after first one
            }
        }
    }

    private Enemy generateRandomEnemy() {
        int enemyHealth = random.nextInt(MAX_ENEMY_HEALTH) + 1;
        int enemyAttackPower = random.nextInt(MAX_ENEMY_ATTACK_POWER) + 1;
        return new Enemy(enemyHealth, enemyAttackPower);
    }

    public void startGame() {
        System.out.println("Welcome to JavaLib Game!");

        while (!gameOver) {
            System.out.println("\nCurrent position: Room " + player.getCurrentPosition());
            System.out.println("Choose an action:");
            System.out.println("1. Move to the next room");
            System.out.println("2. Attack enemy (if any)");
            System.out.println("3. Collect item (if any)");
            System.out.println("4. Quit");

            int choice = getUserChoice();
            switch (choice) {
                case 1:
                    movePlayer();
                    break;
                case 2:
                    attackEnemy();
                    break;
                case 3:
                    collectItem();
                    break;
                case 4:
                    gameOver = true;
                    System.out.println("Exiting the game. Goodbye!");
                    break;
                default:
                    System.out.println("Invalid choice. Please choose a valid option.");
                    break;
            }

            // Check game over conditions
            if (player.getHealth() <= 0) {
                System.out.println("Game over! You are defeated.");
                gameOver = true;
            } else if (player.getCurrentPosition() >= NUM_ROOMS) {
                System.out.println("Congratulations! You've completed the game.");
                gameOver = true;
            }
        }

        // Close scanner
        scanner.close();
    }

    private int getUserChoice() {
        while (true) {
            try {
                System.out.print("Enter your choice: ");
                return Integer.parseInt(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number.");
            }
        }
    }

    private void movePlayer() {
        player.setCurrentPosition(player.getCurrentPosition() + 1);
        System.out.println("Moved to Room " + player.getCurrentPosition());
    }

    private void attackEnemy() {
        Room currentRoom = rooms[player.getCurrentPosition()];
        if (!currentRoom.getEnemies().isEmpty()) {
            Enemy enemy = currentRoom.getEnemies().get(0); // Simplified: Attack the first enemy
            player.attack(enemy);
            System.out.println("Attacked enemy. Enemy health: " + enemy.getHealth());
            if (enemy.getHealth() <= 0) {
                currentRoom.getEnemies().remove(enemy);
                System.out.println("Enemy defeated!");
            }
        } else {
            System.out.println("No enemies in this room.");
        }
    }

    private void collectItem() {
        Room currentRoom = rooms[player.getCurrentPosition()];
        if (!currentRoom.getItems().isEmpty()) {
            Item item = currentRoom.getItems().get(0); // Simplified: Collect the first item
            player.collectItem(item);
            System.out.println("Collected item: " + item.getName());
            currentRoom.getItems().remove(item);
        } else {
            System.out.println("No items in this room.");
        }
    }

    public static void main(String[] args) {
        JavaLibGame game = new JavaLibGame();
        game.startGame();
    }

    // Inner classes: Player, Enemy, Item, Room
    private class Player {
        private int health;
        private int attackPower;
        private int currentPosition;

        public Player(int health, int attackPower) {
            this.health = health;
            this.attackPower = attackPower;
            this.currentPosition = 0; // Starting position
        }

        public int getHealth() {
            return health;
        }

        public void setHealth(int health) {
            this.health = health;
        }

        public int getAttackPower() {
            return attackPower;
        }

        public int getCurrentPosition() {
            return currentPosition;
        }

        public void setCurrentPosition(int currentPosition) {
            this.currentPosition = currentPosition;
        }

        public void attack(Enemy enemy) {
            // Simplified attack mechanism, deducts enemy health
            enemy.takeDamage(attackPower);
        }

        public void collectItem(Item item) {
            // Simplified item collection mechanism, adds item to inventory
            // Implement inventory logic if needed
        }
    }

    private class Enemy {
        private int health;
        private int attackPower;

        public Enemy(int health, int attackPower) {
            this.health = health;
            this.attackPower = attackPower;
        }

        public int getHealth() {
            return health;
        }

        public void setHealth(int health) {
            this.health = health;
        }

        public int getAttackPower() {
            return attackPower;
        }

        public void takeDamage(int damage) {
            health -= damage;
            if (health < 0) {
                health = 0;
            }
        }
    }

    private class Item {
        private String name;

        public Item(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }
    }

    private class Room {
        private java.util.List<Enemy> enemies;
        private java.util.List<Item> items;

        public Room() {
            enemies = new java.util.ArrayList<>();
            items = new java.util.ArrayList<>();
        }

        public void addEnemy(Enemy enemy) {
            enemies.add(enemy);
        }

        public void addItem(Item item) {
            items.add(item);
        }

        public java.util.List<Enemy> getEnemies() {
            return enemies;
        }

        public java.util.List<Item> getItems() {
            return items;
        }
    }
}