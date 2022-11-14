import org.junit.Test;
import org.junit.After;
import org.junit.Before;


import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;
import java.io.*;
import java.nio.Buffer;
import java.util.ArrayList;


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
            System.out.printf("Tests failed: %d.\n",result.getFailureCount());
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
            BufferedReader br = new BufferedReader(new FileReader("Markets.txt"));
            String line = br.readLine();
            while (line != null) {
                stores.add(line);
                line = br.readLine();
            }

        } catch (IOException e) {
            e.printStackTrace();

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
                "9. Exit";
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
            String input = "2" + System.lineSeparator() +
                    "stanleykim2" + System.lineSeparator() +
                    "sparkystan" + System.lineSeparator() +
                    "8" + System.lineSeparator();

            // Pair the input with the expected result
            String expected = "Welcome to Marketplace!" + System.lineSeparator() +
                    "1. Create a new account" + System.lineSeparator() +
                    "2. Log in to your account" + System.lineSeparator() +
                    "Enter the username/email" + System.lineSeparator() +
                    "Enter the password" + System.lineSeparator() +
                    "Welcome Stanley!" + System.lineSeparator() +
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


        // Tests to create a new file
        @Test(timeout = 1000)
        public void testExpectedTwo() {
            // Set the input
            String input = "1" + System.lineSeparator() +
                    "testaccount747" + System.lineSeparator() +
                    "project4" + System.lineSeparator() +
                    "Stan" + System.lineSeparator() +
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
                    "Welcome Stan!" + System.lineSeparator() +
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

            //Reset the files for test case 2
            File f = new File("Stan's File.txt");
            f.delete();
            try {
                f = new File("login.txt");
                BufferedReader br = new BufferedReader(new FileReader("login.txt"));
                String line = br.readLine();
                ArrayList<String> lines = new ArrayList<>();
                while (line != null) {
                    lines.add(line);
                    line = br.readLine();
                }
                br.close();
                PrintWriter pw = new PrintWriter(new FileOutputStream(f, false));
                boolean contain = false;
                for (String x : lines) {
                    if (!x.contains("testaccount747,project4,Stan")) {
                        pw.println(x);
                    } else {
                        contain = true;
                    }
                }
                pw.close();
                if (!contain) {
                    fail("Login in file fail");
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            assertEquals("Test case failed!",
                    expected.trim(), output.trim());
        }


        @Test(timeout = 3000)
        public void testExpectedThree() {

            String input = "2" + System.lineSeparator() +
                    "stanleykim2" + System.lineSeparator() +
                    "sparkystan" + System.lineSeparator() +
                    "7" + System.lineSeparator() +
                    "Target" + System.lineSeparator() +
                    "1" + System.lineSeparator() +
                    "Keyboard" + System.lineSeparator() +
                    "1" + System.lineSeparator() +
                    "9" + System.lineSeparator();

                            // Pair the input with the expected result
            String expected = "Welcome to Marketplace!" + System.lineSeparator() +
                    "1. Create a new account" + System.lineSeparator() +
                    "2. Log in to your account" + System.lineSeparator() +
                    "Enter the username/email" + System.lineSeparator() +
                    "Enter the password" + System.lineSeparator() +
                    "Welcome Stanley!" + System.lineSeparator() +
                    customerMenu() + System.lineSeparator() +
                    "What store would you like to choose to add or remove new products?" + System.lineSeparator() +
                    stores() + System.lineSeparator() +
                    "Which Option of these would you like to choose?\n" +
                    "1. Add a product to your shopping cart\n" +
                    "2. Remove a product to your shopping cart" + System.lineSeparator() +
                    getMarketInformation("Target") + System.lineSeparator() +
                    "Which item would you like to buy" + System.lineSeparator() +
                    "Updating files..." + System.lineSeparator() +
                    "Success!!" + System.lineSeparator() +
                    customerMenu() + System.lineSeparator() +
                    "Have a nice day!!";


                    // Runs the program with the input values
            receiveInput(input);
            MarketPlace.main(new String[0]);

            // Retrieves the output from the program
            String output = getOutput();

            // Trims the output and verifies it is correct.
            expected = expected.replaceAll("\r\n","\n");
            output = output.replaceAll("\r\n","\n");
            assertEquals("Test case failed!",
                    expected.trim(), output.trim());
        }

    }
}
