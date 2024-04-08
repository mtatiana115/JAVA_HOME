package entity;

public class Customer {
    //atributos
    private int id;
    private String name;
    private  String lastname;
    private String email;

    //constructores

    public Customer(String name, String lastname, String email) {
        this.name = name;
        this.lastname = lastname;
        this.email = email;
    }

    public Customer() {
    }

    //getters &setters

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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    //to String

    @Override
    public String toString() {
        return "___" +
                "\nCustomer id: " + id +
                "\nname: " + name + " " + lastname +
                "\nemail: " + email ;
    }
}
