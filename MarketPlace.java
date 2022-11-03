import java.io.*;
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
        } catch (FileNotFoundException e) {
            throw new UserNamePasswordIncorrectException();
        } catch (IOException e) {
            throw new UserNamePasswordIncorrectException();
        }
        throw new UserNamePasswordIncorrectException();
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