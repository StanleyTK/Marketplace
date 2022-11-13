import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class MarketPlace {
    public static void main(String[] args) {
        try {
            Scanner scanner = new Scanner(System.in);
            String password;
            String name;
            System.out.println("Welcome to Marketplace!");
            String info = "";
            //implement try catch error for verify login
            System.out.println("1. Create a new account");
            System.out.println("2. Log in to your account");
            String op = scanner.nextLine();
            String userName = "";
            User user = null;
            if (op.equals("2")) {
                while (true) {
                    try {
                        System.out.println("Enter the username/email");
                        userName = scanner.nextLine();
                        System.out.println("Enter the password");
                        password = scanner.nextLine();
                        info = verifyLogin(userName, password);
                        user = getUser(info);

                        break;
                    } catch (UserNamePasswordIncorrectException e) {
                        System.out.println(e.getMessage());
                    }
                }

            } else {
                while (true) {
                    System.out.println("Enter the username/email");
                    userName = scanner.nextLine();
                    System.out.println("Enter the password");
                    password = scanner.nextLine();
                    System.out.println("What is your name?");
                    name = scanner.nextLine();
                    if (!checkAccount(userName)) {
                        System.out.println("Username/email is already taken");
                        System.out.println("Please try again");
                    } else {
                        break;
                    }
                }
                String yolo;
                while (true) {
                    System.out.println("Are you a Customer or Seller?");
                    yolo = scanner.nextLine();
                    if (yolo.equals("Customer") || yolo.equals("Seller")) {
                        break;
                    }
                    System.out.println("Enter a valid input");
                }
                user = createUser(userName, password, yolo, name);

            }
            System.out.println("Welcome " + user.getCustomerName() + "!");

            boolean running = true;
            if (user instanceof Customer) {
                while (running) {
                    System.out.println("What option would you like to choose?");
                    System.out.println("1. View the marketplace\n" +
                                       "2. Search for specific products by name, description, and store\n" +
                                       "3. Sort by price least to greatest\n" +
                                       "4. Sort by quantity least to greatest\n" +
                                       "5. View Dashboard\n" +
                                       "6. Export File with Purchase History\n" +
                                       "7. Add items to the Shopping Cart\n" +
                                       "8. Exit");
                    int option = Integer.parseInt(scanner.nextLine());
                    switch (option) {
                        case (1) -> CustomerOptions.viewMarket();
                        case (2) -> CustomerOptions.searchForProducts(scanner);
                        case (3) -> CustomerOptions.sortByPrice();
                        case (4) -> CustomerOptions.sortByQuantity();
                        case (5) -> Dashboard.viewCustomer(); //TODO
                        case (6) -> Dashboard.exportPurchaseHistory((Customer) user);
                        case (7) -> CustomerOptions.addOrRemoveProductsShoppingCart(scanner, user.getCustomerName()); //TODO
                        case (8) -> {
                            running = false;
                        }
                        default -> System.out.println("Please enter a valid input!");

                    }
                }


            } else {
                while (running) {

                    System.out.println("What option would you like to choose?");
                    System.out.println("1. View the marketplace\n" +
                                       "2. Create, edit, or delete products from a store\n" +
                                       "3. View the list of their sales by store\n" +
                                       "4. View Dashboard\n" +
                                       "5. Import/Export Products using CSV file\n" +
                                       "6. View products currently in customer's shopping carts\n" +
                                       "7. Create a new market\n" +
                                       "8. Delete a market\n" +
                                       "9. Exit");
                    int option = Integer.parseInt(scanner.nextLine());
                    switch (option) {
                        case (1) -> CustomerOptions.viewMarket();
                        case (2) -> SellerOptions.editProducts(scanner);
                        case (3) -> SellerOptions.viewSales();//TODO
                        case (4) -> Dashboard.viewSeller(); //TODO
                        case (5) -> Dashboard.csvFile();
                        case (6) -> SellerOptions.viewCustomerShoppingCarts(); //TODO
                        case (7) -> SellerOptions.createMarket();
                        case (8) -> SellerOptions.deleteMarket();
                        case (9) -> running = false;
                        default -> System.out.println("Please enter a valid input!");


                    }
                }

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Have a nice day!!");
    }

    public static String verifyLogin(String username, String password) throws UserNamePasswordIncorrectException {
        File f = new File("login.txt");
        FileReader fr = null;
        BufferedReader bfr = null;
        try {
            fr = new FileReader(f);
            bfr = new BufferedReader(fr);
            String line = bfr.readLine();
            while (line != null) {
                String[] contents = line.split(",");
                if (contents[0].equals(username) && contents[1].equals(password)) {
                    bfr.close();
                    return line;
                }
                line = bfr.readLine();
            }
            bfr.close();
        } catch (IOException e) {
            throw new UserNamePasswordIncorrectException();
        }
        throw new UserNamePasswordIncorrectException();
    }

    // Assuming no name duplicate
    // Gets the Customer class from name
    public static User getUser(String info) {
        String[] contents = info.split(",");
        ArrayList<Product> products = new ArrayList<>();
        String user = "Customer";

        String fileName = contents[2] + "'s File.txt";
        try {
            BufferedReader bfr = new BufferedReader(new FileReader(fileName));
            String line = bfr.readLine();
            ArrayList<String> lines = new ArrayList<>();
            while (line != null) {
                lines.add(line);
                line = bfr.readLine();
            }

            for (String productInfo : lines) {
                if (!productInfo.contains("Name:") && !productInfo.contains("User:") && productInfo.contains("User: Seller")) {
                    products.add(getProduct(productInfo));
                } else if (productInfo.contains("User: Seller")) {
                    user = "Seller";
                }
            }
            bfr.close();


        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        ShoppingCart shoppingCart = new ShoppingCart(products);

        if (user.equals("Customer")) {
            return new Customer(contents[2], contents[0], contents[1], shoppingCart.getCartItems());
        } else {
            return new Seller(contents[2], contents[0], contents[1]);
        }



    }

    public static Product getProduct(String line) {
        String[] contents = line.split(",");
        return new Product(contents[0], contents[1], contents[2],
                Integer.parseInt(contents[3]), Double.parseDouble(contents[4]));
    }

    public static boolean checkAccount(String userName) {
        try {
            BufferedReader br = new BufferedReader(new FileReader("login.txt"));
            String line = br.readLine();
            while (line != null) {
                String[] info = line.split(",");
                if (info[0].equals(userName)) return false;
                line = br.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return true;

    }

    public static User createUser(String username, String password, String option, String name) {
        try {

            PrintWriter pw = new PrintWriter(new FileOutputStream("login.txt", true));
            pw.println("");
            pw.println(username + "," + password + "," + name);
            pw.close();

            File f = new File(name + "'s File.txt");
            pw = new PrintWriter(new FileOutputStream(f, true));
            pw.println(("Name: " + name));
            pw.println("User: " + option);
            pw.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

        if (option.equals("Customer")) {
            return new Customer(name, username, password);
        } else {
            return new Seller(name, username, password);
        }

    }
}