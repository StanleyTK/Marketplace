import org.junit.Test;
import org.junit.After;
import org.junit.Before;


import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;
import java.io.*;
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
                    "What option would you like to choose?" + System.lineSeparator() +
                    "1. View the marketplace" + System.lineSeparator() +
                    "2. Search for specific products by name, description, and store" + System.lineSeparator() +
                    "3. Sort by price least to greatest" + System.lineSeparator() +
                    "4. Sort by quantity least to greatest" + System.lineSeparator() +
                    "5. View Dashboard" + System.lineSeparator() +
                    "6. Export File with Purchase History" + System.lineSeparator() +
                    "7. Add or remove items to the Shopping Cart" + System.lineSeparator() +
                    "8. Exit" + System.lineSeparator() +
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
                    "What option would you like to choose?" + System.lineSeparator() +
                    "1. View the marketplace" + System.lineSeparator() +
                    "2. Create, edit, or delete products from a store" + System.lineSeparator() +
                    "3. View the list of their sales by store" + System.lineSeparator() +
                    "4. View Dashboard" + System.lineSeparator() +
                    "5. Import/Export Products using CSV file" + System.lineSeparator() +
                    "6. View products currently in customer's shopping carts" + System.lineSeparator() +
                    "7. Create a new market" + System.lineSeparator() +
                    "8. Delete a market" + System.lineSeparator() +
                    "9. Exit" + System.lineSeparator() +
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


//        @Test(timeout = 1000)
//        public void testExpectedThree() {
//            // Set the input
//            String input = "2" + System.lineSeparator() +
//                    "stanleykim2" + System.lineSeparator() +
//                    "sparkystan" + System.lineSeparator() +
//                    "7" + System.lineSeparator() +
//                    "Walmart" + System.lineSeparator() +
//                    "1" + System.lineSeparator() +
//                    "Keyboard" + System.lineSeparator() +
//                    "3" + System.lineSeparator() +
//                    "7" + System.lineSeparator() +
//                    "Walmart" + System.lineSeparator() +
//                    "2" + System.lineSeparator() +
//                    "Keyboard" + System.lineSeparator() +
//                    "3" + System.lineSeparator() +
//                    "8" + System.lineSeparator();
//
//                            // Pair the input with the expected result
//            String expected = "Welcome to Marketplace!" + System.lineSeparator() +
//                    "1. Create a new account" + System.lineSeparator() +
//                    "2. Log in to your account" + System.lineSeparator() +
//                    "Enter the username/email" + System.lineSeparator() +
//                    "Enter the password" + System.lineSeparator() +
//                    "Welcome Stanley!" + System.lineSeparator();
//
//
//                    // Runs the program with the input values
//            receiveInput(input);
//            MarketPlace.main(new String[0]);
//
//            // Retrieves the output from the program
//            String output = getOutput();
//
//            // Trims the output and verifies it is correct.
//            expected = expected.replaceAll("\r\n","\n");
//            output = output.replaceAll("\r\n","\n");
//            assertEquals("Test case failed!",
//                    expected.trim(), output.trim());
//        }
//
    }
}
