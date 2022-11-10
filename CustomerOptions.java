import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class CustomerOptions {
    // Customer Option 1
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

                while ((line = productReader.readLine()) != null) { //iterates through lines of files and adds them to string
                    Product product = MarketPlace.getProduct(line);
                    printer = printer +
                            "Product: " + product.getName() + "\n" +
                            "Description: " + product.getDescription() + "\n" +
                            "Price: " + product.getPrice() + "\n" +
                            "Quantity " + product.getQuantity() + "\n\n";
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
        while (running) {
            System.out.println("What products would you like to search by?");
            System.out.println("1. Product by name");
            System.out.println("2. Product by description");
            System.out.println("3. Product by store");
            try {
                int input = Integer.parseInt(scanner.nextLine());
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
                    printer = printer + market + "\n" + "-------------\n";

                    f = new File(market + " Market.txt");
                    br = new BufferedReader(new FileReader(f));
                    line = br.readLine();
                    lines = new ArrayList<>();
                    while (line != null) {
                        lines.add(line);
                        line = br.readLine();
                    }
                    for (String productInfo : lines) {
                        Product product = MarketPlace.getProduct(productInfo);
                        if (product.getName().contains(search) && input == 1) {
                            printer = printer +
                                    "Product: " + product.getName() + "\n" +
                                    "Description: " + product.getDescription() + "\n" +
                                    "Price: " + product.getPrice() + "\n" +
                                    "Quantity " + product.getQuantity() + "\n\n";
                        } else if (product.getDescription().contains(search) && input == 2) {
                            printer = printer +
                                    "Product: " + product.getName() + "\n" +
                                    "Description: " + product.getDescription() + "\n" +
                                    "Price: " + product.getPrice() + "\n" +
                                    "Quantity " + product.getQuantity() + "\n\n";
                        } else if (product.getStore().contains(search) && input == 3) {
                            printer = printer +
                                    "Product: " + product.getName() + "\n" +
                                    "Description: " + product.getDescription() + "\n" +
                                    "Price: " + product.getPrice() + "\n" +
                                    "Quantity " + product.getQuantity() + "\n\n";
                        }
                    }
                }
                br.close();

            } catch (NumberFormatException e) {
                System.out.println("Wrong format, try again.");
            } catch (IOException e) {
                System.out.println("There was an error. Try again");
            }
        }
        System.out.println(printer);

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
}
