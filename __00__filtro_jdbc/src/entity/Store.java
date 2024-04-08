package entity;

public class Store {
    //atrivutos
    private int id;
    private String name;
    private String ubication;

    //constructores


    public Store(int id, String name, String ubication) {
        this.id = id;
        this.name = name;
        this.ubication = ubication;
    }

    public Store() {
    }

    //getters & setters

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

    public String getUbication() {
        return ubication;
    }

    public void setUbication(String ubication) {
        this.ubication = ubication;
    }


    //to String

    @Override
    public String toString() {
        return  "\nStore id: " + id +
                "\nname: " + name +
                "\nubication: " + ubication;
    }
}
