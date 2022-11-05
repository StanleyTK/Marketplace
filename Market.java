import java.util.ArrayList;

public class Market {

    private ArrayList<Product> products;
    private ArrayList<Customer> customer;

    public Market(ArrayList<Product> products, ArrayList<Customer> customer) {
        this.products = products;
        this.customer = customer;
    }


    public ArrayList<Product> getProducts() {
        return products;
    }

    public void setProducts(ArrayList<Product> products) {
        this.products = products;
    }

    public ArrayList<Customer> getCustomer() {
        return customer;
    }

    public void setCustomer(ArrayList<Customer> customer) {
        this.customer = customer;
    }

    @Override
    public String toString() {
        return "Store{" +
                "products=" + products +
                ", customer=" + customer +
                '}';
    }
}
