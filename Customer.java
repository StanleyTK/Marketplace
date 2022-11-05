import java.util.ArrayList;

public class Customer extends User {

    private ArrayList<Product> products = new ArrayList<>();

    public Customer(String customerName, String username, String password, ArrayList<Product> products) {
        super(customerName, password, username);
        this.products = products;
    }

    public ArrayList<Product> getProducts() {
        return products;
    }

    public void setProducts(ArrayList<Product> products) {
        this.products = products;
    }

    public String toString() {
        return "Customer{" +
                "products=" + products.get(0).toString() +
                ", customerName='" + getCustomerName() + '\'' +
                ", password='" + getPassword() + '\'' +
                ", username='" + getUsername() + '\'' +
                '}';
    }
}