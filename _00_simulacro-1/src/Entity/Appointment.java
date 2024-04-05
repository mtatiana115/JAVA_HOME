package Entity;

import java.sql.Date;
import java.sql.Time;

public class Appointment {
    //atributos
    private int id;
    private int idPatient;
    private int idDoctor;
    private Date appointmentDate;
    private String appointmentHour;
    private String reason;
    private Doctor doctor;
    private Patient patient;

    //constructor

    public Appointment(int id, int idPatient, int idDoctor, Date appointmentDate, String appointmentHour, String reason, Doctor doctor, Patient patient) {
        this.id = id;
        this.idPatient = idPatient;
        this.idDoctor = idDoctor;
        this.appointmentDate = appointmentDate;
        this.appointmentHour = appointmentHour;
        this.reason = reason;
        this.doctor = doctor;
        this.patient = patient;
    }

    public Appointment() {
    }

    //getters & setters

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdPatient() {
        return idPatient;
    }

    public void setIdPatient(int idPatient) {
        this.idPatient = idPatient;
    }

    public int getIdDoctor() {
        return idDoctor;
    }

    public void setIdDoctor(int idDoctor) {
        this.idDoctor = idDoctor;
    }

    public Date getAppointmentDate() {
        return appointmentDate;
    }

    public void setAppointmentDate(Date appointmentDate) {
        this.appointmentDate = appointmentDate;
    }

    public String getAppointmentHour() {
        return appointmentHour;
    }

    public void setAppointmentHour(String appointmentHour) {
        this.appointmentHour = appointmentHour;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    //to String

    @Override
    public String toString() {
        return "Appointment{" +
                "id=" + id +
                ", idPatient=" + idPatient +
                ", idDoctor=" + idDoctor +
                ", appointmentDate=" + appointmentDate +
                ", appointmentHour='" + appointmentHour + '\'' +
                ", reason='" + reason + '\'' +
                ", doctor=" + doctor +
                ", patient=" + patient +
                '}';
    }
}
