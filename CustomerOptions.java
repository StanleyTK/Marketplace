import java.io.*;
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
                while (!line.equals("--------")) { //iterates through lines of files and adds them to string
                    Product product = MarketPlace.getProduct(line);
                    printer = printer +
                            "Product: " + product.getName() + "\n" +
                            "Description: " + product.getDescription() + "\n" +
                            "Price: " + product.getPrice() + "\n" +
                            "Quantity " + product.getQuantity() + "\n\n";
                    line = productReader.readLine();
                }

            }
        } catch (IOException e) {
            System.out.println("There was an error");
        }
        System.out.println(printer);

    }

    // Customer Option 2
    public static void searchForProducts(Scanner scanner) {
        ArrayList<Product> toReturn = new ArrayList<>();
        boolean running = true;
        String printer = "";
        int option;
        boolean searchResult = false;
        while (running) {
            while (true) {
                try {
                    System.out.print("""
                            Which Option of these would you like to choose?
                            1. Search by name
                            2. Search by description
                            3. Search by store
                            """);
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
                    String results = "";
                    f = new File(market + " Market.txt");
                    br = new BufferedReader(new FileReader(f));
                    line = br.readLine();
                    lines = new ArrayList<>();
                    while (!line.equals("--------")) {
                        lines.add(line);
                        line = br.readLine();
                    }
                    for (String productInfo : lines) {
                        Product product = MarketPlace.getProduct(productInfo);
                        if (product.getName().contains(search) && option == 1) {
                            results +=
                                    "Product: " + product.getName() + "\n" +
                                    "Description: " + product.getDescription() + "\n" +
                                    "Price: " + product.getPrice() + "\n" +
                                    "Quantity " + product.getQuantity() + "\n\n";
                            found = true;
                            searchResult = true;
                        } else if (product.getDescription().contains(search) && option == 2) {
                            results +=
                                    "Product: " + product.getName() + "\n" +
                                    "Description: " + product.getDescription() + "\n" +
                                    "Price: " + product.getPrice() + "\n" +
                                    "Quantity " + product.getQuantity() + "\n\n";
                            found = true;
                            searchResult = true;
                        } else if (product.getStore().contains(search) && option == 3) {
                            results +=
                                    "Product: " + product.getName() + "\n" +
                                    "Description: " + product.getDescription() + "\n" +
                                    "Price: " + product.getPrice() + "\n" +
                                    "Quantity " + product.getQuantity() + "\n\n";
                            found = true;
                            searchResult = true;
                        }
                    }
                    if (found) {
                        printer += market + "\n" + "-------------\n" + results;
                    }
                }
                br.close();

            } catch (NumberFormatException e) {
                System.out.println("Wrong format, try again.");
            } catch (IOException e) {
                System.out.println("There was an error. Try again");
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

                while ((line = productReader.readLine()) != null) { //iterates through lines of files and adds them to string
                    products.add(MarketPlace.getProduct(line));
                }
            }
        } catch (IOException e) {
            System.out.println("There was an error");
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
            System.out.printf(i + ". Product: %s, Store: %s, Description: %s, " +
                            "Price: %.2f, Quantity: %d\n\n", temp[i].getName(), temp[i].getStore(),
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

                while ((line = productReader.readLine()) != null) { //iterates through lines of files and adds them to string
                    products.add(MarketPlace.getProduct(line));
                }
            }
        } catch (IOException e) {
            System.out.println("There was an error");
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
            System.out.printf(i + ". Product: %s, Store: %s, Description: %s, " +
                            "Price: %.2f, Quantity: %d\n\n", temp[i].getName(), temp[i].getStore(),
                    temp[i].getDescription(), temp[i].getPrice(), temp[i].getQuantity());
        }
    }


    public static void addProductsShoppingCart() {
        //TODO Customers can add products from different stores to a shopping cart to purchase all at once,
        // and can remove any product if they choose to do so. The shopping cart is preserved between sessions,
        // so a customer may choose to sign out and return to make the purchase later.
    }
}