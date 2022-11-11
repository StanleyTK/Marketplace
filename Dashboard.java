import java.io.*;
import java.util.*;
public class Dashboard {
    public static void viewSeller() {
        //TODO Data will include a list of customers with the number of items that they have purchased and a list of products with the number of sales.
        //TODO Sellers can choose to sort the dashboard.
    }

    public static void viewCustomer() {
        //TODO Customers can view a dashboard with store and seller information.
        //TODO Data will include a list of stores by number of products sold and a list of stores by the products purchased by that particular customer.
        //TODO Customers can choose to sort the dashboard.
    }

    // export csv
    public static void csvFile(Seller seller) {
        //TODO Sellers can import or export products for their stores using a csv file.
        //TODO All product details should be included, with one row per product.

    }
    // import csv
    public static Seller csvFile(Seller seller, String fileName) throws IOException, IllegalArgumentException {
        File f = new File(fileName);
        FileReader fr = new FileReader(f);
        BufferedReader bfr = new BufferedReader(fr);
        String line = bfr.readLine();
        ArrayList<Product> products = new ArrayList<Product>();
        while (line != null) {
            Product product = MarketPlace.getProduct(line);
            products.add(product);
            seller.setProducts(products);
        }
        return seller;
    }

    public static void exportPurchaseHistory() {
        //TODO Customers can export a file with their purchase history.
    }
}
