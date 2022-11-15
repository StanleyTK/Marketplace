import java.util.ArrayList;
import java.io.*;
/**
 * A Market class for Marketplace
 *
 * <p>Purdue University -- CS18000 -- Fall 2022</p>
 *
 * @author Stanley Kim
 * @version November 14, 2022
 */

public class Market {

    private ArrayList<Product> products;
    private String name;
    // checks if market already exists in markets.txt. throws MarketExistsException if it does
    // while markets.txt does not exist, creates new markets.txt file with itself as the first market name
    // creates its own market file of the format "name" + " Market.txt"
    // prints each product to the file, in the form name;;description;;store;;price;;quantity
    public Market(String name, ArrayList<Product> products) throws IOException, MarketExistsException {
        this.products = products;
        this.name = name;
        File f = new File("Markets.txt");
        ArrayList<String> markets = new ArrayList<String>();
        FileReader fr = new FileReader(f);
        BufferedReader bfr = new BufferedReader(fr);
        String line = bfr.readLine();
        while (line != null) {
            if (line.equals(name)) {
                throw new MarketExistsException(name);
            }
            markets.add(line);
            line = bfr.readLine();
        }
        bfr.close();
        FileOutputStream fos = new FileOutputStream(f);
        PrintWriter pw = new PrintWriter(fos);
        for (int i = 0; i < markets.size(); i++) {
            pw.println(markets.get(i));
        }
        pw.println(name);
        pw.close();
        String fileName = name + " Market.txt";
        File market = new File(fileName);
        fos = new FileOutputStream(market);
        pw = new PrintWriter(fos);
        for (int i = 0; i < products.size(); i++) {
            Product p = products.get(i);
            String product = p.getName() + ";;" + p.getDescription() + ";;" +
                    p.getStore() + ";;" + p.getPrice() + ";;" + p.getQuantity();
            pw.println(product);
        }
        pw.close();
    }

    public Market(String name) throws FileNotFoundException, IOException {
        File f = new File("Markets.txt");
        ArrayList<String> markets = new ArrayList<>();
        ArrayList<Product> prods = new ArrayList<>();
        FileReader fr = new FileReader(f);
        BufferedReader bfr = new BufferedReader(fr);
        String line = bfr.readLine();
        while (line != null) {
            if (line.equals(name)) {
                bfr.close();
                String fileName = name + " Market.txt";
                File market = new File(fileName);
                fr = new FileReader(fileName);
                bfr = new BufferedReader(fr);
                line = bfr.readLine();
                while (line != null) {
                    Product product = MarketPlace.getProduct(line);
                    prods.add(product);
                    line = bfr.readLine();
                }
            }
            markets.add(line);
            line = bfr.readLine();
        }
        bfr.close();
        this.name = name;
        this.products = prods;
    }

    public ArrayList<Product> getProducts() {
        return products;
    }

    public void setProducts(ArrayList<Product> products) throws FileNotFoundException {
        this.products = products;
        String fileName = this.name + " Market.txt";
        File market = new File(fileName);
        FileOutputStream fos = new FileOutputStream(market);
        PrintWriter pw = new PrintWriter(fos);
        for (int i = 0; i < products.size(); i++) {
            Product p = products.get(i);
            String product = p.getName() + ";;" + p.getDescription() + ";;" + p.getStore() + ";;" + p.getPrice() + ";;" + p.getQuantity();
            pw.println(product);
        }
        pw.close();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Store{" + "name=" + name +
                ", products=" + products +
                '}';
    }
}
