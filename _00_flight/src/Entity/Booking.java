package Entity;

import java.sql.Date;

public class Booking {
    //Atributos
    private int id;
    private int idPassenger;
    private Passenger passenger;
    private int idFlight;
    private Flight flight;
    private Date bookingDate;
    private String seat;

    //constructor

    public Booking(int id, int idPassenger, Passenger passenger, int idFlight, Flight flight, Date bookingDate, String seat) {
        this.id = id;
        this.idPassenger = idPassenger;
        this.passenger = passenger;
        this.idFlight = idFlight;
        this.flight = flight;
        this.bookingDate = bookingDate;
        this.seat = seat;
    }

    public Booking() {
    }

    //Getter & Setters

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdPassenger() {
        return idPassenger;
    }

    public void setIdPassenger(int idPassenger) {
        this.idPassenger = idPassenger;
    }

    public Passenger getPassenger() {
        return passenger;
    }

    public void setPassenger(Passenger passenger) {
        this.passenger = passenger;
    }

    public int getIdFlight() {
        return idFlight;
    }

    public void setIdFlight(int idFlight) {
        this.idFlight = idFlight;
    }

    public Flight getFlight() {
        return flight;
    }

    public void setFlight(Flight flight) {
        this.flight = flight;
    }

    public Date getBookingDate() {
        return bookingDate;
    }

    public void setBookingDate(Date bookingDate) {
        this.bookingDate = bookingDate;
    }

    public String getSeat() {
        return seat;
    }

    public void setSeat(String seat) {
        this.seat = seat;
    }

    //to string

    @Override
    public String toString() {
        return "\n Booking information: " +
                "\n id_booking: " + id +
                "\n passenger: " + passenger +
                "\n flight: " + flight +
                "\n bookingDate: " + bookingDate +
                "\n seat: " + seat +
                "\n------------------------"
                ;
    }
}
