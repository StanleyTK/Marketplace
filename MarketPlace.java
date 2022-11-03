import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class MarketPlace {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Welcome to Marketplace!");
        System.out.println("Enter the username");

        String userName = scanner.nextLine();

        System.out.println("Enter the password");
        String password = scanner.nextLine();
        //implement try catch error for verify login
        String name = verifyLogin(userName, password);
        Product product = new Product("Apples", "Target", "you eat it", 1, 2.99);
        System.out.println(product.toString());


    }

    public static String verifyLogin(String a, String b) {
        return "debug";
    }

    // Assuming no name duplicate
    // Gets the Customer class from name
    public static Customer getCustomer(String name) {
        String fileName = name + "'s File.txt";
        try {
            BufferedReader bfr = new BufferedReader(new FileReader(fileName));
            String line = bfr.readLine();
            ArrayList<String> lines = new ArrayList<>();
            while (line != null) {
                lines.add(line);
                line = bfr.readLine();
            }
            ArrayList<Product> products = new ArrayList<>();


            bfr.close();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return null;

    }
}
