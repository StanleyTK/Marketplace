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
        System.out.println(user.toString());






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
        if (user.equals("Customer")) {
            return new Customer(contents[2], contents[0], contents[1], products);
        } else {
            return new Seller(contents[2], contents[0], contents[1], products);
        }



    }

    public static Product getProduct(String line) {
        String[] contents = line.split(",");
        return new Product(contents[0], contents[1], contents[2],
                Integer.parseInt(contents[3]), Double.parseDouble(contents[4]));
    }
}