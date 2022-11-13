import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

class PurchaseInformation { //Class used in the viewSales method.
    public String customer;
    public ArrayList<String> purchases;
    public ArrayList<Integer> amountPurchased;
    public ArrayList<Double> price;
    public PurchaseInformation(String customer, ArrayList<String> purchases, ArrayList<Integer> amountPurchased, ArrayList<Double> price) {
        this.customer = customer;
        this.purchases = purchases;
        this.amountPurchased = amountPurchased;
        this.price = price;
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

    public ArrayList<Integer> getAmountPurchased() {
        return amountPurchased;
    }

    public void setAmountPurchased(ArrayList<Integer> amountPurchased) {
        this.amountPurchased = amountPurchased;
    }

    public ArrayList<Double> getPrice() {
        return price;
    }

    public void setPrice(ArrayList<Double> price) {
        this.price = price;
    }
}

public class SellerOptions {
    // Seller Option 2
    public static void editProducts(Scanner scanner) throws IOException {
        int option;
        while (true) {
            try {
                System.out.print("\nWhich Option of these would you like to choose?\n" +
                        "1. Create a new product\n" +
                        "2. Edit a product\n" +
                        "3. Delete a product");
                option = Integer.parseInt(scanner.nextLine());
                if (option == 1 || option == 2 || option == 3) {
                    break;
                }
                System.out.println("Enter a valid input!");

            } catch (NumberFormatException e) {
                System.out.println("Enter a valid input");
            }
        }
        System.out.println("Which store would you like to make the changes?");
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
        String printer = "";
        if (bol) {
            switch (option) {
                case (1) -> {
                    System.out.println("What is the Product's new name?");
                    String name = scanner.nextLine();
                    System.out.println("What is the Product's new description?");
                    String desc = scanner.nextLine();
                    System.out.println("What is the Product's new quantity?");
                    String quantity = scanner.nextLine();
                    System.out.println("What is the Product's new price?");
                    String price = scanner.nextLine();
                    FileReader fr = new FileReader(f);
                    BufferedReader br = new BufferedReader(fr);
                    line = br.readLine();
                    String after = "";
                    while (line != null) {
                        after += line + "\n";
                        line = br.readLine();
                    }

                    Product product = new Product(name, storeName, desc, Integer.parseInt(quantity), Double.parseDouble(price));
                    FileOutputStream fos = new FileOutputStream(f);
                    PrintWriter pw = new PrintWriter(fos);
                    pw.println(product.toString());
                    pw.print(after);
                    pw.close();
                    br.close();


                }
                case (2) -> {
                    printer = printer + storeName + "\n" + "-------------\n";
                    BufferedReader productReader = new BufferedReader(new FileReader(f));
                    ArrayList<Product> products = new ArrayList<>();
                    line = productReader.readLine();
                    while (!line.equals("--------")) { //iterates through lines of files and adds them to string
                        Product product = MarketPlace.getProduct(line);
                        products.add(product);
                        printer = printer +
                                "Product: " + product.getName() + "\n" +
                                "Description: " + product.getDescription() + "\n" +
                                "Price: " + product.getPrice() + "\n" +
                                "Quantity " + product.getQuantity();
                        line = productReader.readLine();
                    }
                    String rest = "";
                    while (line != null) {
                        rest += line + "\n";
                        line = productReader.readLine();
                    }
                    System.out.println(printer);
                    System.out.println("Which of these items would you like to edit from " + storeName);
                    String item = scanner.nextLine();
                    boolean edited = false;
                    for (Product product : products) {
                        if (item.equals(product.getName())) {
                            edited = true;
                            System.out.println("What is the Product's new name?");
                            product.setName(scanner.nextLine());
                            System.out.println("What is the Product's new description?");
                            product.setDescription(scanner.nextLine());
                            System.out.println("What is the Product's new quantity?");
                            product.setQuantity(Integer.parseInt(scanner.nextLine()));
                            System.out.println("What is the Product's new price?");
                            product.setPrice(Double.parseDouble(scanner.nextLine()));
                            break;
                        }
                    }
                    if (!edited) {
                        System.out.println("The product was not found in the list");
                    } else {
                        System.out.println("The product was successfully edited");
                        System.out.println("Updating the file...");
                        FileOutputStream fos = new FileOutputStream(f);
                        PrintWriter pw = new PrintWriter(fos);
                        for (Product product : products) {
                            System.out.println(product.toString());
                            pw.println(product.toString());
                        }
                        pw.print(rest);
                        pw.close();
                    }

                }
                case (3) -> {
                    printer = printer + storeName + "\n" + "-------------\n";
                    BufferedReader productReader = new BufferedReader(new FileReader(f));
                    ArrayList<Product> products = new ArrayList<>();
                    line = productReader.readLine();
                    while (!line.equals("--------")) { //iterates through lines of files and adds them to string
                        Product product = MarketPlace.getProduct(line);
                        products.add(product);
                        printer = printer +
                                "Product: " + product.getName() + "\n" +
                                "Description: " + product.getDescription() + "\n" +
                                "Price: " + product.getPrice() + "\n" +
                                "Quantity " + product.getQuantity();
                        line = productReader.readLine();
                    }
                    String after = "";
                    while (line != null) {
                        after += line + "\n";
                        line = productReader.readLine();
                    }
                    System.out.println(printer);
                    System.out.println("Which of these items would you like to remove from " + storeName);
                    String item = scanner.nextLine();
                    boolean removed = false;
                    Product productFound = null;
                    for (Product product : products) {
                        if (item.equals(product.getName())) {
                            removed = true;
                            productFound = product;
                            break;
                        }
                    }
                    if (!removed) {
                        System.out.println("The product was not found in the list");
                    } else {
                        products.remove(productFound);
                        System.out.println("The product was successfully removed");
                        System.out.println("Updating the file...");
                        FileOutputStream fos = new FileOutputStream(f, false);
                        PrintWriter pw = new PrintWriter(fos);
                        for (Product product : products) {
                            pw.println(product.toString());
                        }
                        pw.print(after);
                        pw.close();

                    }

                }
                default -> System.out.println("Why is this project so long????????");
            }
        } else {
            System.out.println("Store not found. Try again.");
        }

    }


    // Seller Option 3
    public static void viewSales() throws IOException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please enter a store name.");
        String market = scanner.nextLine(); //Gets the store name from the user.
        FileReader fr = new FileReader(market + " Market.txt");
        BufferedReader br = new BufferedReader(fr);
        int delineate = 0;
        ArrayList<PurchaseInformation> purchaseInformation = new ArrayList<>(); //ArrayList of customers and purchases.
        ArrayList<String[]> purchases = new ArrayList<>(); //ArrayList for each purchase in the store.
        String line = br.readLine();
        double totalRevenue = 0;
        while (line != null) {
            if (line.equals("--------")) {
                delineate++; //Increments delineate if it iterates through the given line.
                line = br.readLine();
            }
            while (delineate == 0 && !line.equals("--------")) {
                line = br.readLine();
            }
            while (delineate == 1 && !line.equals("--------")) {
                purchaseInformation.add(new PurchaseInformation(line, new ArrayList<>(), new ArrayList<>(), new ArrayList<>())); //Adds a new customer.
                line = br.readLine();
            }
            while (delineate == 2 && line != null) {
                String[] purchase = line.split(","); //Creates an array of the purchase.
                purchases.add(purchase); //Adds the purchase.
                line = br.readLine();
            }
        }
        br.close();
        for (PurchaseInformation currentInformation : purchaseInformation) {
            for (String[] currentPurchase : purchases) {
                if (currentInformation.getCustomer().equals(currentPurchase[5])) {
                    ArrayList<String> currentCustomerPurchases = currentInformation.getPurchases();
                    currentCustomerPurchases.add(currentPurchase[0]);
                    currentInformation.setPurchases(currentCustomerPurchases);
                    ArrayList<Integer> currentPurchaseAmount = currentInformation.getAmountPurchased();
                    currentPurchaseAmount.add(Integer.parseInt(currentPurchase[3]));
                    currentInformation.setAmountPurchased(currentPurchaseAmount);
                    ArrayList<Double> currentPrice = currentInformation.getPrice();
                    currentPrice.add(Double.parseDouble(currentPurchase[4]));
                    currentInformation.setPrice(currentPrice);

                }
            }
        } //Creates an arraylist of PurchaseInformation objects with customer, product, amount bought, and price.

        for (int i = 0; i < purchaseInformation.size(); i++) {
            double customerAmountSpent = 0;
            PurchaseInformation currentInformation = purchaseInformation.get(i);
            System.out.printf("%d. ", i + 1);
            System.out.printf("%s bought:\n", currentInformation.getCustomer());
            for (int j = 0; j < currentInformation.getPurchases().size(); j++) {
                double amountSpent = currentInformation.getAmountPurchased().get(j) * currentInformation.getPrice().get(j);
                System.out.printf("%d %s for a total of %.2f\n",currentInformation.getAmountPurchased().get(j), currentInformation.getPurchases().get(j), amountSpent);
                customerAmountSpent = customerAmountSpent + amountSpent;
                totalRevenue = totalRevenue + amountSpent;
            }
            System.out.printf("%s spent %.2f dollars\n", currentInformation.getCustomer(), customerAmountSpent);
        } //Prints the list of product each customer bought and the money spent on each product.
        System.out.printf("The total revenue made from the store is: %.2f\n", totalRevenue);
    } //Prints the total revenue made from the store.


    public static void viewCustomerShoppingCarts() {
        try {
            BufferedReader br = new BufferedReader(new FileReader("Customers.txt"));
            String line = br.readLine();
            ArrayList<String> list = new ArrayList<>();
            while (line != null) {
                list.add(line);
                line = br.readLine();
            }
            for (String user : list) {
                System.out.println("");
                System.out.printf(user + "'s Shopping Cart\n------------------------\n");
                File f = new File(user + "'s File.txt");
                br = new BufferedReader(new FileReader(f));
                line = br.readLine();
                while (line != null) {
                    if (!line.contains("Name: ") && !line.contains("User: ")) {
                        Product product = MarketPlace.getProduct(line);
                        System.out.printf("Product: %s, Store: %s, Description: %s, " +
                                        "Price: %.2f, Quantity: %d\n", product.getName(), product.getStore(),
                                product.getDescription(), product.getPrice(), product.getQuantity());
                    }
                    line = br.readLine();

                }

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.print("\n\n");


    }

    public static void createMarket() {
    }

    public static void deleteMarket() {
    }
}


