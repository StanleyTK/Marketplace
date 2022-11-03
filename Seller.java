import java.util.ArrayList;

public class Seller extends User {

    private ArrayList<Product> products = new ArrayList<>();

    public Seller(String customerName, String password, String username, ArrayList<Product> products) {
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
        return "Seller{" +
                "products=" + products +
                ", customerName='" + customerName + '\'' +
                ", password='" + password + '\'' +
                ", username='" + username + '\'' +
                '}';
    }
}
