package project1;

import java.util.Scanner;

public class Railway_reservation_system {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        TrainService train = new TrainService(5);

        while (true) {
            System.out.println("\n--- Railway Reservation System ---");
            System.out.println("1. Book Ticket");
            System.out.println("2. Cancel Ticket");
            System.out.println("3. View Seats");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");
            
            int choice = sc.nextInt();

            switch (choice) {
                case 1:
                    System.out.print("Enter Name: ");
                    String name = sc.next();
                    System.out.print("Enter Age: ");
                    int age = sc.nextInt();
                    System.out.print("Enter Gender: ");
                    String gender = sc.next();
                    
                    train.bookTicket(name, age, gender);
                    break;

                case 2:
                    System.out.print("Enter The Ticket Number to Cancel: ");
                    int id = sc.nextInt();
                    train.cancelTicket(id);
                    break;

                case 3:
                    train.viewSeats();
                    break;

                case 4:
                    System.out.println("Exiting system...");
                    sc.close(); 
                    return; 

                default:
                    System.out.println("Invalid choice! Please try again.");
                    break;
            }
        }
    }
}