import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;

public class MarketPlace {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome to Marketplace!");
        String info;
        //implement try catch error for verify login
        while (true) {
            try {
                System.out.println("Enter the username");
                String userName = scanner.nextLine();
                System.out.println("Enter the password");
                String password = scanner.nextLine();
                info = verifyLogin(userName, password);
                break;
            } catch (UserNamePasswordIncorrectException e) {
                System.out.println(e.getMessage());
            }
        }
        User user = getUser(info);
        System.out.println("Welcome " + user.getCustomerName() + "!");

        boolean running = true;
        if (user instanceof Customer) {
            while (running) {
                System.out.println("What option would you like to choose?");
                System.out.println("1. View the marketplace\n" +
                        "2. Search for specific products by name, description, and store\n" +
                        "3. Sort by price least to greatest\n" +
                        "4. Sort by quantity least to greatest\n" +
                        "5. Exit");
                int option = Integer.parseInt(scanner.nextLine());
                switch (option) {
                    case (1):
                        CustomerOptions.viewMarket();
                        break;
                    case (2):
                        CustomerOptions.searchForProducts(scanner);
                        break;
                    case (3):
                        CustomerOptions.sortByPrice();
                        break;
                    case (4):
                        CustomerOptions.sortByQuantity();
                        break;
                    case (5):
                        System.out.println("Have a nice day!");
                        running = false;
                        break;
                    default:
                        System.out.println("Please enter a valid input!");

                }
            }


        } else {
            while (running) {

                System.out.println("What option would you like to choose?");
                System.out.println("1. Create, edit, or delete products from a store\n" +
                        "2. Search for specific products by name, description, and store\n" +
                        "3. Exit");
                int option = Integer.parseInt(scanner.nextLine());
                switch (option) {
                    case (1):
                        System.out.println("HEya");
                        break;
                    case (2):
                        System.out.println("asdfa");
                        break;
                    case (3):
                        System.out.println("Have a nice day!");
                        running = false;
                        break;
                    default:
                        System.out.println("Please enter a valid input!");

                }
            }

        }
    }

    // Login function
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
                if (!productInfo.contains("Name:") && !productInfo.contains("User:")) {
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
            return new Seller(contents[2], contents[0], contents[1], shoppingCart.getCartItems());
        }

    }
    // Function to return the Product object from the line in the markets
    public static Product getProduct(String line) {
        String[] contents = line.split(",");
        return new Product(contents[0], contents[1], contents[2],
                Integer.parseInt(contents[3]), Double.parseDouble(contents[4]));
    }




}
