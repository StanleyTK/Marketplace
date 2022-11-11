import java.nio.Buffer;
import java.util.ArrayList;
import java.io.*;

public class Seller extends User {

    private ArrayList<String> customerNames = new ArrayList<>();

    // creates a new seller object and seller file
    public Seller(String customerName, String username, String password) {
        super(customerName, username, password);
        File f = new File("Customers.txt");
        String line;
        try {
            BufferedReader br = new BufferedReader(new FileReader(f));
            while ((line = br.readLine()) != null) { //iterates through lines of files and adds them to string

                customerNames.add(line);
            }


        } catch(IOException e) {
            e.printStackTrace();
            System.out.println("There was an error");
        }
    }

    public ArrayList<String> getCustomerNames() {
        return customerNames;
    }

    public void setCustomerNames(ArrayList<String> customerNames) {
        this.customerNames = customerNames;
    }

    @Override
    public String toString() {
        return "Seller{" +
                "customerNames=" + customerNames +
                '}';
    }

//        try {
//            File f = new File(customerName + "'s File.txt");
//            FileOutputStream fos = new FileOutputStream(f);
//            PrintWriter pw = new PrintWriter(fos);
//            pw.println("Name: " + username);
//            pw.println("User: Seller");
//            pw.println();
//
//            pw.close();
//        } catch (IOException e) {
//            e.printStackTrace();
//            System.out.println("There was an error");
//        }

    // retrieve seller info from a file and create seller object
//    public Seller(String name, String username, String password) {
//        super(name, username, password);
//
//        try {
//            File f = new File(name + "'s File.txt");
//            FileReader fr = new FileReader(f);
//            BufferedReader bfr = new BufferedReader(fr);
//            bfr.readLine();
//            bfr.readLine();
//            String[] customerList = bfr.readLine().split(",");
//            ArrayList<String> customers = new ArrayList<String>();
//            for (String s : customerList) {
//                customers.add(s);
//                ;
//            }
//            ArrayList<Product> products = new ArrayList<Product>();
//            String line = bfr.readLine();
//            while (line != null) {
//                Product product = MarketPlace.getProduct(line);
//                products.add(product);
//                line = bfr.readLine();
//            }
//            this.products = products;
//        } catch (IOException e) {
//            e.printStackTrace();
//            System.out.println("There was an error");
//        }
//
}