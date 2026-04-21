package project1;

import java.util.*;

class User {
    int id;
    String name;
    Set<User> friends;

    User(int id, String name) {
        this.id = id;
        this.name = name;
        this.friends = new HashSet<>();
    }
}

public class Social_network {

    static Map<String, User> users = new HashMap<>();
    static int idCounter = 1;

    static void addUser(Scanner sc) {
        System.out.print("Enter user name: ");
        String name = sc.nextLine();

        if (users.containsKey(name)) {
            System.out.println("User already exists!");
        } else {
            users.put(name, new User(idCounter++, name));
            System.out.println("User added!");
        }
    }

    static void addFriend(Scanner sc) {
        System.out.print("Enter first user: ");
        String u1 = sc.nextLine();

        System.out.print("Enter second user: ");
        String u2 = sc.nextLine();

        if (!users.containsKey(u1) || !users.containsKey(u2)) {
            System.out.println("User not found!");
            return;
        }

        if (u1.equals(u2)) {
            System.out.println("Cannot add self as friend!");
            return;
        }

        User user1 = users.get(u1);
        User user2 = users.get(u2);

        if (user1.friends.contains(user2)) {
            System.out.println("Already friends!");
            return;
        }

        user1.friends.add(user2);
        user2.friends.add(user1);

        System.out.println("Friendship added!");
    }

    static void viewFriends(Scanner sc) {
        System.out.print("Enter user name: ");
        String name = sc.nextLine();

        if (!users.containsKey(name)) {
            System.out.println("User not found!");
            return;
        }

        User user = users.get(name);

        System.out.println("Friends of " + name + ":");
        if (user.friends.isEmpty()) {
            System.out.println("No friends.");
            return;
        }

        for (User f : user.friends) {
            System.out.println(f.name);
        }
    }

    static void mutualFriends(Scanner sc) {
        System.out.print("Enter first user: ");
        String u1 = sc.nextLine();

        System.out.print("Enter second user: ");
        String u2 = sc.nextLine();

        if (!users.containsKey(u1) || !users.containsKey(u2)) {
            System.out.println("User not found!");
            return;
        }

        User user1 = users.get(u1);
        User user2 = users.get(u2);

        System.out.println("Mutual Friends:");

        boolean found = false;

        for (User f : user1.friends) {
            if (user2.friends.contains(f)) {
                System.out.println(f.name);
                found = true;
            }
        }

        if (!found) {
            System.out.println("No mutual friends.");
        }
    }

    static void suggestFriends(Scanner sc) {
        System.out.print("Enter user name: ");
        String name = sc.nextLine();

        if (!users.containsKey(name)) {
            System.out.println("User not found!");
            return;
        }

        User user = users.get(name);

        Set<User> visited = new HashSet<>();
        Queue<User> q = new LinkedList<>();

        Map<User, Integer> level = new HashMap<>();

        q.add(user);
        visited.add(user);
        level.put(user, 0);

        Set<String> suggestions = new HashSet<>();

        while (!q.isEmpty()) {
            User curr = q.poll();

            for (User f : curr.friends) {
                if (!visited.contains(f)) {
                    visited.add(f);
                    q.add(f);
                    level.put(f, level.get(curr) + 1);

                    if (level.get(f) == 2 && !user.friends.contains(f)) {
                        suggestions.add(f.name);
                    }
                }
            }
        }

        System.out.println("Friend Suggestions:");

        if (suggestions.isEmpty()) {
            System.out.println("No suggestions.");
        } else {
            for (String s : suggestions) {
                System.out.println(s);
            }
        }
    }

    static void viewAllUsers() {
        System.out.println("\n===== ALL USERS =====");

        if (users.isEmpty()) {
            System.out.println("No users.");
            return;
        }

        for (User u : users.values()) {
            System.out.println("ID: " + u.id + " | " + u.name + " | Friends: " + u.friends.size());
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("\n===== SOCIAL NETWORK =====");
            System.out.println("1. Add User");
            System.out.println("2. Add Friendship");
            System.out.println("3. View Friends");
            System.out.println("4. Mutual Friends");
            System.out.println("5. Suggest Friends");
            System.out.println("6. View All Users");
            System.out.println("7. Exit");
            System.out.print("Enter choice: ");

            int choice;

            if (sc.hasNextInt()) {
                choice = sc.nextInt();
                sc.nextLine();
            } else {
                System.out.println("Invalid input!");
                sc.next();
                continue;
            }

            switch (choice) {
                case 1: addUser(sc); break;
                case 2: addFriend(sc); break;
                case 3: viewFriends(sc); break;
                case 4: mutualFriends(sc); break;
                case 5: suggestFriends(sc); break;
                case 6: viewAllUsers(); break;
                case 7:
                    System.out.println("Exiting...");
                    sc.close();
                    return;
                default:
                    System.out.println("Invalid choice!");
            }
        }
    }
}