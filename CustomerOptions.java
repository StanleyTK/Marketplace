import java.io.*;
import java.nio.Buffer;
import java.util.ArrayList;
import java.util.Scanner;

public class CustomerOptions {
    // Customer Option 1 and Seller Option 1
    public static void viewMarket() { //prints out marketplace to user
        String line;
        String printer = "";

        ArrayList<String> storeNames = new ArrayList<>();
        try {
            File markets = new File("Markets.txt");
            BufferedReader bfr = new BufferedReader(new FileReader(markets));
            while ((line = bfr.readLine()) != null) { //Takes name of all markets in file
                storeNames.add(line); //adds to arraylist
            }

            bfr.close();
            for (String storeName : storeNames) {
                printer = printer + storeName + "\n" + "-------------\n";
                File f = new File(storeName + " Market.txt");
                BufferedReader productReader = new BufferedReader(new FileReader(f));
                line = productReader.readLine();
                while (line != null) { //iterates through lines of files and adds them to string
                    if (!line.contains("-----")) {
                        Product product = MarketPlace.getProduct(line);
                        printer = printer +
                                "Product: " + product.getName() + "\n" +
                                "Description: " + product.getDescription() + "\n" +
                                "Price: " + product.getPrice() + "\n" +
                                "Quantity " + product.getQuantity() + "\n\n";
                        line = productReader.readLine();
                    }
                }

            }
        } catch (IOException e) {
            System.out.println("There are no stores/products found. Sorry");
        }
        System.out.println(printer);

    }

    // Customer Option 2
    public static void searchForProducts(Scanner scanner) {
        boolean running = true;
        StringBuilder printer = new StringBuilder();
        int option;
        boolean searchResult = false;
        while (running) {
            while (true) {
                try {
                    System.out.println("Which Option of these would you like to choose?\n" +
                            "1. Search by name\n" +
                            "2. Search by description\n" +
                            "3. Search by store");
                    option = Integer.parseInt(scanner.nextLine());
                    if (option == 1 || option == 2 || option == 3) {
                        break;
                    }
                    System.out.println("Enter a valid input!");

                } catch (NumberFormatException e) {
                    System.out.println("Enter a valid input");
                }
            }
            try {
                System.out.println("Enter your text for search");
                String search = scanner.nextLine();
                running = false;
                File f = new File("Markets.txt");
                BufferedReader br = new BufferedReader(new FileReader(f));
                String line = br.readLine();
                ArrayList<String> lines = new ArrayList<>();
                while (line != null) {
                    lines.add(line);
                    line = br.readLine();
                }

                for (String market : lines) {
                    boolean found = false;
                    StringBuilder results = new StringBuilder();
                    f = new File(market + " Market.txt");
                    br = new BufferedReader(new FileReader(f));
                    line = br.readLine();
                    lines = new ArrayList<>();
                    while (line != null) {
                        if (!line.contains("------")) {
                            lines.add(line);
                            line = br.readLine();
                        } else {
                            break;
                        }
                    }
                    for (String productInfo : lines) {
                        Product product = MarketPlace.getProduct(productInfo);
                        if (product.getName().contains(search) && option == 1) {
                            results.append("Product: ").append(product.getName()).append("\n").append("Description: ").append(product.getDescription()).append("\n").append("Price: ").append(product.getPrice()).append("\n").append("Quantity ").append(product.getQuantity()).append("\n\n");
                            found = true;
                            searchResult = true;
                        } else if (product.getDescription().contains(search) && option == 2) {
                            results.append("Product: ").append(product.getName()).append("\n").append("Description: ").append(product.getDescription()).append("\n").append("Price: ").append(product.getPrice()).append("\n").append("Quantity ").append(product.getQuantity()).append("\n\n");
                            found = true;
                            searchResult = true;
                        } else if (product.getStore().contains(search) && option == 3) {
                            results.append("Product: ").append(product.getName()).append("\n").append("Description: ").append(product.getDescription()).append("\n").append("Price: ").append(product.getPrice()).append("\n").append("Quantity ").append(product.getQuantity()).append("\n\n");
                            found = true;
                            searchResult = true;
                        }
                    }
                    if (found) {
                        printer.append(market).append("\n").append("-------------\n").append(results);
                    }
                }
                br.close();

            } catch (NumberFormatException e) {
                System.out.println("Wrong format, try again.");
            } catch (IOException e) {
                System.out.println("There are no stores/products found. Sorry");
            }
        }
        if (searchResult) {
            System.out.print(printer);
        } else {
            System.out.println("No results found!");
        }
    }

    // Customer Option 3
    public static void sortByPrice() {
        String line;
        ArrayList<Product> products = new ArrayList<>();
        ArrayList<String> storeNames = new ArrayList<>();
        try {
            File markets = new File("Markets.txt");
            BufferedReader bfr = new BufferedReader(new FileReader(markets));
            while ((line = bfr.readLine()) != null) { //Takes name of all markets in file
                storeNames.add(line); //adds to arraylist
            }

            bfr.close();
            for (String storeName : storeNames) {
                File f = new File(storeName + " Market.txt");
                BufferedReader productReader = new BufferedReader(new FileReader(f));
                line = productReader.readLine();
                while (line != null) { //iterates through lines of files and adds them to string
                    if (!line.contains("------")) {
                        products.add(MarketPlace.getProduct(line));
                        line = productReader.readLine();
                    } else {
                        break;
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("There are no stores/products found. Sorry");

        }

        Product[] temp = new Product[products.size()];
        for (int i = 0; i < temp.length; i++) {
            temp[i] = products.get(i);
        }

        for (int i = 0; i < temp.length; i++) {
            for (int j = temp.length - 1; j > i; j--) {
                if (temp[i].getPrice() > temp[j].getPrice()) {

                    double tempI = temp[i].getPrice();
                    double tempJ = temp[j].getPrice();
                    if (tempI > tempJ) {
                        Product yolo = temp[i];
                        temp[i] = temp[j];
                        temp[j] = yolo;
                    }

                }

            }
        }
        for (int i = 0; i < temp.length; i++) {
            System.out.printf((i + 1) + ". Product: %s, Store: %s, Description: %s, " +
                            "Price: %.2f, Quantity: %d\n", temp[i].getName(), temp[i].getStore(),
                    temp[i].getDescription(), temp[i].getPrice(), temp[i].getQuantity());
        }
    }


    // Customer Option 4
    public static void sortByQuantity() {
        String line;
        ArrayList<Product> products = new ArrayList<>();
        ArrayList<String> storeNames = new ArrayList<>();
        try {
            File markets = new File("Markets.txt");
            BufferedReader bfr = new BufferedReader(new FileReader(markets));
            while ((line = bfr.readLine()) != null) { //Takes name of all markets in file
                storeNames.add(line); //adds to arraylist
            }

            bfr.close();
            for (String storeName : storeNames) {
                File f = new File(storeName + " Market.txt");
                BufferedReader productReader = new BufferedReader(new FileReader(f));
                line = productReader.readLine();
                while (line != null) { //iterates through lines of files and adds them to string
                    if (!line.contains("----")) {
                        products.add(MarketPlace.getProduct(line));
                        line = productReader.readLine();
                    } else {
                        break;
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("There are no stores/products found. Sorry");
        }

        Product[] temp = new Product[products.size()];
        for (int i = 0; i < temp.length; i++) {
            temp[i] = products.get(i);
        }
        for (int i = 0; i < temp.length; i++) {
            for (int j = temp.length - 1; j > i; j--) {
                if (temp[i].getQuantity() > temp[j].getQuantity()) {

                    int tempI = temp[i].getQuantity();
                    int tempJ = temp[j].getQuantity();
                    if (tempI > tempJ) {
                        Product yolo = temp[i];
                        temp[i] = temp[j];
                        temp[j] = yolo;
                    }

                }

            }
        }
        for (int i = 0; i < temp.length; i++) {
            System.out.printf((i + 1) + ". Product: %s, Store: %s, Description: %s, " +
                            "Price: %.2f, Quantity: %d\n", temp[i].getName(), temp[i].getStore(),
                    temp[i].getDescription(), temp[i].getPrice(), temp[i].getQuantity());
        }
    }


    public static void addOrRemoveProductsShoppingCart(Scanner scanner, String customerName) {
        String input = null;
        int option;
        boolean run = true;
        try {
            ArrayList<Product> userProducts = new ArrayList<>();
            BufferedReader br = new BufferedReader(new FileReader(customerName + "'s File.txt"));
            String line;

            while ((line = br.readLine()) != null) { //Takes name of all markets in file
                if (!line.contains("Name:") && !line.contains("User: ")) {
                    Product product = MarketPlace.getProduct(line);
                    userProducts.add(product);

                }
            }
            br = new BufferedReader(new FileReader("Markets.txt"));
            System.out.println("What store would you like to choose to add or remove new products?");

            while ((line = br.readLine()) != null) { //Takes name of all markets in file
                System.out.println(line);
            }

            input = scanner.nextLine();
            br = new BufferedReader(new FileReader(input + " Market.txt"));
            ArrayList<Product> productsFromStore = new ArrayList<>();
            while ((line = br.readLine()) != null) { //Takes name of all markets in file

                if (!line.contains("--------")) {
                    Product product = MarketPlace.getProduct(line);
                    productsFromStore.add(product);
                } else {
                    break;
                }
            }
            ArrayList<String> marketExtra = new ArrayList<>();
            marketExtra.add("----------");
            line = br.readLine();
            while (line != null) {
                marketExtra.add(line);
                line = br.readLine();

            }

            System.out.print("Which Option of these would you like to choose?\n" +
                    "1. Add a product to your shopping cart\n" +
                    "2. Remove a product to your shopping cart\n");
            option = Integer.parseInt(scanner.nextLine());
            boolean bol = false;
            Product prodToRemove = null;

            // Add product
            if (option == 1) {
                // Add product to existing product
                for (Product product : productsFromStore) {
                    System.out.printf("Product: %s, Description: %s, " +
                                    "Price: %.2f, Quantity: %d\n", product.getName(),
                            product.getDescription(), product.getPrice(), product.getQuantity());
                }
                System.out.println("Which item would you like to buy?");
                String item = scanner.nextLine();
                System.out.printf("How many %s would you like to buy?\n", item);
                int quantity = Integer.parseInt(scanner.nextLine());
                boolean isInShoppingCart = false;
                for (Product userProduct : userProducts) {
                    if (userProduct.getName().equals(item)) {
                        isInShoppingCart = true;
                        break;
                    }
                }
                // If the item is already in the shopping cart
                if (isInShoppingCart) {
                    for (Product product : userProducts) {
                        if (product.getName().equals(item)) {
                            product.setQuantity(product.getQuantity() + quantity);
                        }
                    }
                    for (int i = 0; i < productsFromStore.size(); i++) {
                        if (productsFromStore.get(i).getName().equals(item)) {
                            if (!(productsFromStore.get(i).getQuantity() <= quantity)) {
                                productsFromStore.get(i).setQuantity(productsFromStore.get(i).getQuantity() - quantity);
                            } else {
                                productsFromStore.remove(productsFromStore.get(i));
                            }
                        }
                    }
                // If the product is not in the shopping cart
                } else {
                    for (Product value : productsFromStore) {
                        if (item.equals(value.getName())) {
                            if (quantity >= value.getQuantity()) {
                                userProducts.add(value);
                                bol = true;
                                prodToRemove = value;
                            } else {
                                value.setQuantity(value.getQuantity() - quantity);
                                Product product = new Product(value.getName(), value.getStore(),
                                        value.getDescription(), quantity, value.getPrice());
                                userProducts.add(product);

                            }
                        }
                    }
                    if (bol) {
                        productsFromStore.remove(prodToRemove);
                    }
                }
            } else {
                System.out.println("Which product name would you like to remove from your shopping cart?");
                for (Product product : userProducts) {
                    System.out.printf("Product: %s, Description: %s, " +
                                    "Price: %.2f, Quantity: %d\n", product.getName(),
                            product.getDescription(), product.getPrice(), product.getQuantity());
                }
                String answer = scanner.nextLine();
                int quantity = 0;

                for (int i = 0; i < userProducts.size(); i++) {
                    if (answer.equals(userProducts.get(i).getName())) {
                        while (run) {
                            System.out.println("How many items would you like to remove?");
                            quantity = Integer.parseInt(scanner.nextLine());
                            if (quantity >= userProducts.get(i).getQuantity()) {
                                System.out.printf("%s is removed from your shopping cart\n", answer);
                                for (Product product : productsFromStore) {
                                    if (product.getName().equals(answer)) {
                                        product.setQuantity(userProducts.get(i).getQuantity() + product.getQuantity());
                                    }
                                }
                                userProducts.remove(userProducts.get(i));

                                run = false;
                                break;
                            } else if (quantity < 0) {
                                System.out.println("The quantity removed cannot be less than 0");
                            } else {
                                userProducts.get(i).setQuantity(userProducts.get(i).getQuantity() - quantity);
                                run = false;
                                break;
                            }
                        }
                    }
                }
            }
            File f = new File(customerName + "'s File.txt");
            FileOutputStream fos = new FileOutputStream(f, false);
            PrintWriter pw = new PrintWriter(fos);
            pw.println("Name: " + customerName);
            pw.println("User: Customer");
            for (Product product : userProducts) {
                pw.println(product.toString());
            }
            pw.close();

            f = new File(input + " Market.txt");
            fos = new FileOutputStream(f, false);
            pw = new PrintWriter(fos);
            for (Product product : productsFromStore) {
                pw.println(product.toString());
            }
            for (String x : marketExtra) {
                pw.println(x);
            }
            pw.close();
            System.out.println("Updating files...\nSuccess!!!");

        } catch (IOException e) {
            System.out.println("There are no stores/products found. Sorry");
        }
    }

    public static void buyShoppingCart(Customer customer) {
        try {
            File f = new File(customer.getCustomerName() + "'s File.txt");
            BufferedReader bfr = new BufferedReader(new FileReader(f));
            String name = bfr.readLine();
            String userType = bfr.readLine();
            ArrayList<Product> products = new ArrayList<Product>();
            String line = bfr.readLine();
            while (line != null) {
                products.add(MarketPlace.getProduct(line));
                line = bfr.readLine();
            }
            bfr.close();
            PrintWriter pw = new PrintWriter(new FileOutputStream(f));
            pw.println(name);
            pw.println(userType);
            pw.close();
            double total = 0;
            for (Product product : products) {
                String market = product.getStore();
                f = new File(market + " Market.txt");
                FileOutputStream fos = new FileOutputStream(f, true);
                pw = new PrintWriter(fos);
                pw.println(product.toString() + "," + customer.getCustomerName());
                double cost = product.getPrice() * product.getQuantity();
                total += cost;
                System.out.printf("Purchased %d %s for %.2f total\n",
                        product.getQuantity(), product.getName(), cost);
                pw.close();
                bfr = new BufferedReader(new FileReader(f));
                line = bfr.readLine();
                String before = "";
                boolean isCustomer = false;
                while (!line.contains("------")) {
                    before += line + "\n";
                    line = bfr.readLine();
                }
                before += "--------\n";
                line = bfr.readLine();
                ArrayList<String> customers = new ArrayList<String>();
                while (!line.contains("------")) {
                    customers.add(line);
                    if (line.equals(customer.getCustomerName())) {
                        isCustomer = true;
                    }
                    line = bfr.readLine();
                }
                if (!isCustomer) {
                    customers.add(customer.getCustomerName());
                }
                String after = line;
                line = bfr.readLine();
                while (line != null) {
                    after += "\n" + line;
                    line = bfr.readLine();
                }
                bfr.close();
                pw = new PrintWriter(new FileOutputStream(f));
                pw.print(before);
                for (int i = 0; i < customers.size(); i++) {
                    pw.println(customers.get(i));
                }
                pw.println(after);
                pw.close();
            }
            System.out.printf("Shopping cart purchased! Total cost: %.2f\n", total);
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("An unexpected error occurred while accessing files!");
        }
    }

    public static void viewShoppingCart(Customer user) {
        try {
            ArrayList<Product> lines = new ArrayList<>();
            BufferedReader br = new BufferedReader(new FileReader(user.getCustomerName() + "'s File.txt"));
            String line = br.readLine();

            while (line != null) {
                if (!line.contains("User: ") && !line.contains("Name: ")) {
                    Product product = MarketPlace.getProduct(line);
                    System.out.printf("Product: %s, Description: %s, " +
                                    "Price: %.2f, Quantity: %d\n", product.getName(),
                            product.getDescription(), product.getPrice(), product.getQuantity());
                    lines.add(product);

                }
                line = br.readLine();

            }
            if (lines.size() == 0) {
                System.out.println("You do not have any products in your shopping cart.");
            }
        } catch (IOException e) {
            System.out.println("There are no stores/products found. Sorry");
        }
    }
}