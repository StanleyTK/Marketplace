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
    public static void editProducts(Scanner scanner) {
        try {
            int option;
            while (true) {
                try {
                    System.out.print("Which Option of these would you like to choose?\n" +
                            "1. Create a new product\n" +
                            "2. Edit a product\n" +
                            "3. Delete a product\n");
                    option = Integer.parseInt(scanner.nextLine());
                    if (option == 1 || option == 2 || option == 3) {
                        break;
                    }
                    System.out.println("Enter a valid input!");

                } catch (NumberFormatException e) {
                    System.out.println("Enter a valid input");
                }
            }
            String todo = (RunLocalTest.stores());
            if (todo != null) {
                System.out.println("Which store would you like to make the changes?");
                System.out.println(todo);
            } else {
                throw new IOException();
            }
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
                        System.out.println("Product is sucessfully created!");

                    }
                    case (2) -> {
                        printer = printer + storeName + "\n" + "-------------\n";
                        BufferedReader productReader = new BufferedReader(new FileReader(f));
                        ArrayList<Product> products = new ArrayList<>();
                        line = productReader.readLine();
                        while (!line.contains("-----")) { //iterates through lines of files and adds them to string
                            Product product = MarketPlace.getProduct(line);
                            products.add(product);
                            printer = printer +
                                    "Product: " + product.getName() + "\n" +
                                    "Description: " + product.getDescription() + "\n" +
                                    "Price: " + product.getPrice() + "\n" +
                                    "Quantity " + product.getQuantity() + "\n\n";
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
                                System.out.println("What is the Product's new name?");
                                product.setName(scanner.nextLine());
                                System.out.println("What is the Product's new description?");
                                product.setDescription(scanner.nextLine());
                                try {
                                    System.out.println("What is the Product's new quantity?");
                                    product.setQuantity(Integer.parseInt(scanner.nextLine()));
                                } catch (NumberFormatException e) {
                                    System.out.println("You must enter an integer!");
                                    return;
                                }
                                try {
                                    System.out.println("What is the Product's new price?");
                                    product.setPrice(Double.parseDouble(scanner.nextLine()));
                                } catch (NumberFormatException e) {
                                    System.out.println("You must enter a number!");
                                    return;
                                }
                                edited = true;
                                break;
                            }

                        }
                        if (!edited) {
                            System.out.println("The product was not found in the list");
                        } else {
                            System.out.println("The product was successfully edited");
                            FileOutputStream fos = new FileOutputStream(f);
                            PrintWriter pw = new PrintWriter(fos);
                            for (Product product : products) {
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
                        while (!line.contains("-----")) { //iterates through lines of files and adds them to string
                            Product product = MarketPlace.getProduct(line);
                            products.add(product);
                            printer = printer +
                                    "Product: " + product.getName() + "\n" +
                                    "Description: " + product.getDescription() + "\n" +
                                    "Price: " + product.getPrice() + "\n" +
                                    "Quantity " + product.getQuantity() + "\n\n";
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
        } catch (IOException e) {
            System.out.println("There are no stores found. Please add a new store");
        }

    }


    // Seller Option 3
    public static void viewSales() {
        try {
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
                if (line.contains("------")) {
                    delineate++; //Increments delineate if it iterates through the given line.
                    line = br.readLine();
                }
                while (delineate == 0 && !line.contains("------")) {
                    line = br.readLine();
                }
                while (delineate == 1 && !line.contains("------")) {
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
                    System.out.printf("%d %s for a total of %.2f\n", currentInformation.getAmountPurchased().get(j), currentInformation.getPurchases().get(j), amountSpent);
                    customerAmountSpent = customerAmountSpent + amountSpent;
                    totalRevenue = totalRevenue + amountSpent;
                }
                System.out.printf("%s spent %.2f dollars\n", currentInformation.getCustomer(), customerAmountSpent);
            } //Prints the list of product each customer bought and the money spent on each product.
            System.out.printf("The total revenue made from the store is: %.2f\n", totalRevenue);
        } catch (IOException e) {
            System.out.println("There are no sales yet, or the market does not exist");
        } catch (NullPointerException e) {
            System.out.println("There is no purchase history yet, please try again when there are purchases made.");
        }
    }//Prints the total revenue made from the store.


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
            System.out.println("The user is not found, or there is nothing in the shopping cart");
        }
        System.out.print("\n\n");


    }

    public static void deleteMarket() {
        try {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Which market would you like to delete?");
            String market = scanner.nextLine();
            File f = new File("Markets.txt");
            FileReader fr = new FileReader(f);
            BufferedReader bfr = new BufferedReader(fr);
            String line = bfr.readLine();
            ArrayList<String> markets = new ArrayList<String>();
            boolean exists = false;
            while (line != null) {
                markets.add(line);
                if (line.equals(market)) {
                    exists = true;
                }
                line = bfr.readLine();
            }
            bfr.close();
            if (exists) {
                PrintWriter pw = new PrintWriter(new FileOutputStream(f));
                for (int i = 0; i < markets.size(); i++) {
                    if (!markets.get(i).equals(market)) {
                        pw.println(markets.get(i));
                    }
                }
                pw.close();
                pw = new PrintWriter(new FileOutputStream(new File("DeletedMarkets.txt"), true));
                pw.println(market);
                pw.close();
                f = new File("login.txt");
                fr = new FileReader(f);
                bfr = new BufferedReader(fr);
                line = bfr.readLine();
                ArrayList<String> users = new ArrayList<String>();
                while (line != null) {
                    String[] lineSplit = line.split(",");
                    users.add(lineSplit[2]);
                    line = bfr.readLine();
                }
                bfr.close();
                for (int i = 0; i < users.size(); i++) {
                    f = new File(users.get(i) + "'s File.txt");
                    bfr = new BufferedReader(new FileReader(f));
                    String name = bfr.readLine();
                    String customerType = bfr.readLine();
                    if (customerType.equals("User: Customer")) {
                        ArrayList<Product> shoppingCart = new ArrayList<Product>();
                        line = bfr.readLine();
                        while (line != null) {
                            Product product = MarketPlace.getProduct(line);
                            if (!product.getStore().equals(market)) {
                                shoppingCart.add(product);
                            }
                            line = bfr.readLine();
                        }
                        pw = new PrintWriter(new FileOutputStream(f));
                        pw.println(name);
                        pw.println(customerType);
                        for (int j = 0; j < shoppingCart.size(); j++) {
                            pw.println(shoppingCart.get(j).toString());
                        }
                        pw.close();
                        System.out.println("The market has been removed!");
                    }
                    bfr.close();
                }
            } else {
                System.out.println("This market couldn't be found!");
            }
        } catch (IOException e) {
            System.out.println("Sorry, there are currently no markets!");
        }
    }

    public static void createMarket() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("What is the name of the market you want to create?");
        String market = scanner.nextLine();
        try {
            PrintWriter pw = new PrintWriter(new FileWriter("Markets.txt", true));
            pw.println(market);
            pw.flush();
            pw.close();
            FileWriter fw = new FileWriter(market + " Market.txt");
            pw = new PrintWriter(market + " Market.txt");
            pw.println("--------");
            pw.println("--------");
            fw.write("");
            fw.flush();
            pw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(market + " market has been created. If you want to add any products, please restart the program");
    }

}


