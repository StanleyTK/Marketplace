import java.util.ArrayList;

public class Customer extends User {

    private ArrayList<Product> products = new ArrayList<>();

    public Customer(String customerName, String password, String username, ArrayList<Product> products) {
        super(customerName, password, username);
        this.products = products;
    }

    public ArrayList<Product> getProducts() {
        return products;
    }

    public void setProducts(ArrayList<Product> products) {
        this.products = products;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "products=" + products +
                ", customerName='" + getCustomerName() + '\'' +
                ", password='" + getPassword() + '\'' +
                ", username='" + getUsername() + '\'' +
                '}';
    }
}
