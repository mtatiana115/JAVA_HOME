package Entity;

public class Doctor {
    //atributos
    private int id;
    private String name;
    private String lastName;
    private int idSpecialty;
    private  Specialty specialty;

    //constructor
    public Doctor(int id, String name, String lastName, int idSpecialty, Specialty specialty) {
        this.id = id;
        this.name = name;
        this.lastName = lastName;
        this.idSpecialty = idSpecialty;
        this.specialty = specialty;
    }

    public Doctor() {
    }

    //Getters & setters

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getIdSpecialty() {
        return idSpecialty;
    }

    public void setIdSpecialty(int idSpecialty) {
        this.idSpecialty = idSpecialty;
    }

    public Specialty getSpecialty() {
        return specialty;
    }

    public void setSpecialty(Specialty specialty) {
        this.specialty = specialty;
    }

    //to String
    @Override
    public String toString() {
        return "doctor{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", lastName='" + lastName + '\'' +
                ", idSpecialty=" + idSpecialty +
                ", specialty=" + specialty +
                '}';
    }
}
