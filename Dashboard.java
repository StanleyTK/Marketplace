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
        //TODO Sellers can import or export products for their stores using a csv file.
        //TODO All product details should be included, with one row per product.
    // import csv
    public static void csvFile() throws IOException {
        Scanner scanner = new Scanner(System.in);
        int option;
        while(true) {
            try {
                System.out.println("Would you like to import or export?\n1. Import\n2. Export");
                option = Integer.parseInt(scanner.nextLine());
                if (option == 1 || option == 2) {
                    break;
                }
                System.out.println("Enter a valid input!");
            } catch (NumberFormatException e) {
                System.out.println("Enter a valid input!");
            }
        }
        System.out.println("Which market would you like to import or export products to?");
        String storeName = scanner.nextLine();
        File markets = new File("Markets.txt");
        BufferedReader bfr = new BufferedReader(new FileReader(markets));
        String line;
        boolean bol = false;
        File f = null;
        while ((line = bfr.readLine()) != null) {
            if (storeName.equals(line)) {
                f = new File(storeName + " Market.txt");
                bol = true;
            }
        }
        bfr.close();
        if (bol) {
            switch (option) {
                case (1) -> {
                    System.out.println("What is the file location you are importing from?");
                    File file = new File(scanner.nextLine());
                    FileReader fr = new FileReader(file);
                    bfr = new BufferedReader(fr);
                    ArrayList<Product> products = new ArrayList<Product>();
                    line = bfr.readLine();
                    while (line != null) {
                        products.add(MarketPlace.getProduct(line));
                        line = bfr.readLine();
                    }
                    bfr.close();
                    bfr = new BufferedReader(new FileReader(f));
                    line = bfr.readLine();
                    while (!line.equals("--------")) {
                        line = bfr.readLine();
                    }
                    String rest = "";
                    while (line != null) {
                        rest += line + "\n";
                        line = bfr.readLine();
                    }
                    FileOutputStream fos = new FileOutputStream(f);
                    PrintWriter pw = new PrintWriter(fos);
                    for (int i = 0; i < products.size(); i++) {
                        pw.println(products.get(i).toString());
                    }
                    pw.print(rest);
                    pw.close();
                    bfr.close();
                    break;
                }
                case (2) -> {
                    System.out.println("What is the file location where the products should be exported?");
                    File file = new File(scanner.nextLine());
                    bfr = new BufferedReader(new FileReader(f));
                    line = bfr.readLine();
                    String products = "";
                    while (!line.equals("--------")) {
                        products += line + "\n";
                        line = bfr.readLine();
                    }
                    PrintWriter pw = new PrintWriter(new FileOutputStream(file));
                    pw.print(products);
                    pw.close();
                    bfr.close();
                    break;
                }
            }
        }
    }

    public static void exportPurchaseHistory() {
        //TODO Customers can export a file with their purchase history.
    }
}
