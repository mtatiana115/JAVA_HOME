package Entity;

public class Specialty {
    //atributos
    private int id;
    private String name;
    private String description;

    //constructores
    public Specialty(int id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }

    public Specialty() {
    }

    //getters  & setters

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    //to String

    @Override
    public String toString() {
        return "Specialty{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
