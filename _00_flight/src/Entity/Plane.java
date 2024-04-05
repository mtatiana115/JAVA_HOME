package Entity;

public class Plane {

    //atributos
    private int id;
    private String model;
    private int capacity;

    //constructor

    public Plane(int id, String model, int capacity) {
        this.id = id;
        this.model = model;
        this.capacity = capacity;
    }

    public Plane() {
    }

    //Getters & setters

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    //to string

    @Override
    public String toString() {
        return "\n  Plane information: "+
                "\n id_plane:"+ id +
                "\n model='" + model + '\'' +
                "\n capacity=" + capacity +
                "\n------------------------"
                ;
    }
}
