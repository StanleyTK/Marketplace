import org.junit.Test;
import org.junit.After;
import org.junit.Before;


import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;
import java.io.*;
import java.nio.Buffer;
import java.util.ArrayList;
import java.util.Scanner;


import static org.junit.Assert.*;

/**
 * A framework to run public test cases.
 *
 * <p>Purdue University -- CS18000 -- Fall 2022</p>
 *
 * @author Purdue CS
 * @version August 22, 2022
 */
public class RunLocalTest {
    public static void main(String[] args) {
        Result result = JUnitCore.runClasses(TestCase.class);
        System.out.printf("Test Count: %d.\n", result.getRunCount());
        if (result.wasSuccessful()) {
            System.out.println("Excellent - all local tests ran successfully.");
        } else {
            System.out.printf("Tests failed: %d.\n", result.getFailureCount());
            for (Failure failure : result.getFailures()) {
                System.out.println(failure.getMessage());
                System.out.println(failure.getTestHeader());
                System.out.println(failure.getDescription());
                System.out.println(failure);
            }
        }


    }


    public static String getMarketInformation(String market) {
        String toReturn = "";
        ArrayList<Product> lines = null;
        try {
            BufferedReader br = new BufferedReader(new FileReader(market + " Market.txt"));
            String line = br.readLine();
            lines = new ArrayList<>();
            while (line != null) {
                lines.add(MarketPlace.getProduct(line));
                line = br.readLine();
                if (line.contains("-----")) {
                    break;
                }

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        for (int i = 0; i < lines.size(); i++) {
            toReturn += String.format("Product: %s, Description: %s, " +
                            "Price: %.2f, Quantity: %d", lines.get(i).getName(),
                    lines.get(i).getDescription(), lines.get(i).getPrice(), lines.get(i).getQuantity());
            if (i != lines.size() - 1) {
                toReturn += "\n";
            }
        }

        return toReturn;
    }


    public static String getShoppingCart(String name) {
        String toReturn = "";
        ArrayList<Product> lines = null;
        try {
            BufferedReader br = new BufferedReader(new FileReader(name + "'s File.txt"));
            String line = br.readLine();
            lines = new ArrayList<>();
            while (line != null) {
                if (!line.contains("Name: ") && !line.contains("User: ")) {
                    lines.add(MarketPlace.getProduct(line));
                    line = br.readLine();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        for (int i = 0; i < lines.size(); i++) {
            toReturn += String.format("Product: %s, Description: %s, " +
                            "Price: %.2f, Quantity: %d", lines.get(i).getName(),
                    lines.get(i).getDescription(), lines.get(i).getPrice(), lines.get(i).getQuantity());
            if (i != lines.size() - 1) {
                toReturn += "\n";
            }
        }

        return toReturn;
    }

    public static String stores() {
        ArrayList<String> stores = new ArrayList<>();
        String toReturn = "";
        try {
            File f = new File("Markets.txt");
            if (!f.createNewFile()) {
                BufferedReader br = new BufferedReader(new FileReader(f));
                String line = br.readLine();
                while (line != null) {
                    stores.add(line);
                    line = br.readLine();
                }
            } else {
                f.delete();
                throw new IOException();
            }

        } catch (IOException e) {
            return null;

        }
        for (int i = 0; i < stores.size(); i++) {
            toReturn += stores.get(i);
            if (i != stores.size() - 1) {
                toReturn += "\n";
            }
        }
        return toReturn;
    }

    public static String customerMenu() {
        return "What option would you like to choose?\n" +
                "1. View the marketplace\n" +
                "2. Search for specific products by name, description, and store\n" +
                "3. Sort by price least to greatest\n" +
                "4. Sort by quantity least to greatest\n" +
                "5. View Dashboard\n" +
                "6. Export File with Purchase History\n" +
                "7. Add or remove items to the Shopping Cart\n" +
                "8. Purchase all items in the Shopping Cart\n" +
                "9. View my Shopping Cart\n" +
                "10. Exit";
    }


    public static String sellerMenu() {
        return "What option would you like to choose?\n" +
                "1. View the marketplace\n" +
                "2. Create, edit, or delete products from a store\n" +
                "3. View the list of their sales by store\n" +
                "4. View Dashboard\n" +
                "5. Import/Export Products using CSV file\n" +
                "6. View products currently in customer's shopping carts\n" +
                "7. Create a new market\n" +
                "8. Delete a market\n" +
                "9. Exit";
    }

    public static String viewShoppingCart(String name) {
        String toReturn = "";

        try {
            ArrayList<Product> lines = new ArrayList<>();
            BufferedReader br = new BufferedReader(new FileReader(name + "'s File.txt"));
            String line = br.readLine();

            while (line != null) {
                if (!line.contains("User: ") && !line.contains("Name: ")) {
                    Product product = MarketPlace.getProduct(line);
                    String toadd = String.format("Product: %s, Description: %s, " +
                                    "Price: %.2f, Quantity: %d", product.getName(),
                            product.getDescription(), product.getPrice(), product.getQuantity());
                    toReturn += toadd;
                    lines.add(product);

                }
                line = br.readLine();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return toReturn;
    }


    /**
     * A set of public test cases.
     *
     * <p>Purdue University -- CS18000 -- Fall 2022</p>
     *
     * @author Purdue CS
     * @version August 22, 2022
     */
    public static class TestCase {
        private final PrintStream originalOutput = System.out;
        private final InputStream originalSysin = System.in;

        @SuppressWarnings("FieldCanBeLocal")
        private ByteArrayInputStream testIn;

        @SuppressWarnings("FieldCanBeLocal")
        private ByteArrayOutputStream testOut;

        @Before
        public void outputStart() {
            testOut = new ByteArrayOutputStream();
            System.setOut(new PrintStream(testOut));
        }

        @After
        public void restoreInputAndOutput() {
            System.setIn(originalSysin);
            System.setOut(originalOutput);
        }

        private String getOutput() {
            return testOut.toString();
        }

        @SuppressWarnings("SameParameterValue")
        private void receiveInput(String str) {
            testIn = new ByteArrayInputStream(str.getBytes());
            System.setIn(testIn);
        }


        // Tests to see if the login function works
        @Test(timeout = 1000)
        public void testExpectedOne() {
            // Set the input
            String input = "1" + System.lineSeparator() +
                    "testingaccount" + System.lineSeparator() +
                    "test" + System.lineSeparator() +
                    "test" + System.lineSeparator() +
                    "Customer" + System.lineSeparator() +
                    "10" + System.lineSeparator();


            // Pair the input with the expected result
            String expected = "Welcome to Marketplace!" + System.lineSeparator() +
                    "1. Create a new account" + System.lineSeparator() +
                    "2. Log in to your account" + System.lineSeparator() +
                    "Enter the username/email" + System.lineSeparator() +
                    "Enter the password" + System.lineSeparator() +
                    "What is your name?" + System.lineSeparator() +
                    "Are you a Customer or Seller?" + System.lineSeparator() +
                    "Welcome test!" + System.lineSeparator() +
                    customerMenu() + System.lineSeparator() +
                    "Have a nice day!!" + System.lineSeparator();

            // Runs the program with the input values
            receiveInput(input);
            MarketPlace.main(new String[0]);

            // Retrieves the output from the program
            String output = getOutput();
            boolean bol = false;
            boolean bol2 = false;
            boolean bol3 = false;
            try {
                File f = new File("login.txt");
                BufferedReader br = new BufferedReader(new FileReader(f));
                String line = br.readLine();
                while (line != null) {
                    if (line.contains("testing")) {
                        bol = true;
                    }
                    line = br.readLine();
                }


                f = new File("test's File.txt");
                br = new BufferedReader(new FileReader(f));
                line = br.readLine();
                while (line != null) {
                    if (line.contains("Name:")) {
                        bol2 = true;
                    }
                    line = br.readLine();
                }

                f = new File("Customers.txt");
                br = new BufferedReader(new FileReader(f));
                line = br.readLine();
                while (line != null) {
                    if (line.contains("test")) {
                        bol3 = true;
                    }
                    line = br.readLine();
                }
                if (!bol || !bol2 || !bol3) {
                    fail("Files weren't matched");

                }


            } catch (IOException e) {
                fail("No files were found");
            }


            // Trims the output and verifies it is correct.
            expected = expected.replaceAll("\r\n", "\n");
            output = output.replaceAll("\r\n", "\n");
            assertEquals("Test case failed!",
                    expected.trim(), output.trim());
        }

        @Test(timeout = 1000)
        public void testExpectedTwo() {
            // Set the input
            String input = "2" + System.lineSeparator() +
                    "testingaccount" + System.lineSeparator() +
                    "test" + System.lineSeparator() +
                    "10" + System.lineSeparator();


            // Pair the input with the expected result
            String expected = "Welcome to Marketplace!" + System.lineSeparator() +
                    "1. Create a new account" + System.lineSeparator() +
                    "2. Log in to your account" + System.lineSeparator() +
                    "Enter the username/email" + System.lineSeparator() +
                    "Enter the password" + System.lineSeparator() +
                    "Welcome test!" + System.lineSeparator() +
                    customerMenu() + System.lineSeparator() +
                    "Have a nice day!!" + System.lineSeparator();

            // Runs the program with the input values
            receiveInput(input);
            MarketPlace.main(new String[0]);

            // Retrieves the output from the program
            String output = getOutput();


            // Trims the output and verifies it is correct.
            expected = expected.replaceAll("\r\n", "\n");
            output = output.replaceAll("\r\n", "\n");
            assertEquals("Test case failed!",
                    expected.trim(), output.trim());
        }


        @Test(timeout = 1000)
        public void testExpectedThree() {
            // Set the input
            String input = "1" + System.lineSeparator() +
                    "testingaccount2" + System.lineSeparator() +
                    "test2" + System.lineSeparator() +
                    "test2" + System.lineSeparator() +
                    "Seller" + System.lineSeparator() +
                    "9" + System.lineSeparator();


            // Pair the input with the expected result
            String expected = "Welcome to Marketplace!" + System.lineSeparator() +
                    "1. Create a new account" + System.lineSeparator() +
                    "2. Log in to your account" + System.lineSeparator() +
                    "Enter the username/email" + System.lineSeparator() +
                    "Enter the password" + System.lineSeparator() +
                    "What is your name?" + System.lineSeparator() +
                    "Are you a Customer or Seller?" + System.lineSeparator() +
                    "Welcome test2!" + System.lineSeparator() +
                    sellerMenu() + System.lineSeparator() +
                    "Have a nice day!!" + System.lineSeparator();

            // Runs the program with the input values
            receiveInput(input);
            MarketPlace.main(new String[0]);

            // Retrieves the output from the program
            String output = getOutput();
            boolean bol = false;
            boolean bol2 = false;
            try {
                File f = new File("login.txt");
                BufferedReader br = new BufferedReader(new FileReader(f));
                String line = br.readLine();
                while (line != null) {
                    if (line.contains("testingaccount2")){
                        bol = true;
                    }
                    line=br.readLine();
                }


                f = new File("test2's File.txt");
                br = new BufferedReader(new FileReader(f));
                line = br.readLine();
                while (line != null) {
                    if (line.contains("Name:")){
                        bol2 = true;
                    }
                    line=br.readLine();
                }


                if (!bol || !bol2) {
                    fail("Files weren't matched");

                }


            } catch (IOException e) {
                fail("No files were found");
            }


            // Trims the output and verifies it is correct.
            expected = expected.replaceAll("\r\n", "\n");
            output = output.replaceAll("\r\n", "\n");
            assertEquals("Test case failed!",
                    expected.trim(), output.trim());
        }



        @Test(timeout = 1000)
        public void testExpectedFour() {
            // Set the input
            String input = "2" + System.lineSeparator() +
                    "testingaccount2" + System.lineSeparator() +
                    "test2" + System.lineSeparator() +
                    "9" + System.lineSeparator();


            // Pair the input with the expected result
            String expected = "Welcome to Marketplace!" + System.lineSeparator() +
                    "1. Create a new account" + System.lineSeparator() +
                    "2. Log in to your account" + System.lineSeparator() +
                    "Enter the username/email" + System.lineSeparator() +
                    "Enter the password" + System.lineSeparator() +
                    "Welcome test2!" + System.lineSeparator() +
                    sellerMenu() + System.lineSeparator() +
                    "Have a nice day!!" + System.lineSeparator();

            // Runs the program with the input values
            receiveInput(input);
            MarketPlace.main(new String[0]);

            // Retrieves the output from the program
            String output = getOutput();



            // Trims the output and verifies it is correct.
            expected = expected.replaceAll("\r\n", "\n");
            output = output.replaceAll("\r\n", "\n");
            assertEquals("Test case failed!",
                    expected.trim(), output.trim());
        }

        @Test(timeout = 1000)
        public void testExpectedFive() {
            // Set the input
            String input = "Apple" + System.lineSeparator();


            // Pair the input with the expected result
            String expected = "What is the name of the market you want to create?" + System.lineSeparator() +
                    "Apple market has been created." + System.lineSeparator();

            // Runs the program with the input values
            receiveInput(input);
            SellerOptions.createMarket();

            // Retrieves the output from the program
            String output = getOutput();
            try {
                File f = new File("Apple Market.txt");
                BufferedReader br = new BufferedReader(new FileReader(f));
                if (!br.readLine().contains("------")) {
                    fail("File was not created properly");
                }

            } catch (IOException e) {
                fail("File was not created");
            }


            // Trims the output and verifies it is correct.
            expected = expected.replaceAll("\r\n", "\n");
            output = output.replaceAll("\r\n", "\n");
            assertEquals("Test case failed!",
                    expected.trim(), output.trim());
        }



//        @Test(timeout = 1000)
//        public void testExpectedSix() {
//            // Set the input
//            String input = "1" + System.lineSeparator() +
//                    "Apple" + System.lineSeparator() +
//                    "iPhone XS" + System.lineSeparator() +
//                    "You use it" + System.lineSeparator() +
//                    "20" + System.lineSeparator() +
//                    "999.99" + System.lineSeparator();
//
//
//            // Pair the input with the expected result
//            String expected = "Which Option of these would you like to choose?" +System.lineSeparator() +
//                    "1. Create a new product" + System.lineSeparator() +
//                    "2. Edit a product" + System.lineSeparator() +
//                    "3. Delete a product" + System.lineSeparator() +
//                    "Which store would you like to make the changes?" +
//                    "Apple" + System.lineSeparator() +
//                    "What is the Product's new name?" + System.lineSeparator() +
//                    "What is the Product's new description?" + System.lineSeparator() +
//                    "What is the Product's new quantity?" + System.lineSeparator() +
//                    "What is the Product's new price?" + System.lineSeparator() +
//                    "Product is sucessfully created!";
//
//
//
//                    // Runs the program with the input values
//            receiveInput(input);
//            SellerOptions.editProducts(new Scanner(System.in));
//
//            // Retrieves the output from the program
//            String output = getOutput();
//            try {
//                File f = new File("Apple Market.txt");
//                BufferedReader br = new BufferedReader(new FileReader(f));
//                String line = br.readLine();
//                if (!line.contains("iPhone XS")) {
//                    fail();
//                }
//            } catch (IOException e) {
//            }
//
//            // Trims the output and verifies it is correct.
//            expected = expected.replaceAll("\r\n", "\n");
//            output = output.replaceAll("\r\n", "\n");
//            assertEquals("Test case failed!",
//                    expected.trim(), output.trim());
//        }

    }
}