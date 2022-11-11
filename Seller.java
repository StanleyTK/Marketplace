import java.util.ArrayList;
import java.io.*;
public class Seller extends User {

    private ArrayList<Product> products = new ArrayList<>();

    public Seller(String customerName, String username, String password, ArrayList<Product> products) {
        super(customerName, username, password);
        this.products = products;
    }

    public ArrayList<Product> getProducts() {
        return products;
    }

    public void setProducts(ArrayList<Product> products) throws IOException {
        for (int i = 0; i < products.size(); i++) {
            String market = products.get(i).getStore();
            File f = new File(market + " Market.txt");
            FileReader fr = new FileReader(f);
            BufferedReader bfr = new BufferedReader(fr);
            String line = bfr.readLine();
            String contents = "";
            while (line != null) {
                contents += line;
                line = bfr.readLine();
            }
            FileOutputStream fos = new FileOutputStream(f);
            PrintWriter pw = new PrintWriter(fos);
            pw.println(contents);
            pw.println(products.get(i));
            bfr.close();
            pw.close();
        }
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
