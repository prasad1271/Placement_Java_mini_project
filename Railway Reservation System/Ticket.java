package project1;

public class Ticket {
    private int ticketId;
    private Passenger passenger;
    private String status;
    private int seatNumber;

    public Ticket(int ticketId, Passenger passenger, String status, int seatNumber) {
        this.ticketId = ticketId;
        this.passenger = passenger;
        this.status = status;
        this.seatNumber = seatNumber;
    }

    public int getTicketId() {
        return ticketId;
    }

    public Passenger getPassenger() {
        return passenger;
    }

    public String getStatus() {
        return status;
    }

    public int getSeatNumber() {
        return seatNumber;
    }
}