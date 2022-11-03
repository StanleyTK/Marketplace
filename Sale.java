import java.util.ArrayList;

public class Sale {
    private ArrayList<Product> products = new ArrayList<>();
    private String customer;

    public Sale(ArrayList<Product> products, String customer) {
        this.products = products;
        this.customer = customer;
    }

    public ArrayList<Product> getProducts() {
        return products;
    }

    public void setProducts(ArrayList<Product> products) {
        this.products = products;
    }

    public String getCustomer() {
        return customer;
    }

    public void setCustomer(String customer) {
        this.customer = customer;
    }

    @Override
    public String toString() {
        return "Sale{" +
                "products=" + products +
                ", customer='" + customer + '\'' +
                '}';
    }
}
