import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

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
    public static void viewSales() {
        //TODO Sellers can view a list of their sales by store, including customer information and revenues from the sale.
    }


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
}


