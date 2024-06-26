import java.io.*;
import java.util.*;

/**
 * A class that prints out the CustomerPurchases
 *
 * <p>Purdue University -- CS18000 -- Fall 2022</p>
 *
 * @author Stanley Kim
 * @version November 14, 2022
 */

class CustomerPurchases { //Class used in the viewSeller method.
    public String customer;
    public ArrayList<String> purchases;
    public CustomerPurchases(String customer, ArrayList<String> purchases) {
        this.customer = customer;
        this.purchases = purchases;
    }

    public String getCustomer() {
        return customer;
    }

    public void setCustomer(String customer) {
        this.customer = customer;
    }

    public ArrayList<String> getPurchases() {
        return purchases;
    }

    public void setPurchases(ArrayList<String> purchases) {
        this.purchases = purchases;
    }
}


/**
 * A class that prints out the ProductPurchases
 *
 * <p>Purdue University -- CS18000 -- Fall 2022</p>
 *
 * @author Stanley Kim
 * @version November 14, 2022
 */

class ProductPurchases { //Class used in the viewSeller method.
    public String product;
    public int purchaseNumber;
    public ProductPurchases(String product, int purchaseNumber) {
        this.product = product;
        this.purchaseNumber = purchaseNumber;
    }

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public int getPurchaseNumber() {
        return purchaseNumber;
    }

    public void setPurchaseNumber(int purchaseNumber) {
        this.purchaseNumber = purchaseNumber;
    }
}

/**
 * A class that has the method for options in the marketplace
 *
 * <p>Purdue University -- CS18000 -- Fall 2022</p>
 *
 * @author Stanley Kim
 * @version November 14, 2022
 */
public class Dashboard {
    public static void viewSeller() {
        try {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Please enter a store name.");
            System.out.println(RunLocalTest.stores());
            String market = scanner.nextLine(); //Gets the store name from the user.
            FileReader fr = new FileReader(market + " Market.txt");
            BufferedReader br = new BufferedReader(fr);
            int delineate = 0; //Checks to see where in the market store br is.
            ArrayList<ProductPurchases> productPurchases = new ArrayList<>(); //ArrayList of products and purchases.
            ArrayList<CustomerPurchases> customerPurchases = new ArrayList<>(); //ArrayList of customers and purchases.
            ArrayList<String[]> purchases = new ArrayList<>(); //ArrayList for each purchase in the store.
            String line = br.readLine();
            while (line != null) {
                if (line.contains("------")) {
                    delineate++; //Increments delineate if it iterates through the given line.
                    line = br.readLine();
                }
                while (delineate == 0 && !line.contains("------")) {
                    productPurchases.add(new ProductPurchases(line, 0)); //Adds a new product.
                    line = br.readLine();
                }
                while (delineate == 1 && !line.contains("------")) {
                    customerPurchases.add(new CustomerPurchases(line, new ArrayList<>())); //Adds a new customer.
                    line = br.readLine();
                }
                while (delineate == 2 && line != null) {
                    String[] purchase = line.split(","); //Creates an array of the purchase.
                    purchases.add(purchase); //Adds the purchase.
                    line = br.readLine();
                }
            }
            br.close();

            for (CustomerPurchases currentCustomer : customerPurchases) {
                for (String[] currentPurchase : purchases) {
                    if (currentCustomer.getCustomer().equals(currentPurchase[5])) {
                        ArrayList<String> currentCustomerPurchases = currentCustomer.getPurchases();
                        currentCustomerPurchases.add(currentPurchase[0]);
                        currentCustomer.setPurchases(currentCustomerPurchases);
                    }
                }
            } //Assigns each purchase in the store to a customer.

            for (ProductPurchases currentProduct : productPurchases) {
                for (String[] currentPurchase : purchases) {
                    if (currentProduct.getProduct().equals(currentPurchase[0])) {
                        int purchaseNumber = Integer.parseInt(currentProduct.getPurchaseNumber() + currentPurchase[3]);
                        currentProduct.setPurchaseNumber(purchaseNumber);
                    }
                }
            } //Finds the total number of times each product has been purchased.
            System.out.println("The list of customers who have purchased from this store is: ");
            for (int i = 0; i < customerPurchases.size(); i++) {
                CustomerPurchases currentCustomer = customerPurchases.get(i);
                System.out.printf("%d. ", i + 1);
                System.out.printf("%s:\n", currentCustomer.getCustomer());
                for (int j = 0; j < currentCustomer.getPurchases().size(); j++) {
                    System.out.println(currentCustomer.getPurchases().get(j));
                }
            } //Prints the list of customers who have made purchases and their products.

            System.out.println("The list of products and the amount of times they have been purchased is: ");
            for (int i = 0; i < productPurchases.size(); i++) {
                ProductPurchases currentProduct = productPurchases.get(i);
                System.out.printf("%d. ", i + 1);
                System.out.printf("%s: %d\n", currentProduct.getProduct(), currentProduct.getPurchaseNumber());
            } //Prints the list of products in the store and the amount of times they have been purchased.
        } catch (IOException e) {
            System.out.println("The files were not found, and the products were not sold.");
        }
    }

    public static void viewCustomer(Customer customer) {
        try {
            String customerName = customer.getCustomerName();
            FileReader fr = new FileReader("Markets.txt");
            BufferedReader br = new BufferedReader(fr);
            String line = br.readLine();
            ArrayList<String[]> products = new ArrayList<>(); //ArrayList for each product in the store.
            ArrayList<String[]> purchases = new ArrayList<>(); //ArrayList for each purchase in the store.
            ArrayList<String[]> customerPurchases = new ArrayList<>(); //ArrayList for each purchase the customer made.
            while (line != null) {
                FileReader fileReader = new FileReader(line + " Market.txt");
                BufferedReader bufferedReader = new BufferedReader(fileReader);
                int delineate = 0; //Checks to see where in the market store bufferedReader is.
                String marketLine = bufferedReader.readLine();
                while (marketLine != null) {
                    if (marketLine.contains("------")) {
                        delineate++; //Increments delineate if it iterates through the given line.
                        marketLine = bufferedReader.readLine();
                    }
                    while (delineate == 0 && !marketLine.contains("------")) {
                        String[] product = marketLine.split(","); //Creates an array of the product.
                        products.add(product);
                        marketLine = bufferedReader.readLine(); //Creates an arraylist of products available.
                    }
                    while (delineate == 1 && !marketLine.contains("------")) {
                        marketLine = bufferedReader.readLine();
                    }
                    while (delineate == 2 && marketLine != null) {
                        String[] purchase = marketLine.split(","); //Creates an array of the purchase.
                        purchases.add(purchase);
                        marketLine = bufferedReader.readLine(); //Creates an arraylist of all the purchases.
                    }
                }
                bufferedReader.close();
                int productsNumber = products.size();
                System.out.printf("%s has %d products for sale.\n", line, productsNumber);
                for (int i = 0; i < products.size(); i++) {
                    System.out.printf("%d: %s\n", i + 1, Arrays.toString(products.get(i)));
                } //Prints all the products available for sale.
                for (String[] currentPurchase : purchases) {
                    if (customerName.equals(currentPurchase[5])) {
                        customerPurchases.add(currentPurchase);
                    }
                } //Checks if the customer has purchased, then adds to the arraylist of customer purchases.
                System.out.printf("From %s, %s has purchased the following products:\n", line, customerName);
                for (int i = 0; i < customerPurchases.size(); i++) {
                    System.out.printf("%d: %s\n", i + 1, Arrays.toString(customerPurchases.get(i)));
                } //Prints the products that the customer has purchased from the store.
                line = br.readLine();
            }
            br.close();
        } catch (IOException e) {
            System.out.println("There are no stores/products found. Sorry");
        }
    }


    // import csv
    public static void csvFile() {
        try {
            Scanner scanner = new Scanner(System.in);
            int option;
            File markets = new File("Markets.txt");
            BufferedReader bfr = new BufferedReader(new FileReader(markets));
            System.out.println("Which market would you like to import or export products to?");
            String storeName = scanner.nextLine();
            ArrayList<String> lines = new ArrayList<>();
            boolean bol = false;
            File f = null;

            String line = bfr.readLine();
            while (line != null) {
                System.out.println(line);
                lines.add(line);
                line = bfr.readLine();
            }

            for (String x : lines) {
                if (storeName.equals(x)) {
                    f = new File(storeName + " Market.txt");
                    bol = true;
                }
            }


            bfr.close();

            while (true) {
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
                        while (!line.contains("------")) {
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
                        while (!line.contains("-----")) {
                            products += line + "\n";
                            line = bfr.readLine();
                        }
                        PrintWriter pw = new PrintWriter(new FileOutputStream(file));
                        pw.print(products);
                        pw.close();
                        bfr.close();
                        System.out.println("Done\n");
                        break;
                    }
                }
            } else {
                System.out.println("Store is not found.");
            }
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("There are no markets available");
        }

    }

    public static void exportPurchaseHistory(Customer customer) {
        try {
            Scanner scanner = new Scanner(System.in);
            ArrayList<Product> products = new ArrayList<Product>();
            File marketsFile = new File("Markets.txt");
            BufferedReader bfr = new BufferedReader(new FileReader(marketsFile));
            ArrayList<String> markets = new ArrayList<String>();
            String line = bfr.readLine();
            while (line != null) {
                markets.add(line);
                line = bfr.readLine();
            }
            bfr.close();
            for (int i = 0; i < markets.size(); i++) {
                File file = new File(markets.get(i) + " Market.txt");
                bfr = new BufferedReader(new FileReader(file));
                line = bfr.readLine();
                while (!line.contains("------")) {
                    line = bfr.readLine();
                }
                line = bfr.readLine();
                while (!line.contains("------")) {
                    line = bfr.readLine();
                }
                line = bfr.readLine();
                while (line != null) {
                    String[] splitLine = line.split(",");
                    if (splitLine[5].equals(customer.getCustomerName())) {
                        products.add(new Product(splitLine[0], splitLine[1], splitLine[2],
                                Integer.parseInt(splitLine[3]), Double.parseDouble(splitLine[4])));
                    }
                    line = bfr.readLine();
                }
            }
            bfr.close();
            System.out.println("What is the file location you wish to export your purchase history to?");
            File f = new File(scanner.nextLine());
            PrintWriter pw = new PrintWriter(new FileOutputStream(f));
            pw.println(customer.getCustomerName() + "'s Purchase History");
            pw.println("--------");
            for (int i = 0; i < products.size(); i++) {
                Product product = products.get(i);
                pw.print("Purchased " + product.getQuantity() + " " + product.getName() + " for\n" +
                        product.getPrice());
                pw.printf(" each ($%.2f) total from %s.\n",
                        (product.getPrice() * (double) product.getQuantity()), product.getStore());
                pw.println("Description: " + product.getDescription());
                pw.println();
            }
            pw.close();
        } catch (IOException e) {
            System.out.println("There are no stores/products found. Sorry");
        }
    }
}
