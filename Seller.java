import java.nio.Buffer;
import java.util.ArrayList;
import java.io.*;

public class Seller extends User {

    private ArrayList<Product> products = new ArrayList<Product>();

    // creates a new seller object and seller file
    public Seller(String customerName, String username, String password, ArrayList<Product> products) {
        super(customerName, username, password);

        try {
            this.products = products;
            File f = new File(customerName + "'s File.txt");
            FileOutputStream fos = new FileOutputStream(f);
            PrintWriter pw = new PrintWriter(fos);
            pw.println("Name: " + username);
            pw.println("User: Seller");
            pw.println();
            for (Product product : products) {
                pw.println(product.toString());
            }
            pw.close();
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("There was an error");
        }
    }
    // retrieve seller info from a file and create seller object
    public Seller(String name, String username, String password) {
        super(name, username, password);

        try {
            File f = new File(name + "'s File.txt");
            FileReader fr = new FileReader(f);
            BufferedReader bfr = new BufferedReader(fr);
            bfr.readLine();
            bfr.readLine();
            String[] customerList = bfr.readLine().split(",");
            ArrayList<String> customers = new ArrayList<String>();
            for (String s : customerList) {
                customers.add(s);
                ;
            }
            ArrayList<Product> products = new ArrayList<Product>();
            String line = bfr.readLine();
            while (line != null) {
                Product product = MarketPlace.getProduct(line);
                products.add(product);
                line = bfr.readLine();
            }
            this.products = products;
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("There was an error");
        }
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
                ", customerName='" + getCustomerName() + '\'' +
                ", password='" + getPassword() + '\'' +
                ", username='" + getUsername() + '\'' +
                '}';
    }
}