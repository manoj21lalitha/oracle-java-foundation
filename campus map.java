package gui.java;
import java.util.*;

public class CampusMap {

    static class Dorm {
        private String name;
        private int population;
        private List<String> friends;

        public Dorm(String name, int population) {
            this.name = name;
            this.population = population;
            this.friends = new ArrayList<>();
        }

        public String getName() {
            return name;
        }

        public int getPopulation() {
            return population;
        }

        public void addFriend(String friendName) {
            friends.add(friendName);
        }

        public List<String> getFriends() {
            return friends;
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<Dorm> dorms = new ArrayList<>();

        System.out.println("Welcome to CampusMap!");

        // Prompt user to add dormitories dynamically
        boolean addingDorms = true;
        while (addingDorms) {
            System.out.print("\nEnter dormitory name (or 'exit' to finish adding dorms): ");
            String dormName = scanner.nextLine().trim();

            if (dormName.equalsIgnoreCase("exit")) {
                addingDorms = false;
                break;
            }

            System.out.print("Enter population of " + dormName + ": ");
            int population = scanner.nextInt();
            scanner.nextLine(); // Consume newline character

            Dorm dormitory = new Dorm(dormName, population);

            // Prompt user to add friends dynamically
            boolean addingFriends = true;
            while (addingFriends) {
                System.out.print("Add friend to " + dormName + " (or 'done' to finish adding friends): ");
                String friendName = scanner.nextLine().trim();

                if (friendName.equalsIgnoreCase("done")) {
                    addingFriends = false;
                } else {
                    dormitory.addFriend(friendName);
                }
            }

            dorms.add(dormitory);
        }

        // Print campus map
        System.out.println("\nCampus Map:");

        for (Dorm dorm : dorms) {
            System.out.println("Dormitory: " + dorm.getName());
            System.out.println("Population: " + dorm.getPopulation());
            System.out.println("Friends: " + dorm.getFriends());
            System.out.println();
        }

        scanner.close();
    }
}