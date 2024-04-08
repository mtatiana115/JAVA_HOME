package entity;

public class Bill {
    //atributos
    private Product product;
    private Store store;
    private Customer customer;

    //


    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Store getStore() {
        return store;
    }

    public void setStore(Store store) {
        this.store = store;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    @Override
    public String toString() {
        return "\nproduct name: " + this.product.getName() +
                "\nprice: " + this.product.getPrice() +
                "\nstore name: " + this.store.getName() +
                "\nstore ubication: " + this.store.getUbication() +
                "\ncustomer name: " + this.customer.getName() + " " + this.customer.getLastname() +
                "\nCustomer email: " + this.customer.getEmail() + ""
                ;
    }
}
