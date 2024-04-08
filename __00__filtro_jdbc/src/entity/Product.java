package entity;

public class Product {

    //atributos
    private int id;
    private String name;
    private double price;
    private int idStore;
    private Store store;
    private int stock;

    //constructor


    public Product(String name, double price, int idStore, Store store, int stock) {
        this.name = name;
        this.price = price;
        this.idStore = idStore;
        this.store = store;
        this.stock = stock;
    }


    public Product() {
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

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getIdStore() {
        return idStore;
    }

    public void setIdStore(int idStore) {
        this.idStore = idStore;
    }

    public Store getStore() {
        return store;
    }

    public void setStore(Store store) {
        this.store = store;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }


    //to String

    @Override
    public String toString() {
        return "___" +
                "\nProduct id: " + id +
                "\nname: " + name +
                "\nprice: " + price +
                "\nstock: " + stock +
                store;
    }


}
