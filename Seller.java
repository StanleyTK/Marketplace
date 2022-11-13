import java.util.ArrayList;
import java.io.*;
public class Seller extends User {
    private ArrayList<String> customerNames = new ArrayList<>();

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
    public void setProducts(ArrayList<Product> products) throws IOException {
        for (int i = 0; i < products.size(); i++) {
            String market = products.get(i).getStore();
            File f = new File(market + " Market.txt");
            FileReader fr = new FileReader(f);
            BufferedReader bfr = new BufferedReader(fr);
            String line = bfr.readLine();
            String contents = "";
            while (line != null) {
                contents += line;
                line = bfr.readLine();
            }
            FileOutputStream fos = new FileOutputStream(f);
            PrintWriter pw = new PrintWriter(fos);
            pw.println(contents);
            pw.println(products.get(i));
            bfr.close();
            pw.close();
        }
    }

//    public ArrayList<Product> getProducts() {
//        return products;
//    }
//
//    public void setProducts(ArrayList<Product> products) throws IOException {
//        for (int i = 0; i < products.size(); i++) {
//            String market = products.get(i).getStore();
//            File f = new File(market + " Market.txt");
//            FileReader fr = new FileReader(f);
//            BufferedReader bfr = new BufferedReader(fr);
//            String line = bfr.readLine();
//            String contents = "";
//            while (line != null) {
//                contents += line;
//                line = bfr.readLine();
//            }
//            FileOutputStream fos = new FileOutputStream(f);
//            PrintWriter pw = new PrintWriter(fos);
//            pw.println(contents);
//            pw.println(products.get(i));
//            bfr.close();
//            pw.close();
//        }
//        this.products = products;
//    }


    @Override
    public String toString() {
        return "Seller{" +
                "customerNames=" + customerNames +
                '}';
    }
}
