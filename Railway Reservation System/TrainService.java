package project1;

import java.util.*;

public class TrainService {
    private List<Integer> availableSeats = new ArrayList<>();
    private Map<Integer, Ticket> bookedTickets = new HashMap<>();
    private int ticketCounter = 1;
    private int totalSeats;

    public TrainService(int totalSeats) {
        this.totalSeats = totalSeats;
        for (int i = 1; i <= totalSeats; i++) {
            availableSeats.add(i);
        }
    }

    public void bookTicket(String name, int age, String gender) {
        Passenger passenger = new Passenger(name, age, gender);

        if (!availableSeats.isEmpty()) {
            int seat = availableSeats.remove(0);
            Ticket ticket = new Ticket(ticketCounter++, passenger, "CONFIRMED", seat);
            bookedTickets.put(ticket.getTicketId(), ticket);

            System.out.println("Booked ID: " + ticket.getTicketId() + " Seat: " + seat);
        } else {
            Ticket ticket = new Ticket(ticketCounter++, passenger, "WAITING", -1);
            bookedTickets.put(ticket.getTicketId(), ticket);

            System.out.println("Added to Waiting ID: " + ticket.getTicketId());
        }
    }

    public void cancelTicket(int ticketId) {
        if (!bookedTickets.containsKey(ticketId)) {
            System.out.println("Invalid Ticket ID");
            return;
        }

        Ticket ticket = bookedTickets.remove(ticketId);

        if (ticket.getStatus().equals("CONFIRMED")) {
            availableSeats.add(ticket.getSeatNumber());
            System.out.println("Cancelled Seat: " + ticket.getSeatNumber());
        } else {
            System.out.println("Waiting ticket cancelled");
        }
    }

    public void viewSeats() {
        int reserved = getConfirmedCount();
        int free = availableSeats.size();

        System.out.println("\nTotal Seats: " + totalSeats);
        System.out.println("Reserved Seats: " + reserved);
        System.out.println("Available Seats Count: " + free);

        Collections.sort(availableSeats);
        System.out.println("Available Seat Numbers: " + availableSeats);

        System.out.println("\nReserved Passenger List:");
        displayPassengers();
    }

    private int getConfirmedCount() {
        int count = 0;
        for (Ticket t : bookedTickets.values()) {
            if (t.getStatus().equals("CONFIRMED")) {
                count++;
            }
        }
        return count;
    }

    private void displayPassengers() {
        boolean found = false;

        for (Ticket t : bookedTickets.values()) {
            if (t.getStatus().equals("CONFIRMED")) {
                Passenger p = t.getPassenger();

                System.out.println(
                        "ID: " + t.getTicketId() +
                        " Name: " + p.getName() +
                        " Age: " + p.getAge() +
                        " Gender: " + p.getGender() +
                        " Seat: " + t.getSeatNumber()
                );
                found = true;
            }
        }

        if (!found) {
            System.out.println("No reservations yet");
        }
    }
}