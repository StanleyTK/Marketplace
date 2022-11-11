import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Options {
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
            while (true) {
                try {
                    System.out.print("""
                        Which Option of these would you like to choose?
                        1. Create a new product
                        2. Edit a product
                        3. Delete a product
                        """);
                    int option = Integer.parseInt(scanner.nextLine());
                    if (option == 1 || option == 2 || option == 3) {
                        break;
                    }
                    System.out.println("Enter a valid input!");

                } catch (NumberFormatException e) {
                    System.out.println("Enter a valid input");
                }
            }
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














    // Seller Option 2
    public static void editProducts(Scanner scanner) throws IOException {
        int option;
        while (true) {
            try {
                System.out.print("""
                        Which Option of these would you like to choose?
                        1. Create a new product
                        2. Edit a product
                        3. Delete a product
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
                    Product product = new Product(name, storeName, desc, Integer.parseInt(quantity), Double.parseDouble(price));
                    FileOutputStream fos = new FileOutputStream(f, true);
                    PrintWriter pw = new PrintWriter(fos);
                    pw.println("");
                    pw.println(product.toString());
                    pw.close();


                }
                case (2) -> {
                    printer = printer + storeName + "\n" + "-------------\n";
                    BufferedReader productReader = new BufferedReader(new FileReader(f));
                    ArrayList<Product> products = new ArrayList<>();
                    while ((line = productReader.readLine()) != null) { //iterates through lines of files and adds them to string
                        Product product = MarketPlace.getProduct(line);
                        products.add(product);
                        printer = printer +
                                "Product: " + product.getName() + "\n" +
                                "Description: " + product.getDescription() + "\n" +
                                "Price: " + product.getPrice() + "\n" +
                                "Quantity " + product.getQuantity() + "\n\n";
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
                        FileOutputStream fos = new FileOutputStream(f, false);
                        PrintWriter pw = new PrintWriter(fos);
                        for (Product product : products) {
                            System.out.println(product.toString());
                            pw.println(product.toString());
                        }
                        pw.close();
                    }

                }
                case (3) -> {
                    printer = printer + storeName + "\n" + "-------------\n";
                    BufferedReader productReader = new BufferedReader(new FileReader(f));
                    ArrayList<Product> products = new ArrayList<>();
                    while ((line = productReader.readLine()) != null) { //iterates through lines of files and adds them to string
                        Product product = MarketPlace.getProduct(line);
                        products.add(product);
                        printer = printer +
                                "Product: " + product.getName() + "\n" +
                                "Description: " + product.getDescription() + "\n" +
                                "Price: " + product.getPrice() + "\n" +
                                "Quantity " + product.getQuantity() + "\n\n";
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
    public static void viewSales() {


    }






}
