import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class MarketPlace {
    public static void main(String[] args) {
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
                System.out.println("1. Look up a product name, with where the stores sell it\n" +
                        "2. Look up a store\n" +
                        "3. Look up product description\n" +
                        "4. How many of the product is there left in the store\n" +
                        "5. Exit");
                int option = Integer.parseInt(scanner.nextLine());
                switch (option) {
                    case (1):
                        System.out.println("HEya");
                        break;
                    case (2):
                        System.out.println("asdfa");
                        break;
                    case (3):
                        System.out.println("fasd");
                        break;
                    case (4):
                        System.out.println("a");
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
                System.out.println("1. Look up a product name, with where the stores sell it\n" +
                        "2. Look up a store\n" +
                        "3. Look up product description\n" +
                        "4. How many of the product is there left in the store\n" +
                        "5. Exit");
                int option = Integer.parseInt(scanner.nextLine());
                switch (option) {
                    case (1):
                        System.out.println("HEya");
                        break;
                    case (2):
                        System.out.println("asdfa");
                        break;
                    case (3):
                        System.out.println("fasd");
                        break;
                    case (4):
                        System.out.println("a");
                        break;
                    case (5):
                        System.out.println("Have a nice day!");
                        running = false;
                        break;
                    default:
                        System.out.println("Please enter a valid input!");

                }
            }

        }
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
    public static User getUser(String info) throws IOException {
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
            return new Customer(contents[3], contents[0], contents[1]);
        } else {
            return new Seller(contents[3], contents[0], contents[1]);
        }
    }

    public static Product getProduct(String line) {
        String[] contents = line.split(",");
        return new Product(contents[0], contents[1], contents[2],
                Integer.parseInt(contents[3]), Double.parseDouble(contents[4]));
    }
}