package Entity;

public class Passenger {

    //atributos
    private int id;
    private String name;
    private String lastname;
    private String idDocument;

    //Constructor

    public Passenger(int id, String name, String lastname, String idDocument) {
        this.id = id;
        this.name = name;
        this.lastname = lastname;
        this.idDocument = idDocument;
    }

    public Passenger() {
    }

    //Getters and setters

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

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getIdDocument() {
        return idDocument;
    }

    public void setIdDocument(String idDocument) {
        this.idDocument = idDocument;
    }

    //to string

    @Override
    public String toString() {
        return "\n Passenger information: " +
                "\n id_passenger: " + id +
                "\n name: " + name + " " + lastname  +
                "\n idDocument: " + idDocument +
                "\n------------------------"
                ;
    }
}
