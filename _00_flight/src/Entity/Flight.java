package Entity;

import java.sql.Date;

public class Flight {
    //atributos

    private int id;
    private String destination;
    private Date departureDate;
    private String departureHour;
    private int idPlane;
    private Plane plane;

    //constructor

    public Flight(int id, String destination, Date departureDate, String departureHour, int idPlane, Plane plane) {
        this.id = id;
        this.destination = destination;
        this.departureDate = departureDate;
        this.departureHour = departureHour;
        this.idPlane = idPlane;
        this.plane = plane;
    }

    public Flight() {
    }

    //Getters and setters

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public Date getDepartureDate() {
        return departureDate;
    }

    public void setDepartureDate(Date departureDate) {
        this.departureDate = departureDate;
    }

    public String getDepartureHour() {
        return departureHour;
    }

    public void setDepartureHour(String departureHour) {
        this.departureHour = departureHour;
    }

    public int getIdPlane() {
        return idPlane;
    }

    public void setIdPlane(int idPlane) {
        this.idPlane = idPlane;
    }

    public Plane getPlane() {
        return plane;
    }

    public void setPlane(Plane plane) {
        this.plane = plane;
    }

    //to string

    @Override
    public String toString() {
        return "\n Flight information: " +
                "\n id_flight: " + id +
                "\n destination: " + destination +
                "\n departure_date: " + departureDate +
                "\n departure_hour: " + departureHour +
                "\n plane: " + plane
                ;
    }
}
