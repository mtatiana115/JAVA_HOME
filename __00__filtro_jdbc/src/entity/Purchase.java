package entity;

public class Purchase {
    //atributos
    private int id;
    private int amount;
    private String purchaseDate;
    private int idCustomer;
    private Customer customer;
    private int idProduct;
    private Product product;

    //constructores


    public Purchase(int id, int amount, String purchaseDate, int idCustomer, Customer customer, int idProduct, Product product) {
        this.id = id;
        this.amount = amount;
        this.purchaseDate = purchaseDate;
        this.idCustomer = idCustomer;
        this.customer = customer;
        this.idProduct = idProduct;
        this.product = product;
    }

    public Purchase() {
    }

    //getters & setters

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getPurchaseDate() {
        return purchaseDate;
    }

    public void setPurchaseDate(String purchaseDate) {
        this.purchaseDate = purchaseDate;
    }

    public int getIdCustomer() {
        return idCustomer;
    }

    public void setIdCustomer(int idCustomer) {
        this.idCustomer = idCustomer;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public int getIdProduct() {
        return idProduct;
    }

    public void setIdProduct(int idProduct) {
        this.idProduct = idProduct;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }


    //

    @Override
    public String toString() {
        return "\n________________________________________" +
                "\nPurchase id: " + id +
                "\namount: " + amount +
                "\npurchaseDate: " + purchaseDate +
                "\ncustomer: " + customer +
                "\nproduct: " + product ;
    }
}
