Project 4
Stanley Kim, Vidyaratnam Ganapathy, Richard Su, Michael Hughes
Essay is submitted by Vidyaratnam on Brightspace.
Project is submitted to Vocareum by Stanley.

Instructions:
The main method for the project is hosted in the MarketPlace class.
Compile and run the Marketplace class to run the program.
You must first create an account to use the program.
To start off, we recommend to create a seller account and add a market, so that you can add products in the market that you created
You can create a Customer account after the changes
The program will prompt you for input/give instructions.
When options are presented as a numbered list, enter the number to select an option.
When prompted about the quantity of a product, enter an int.
Similarly for price enter a double.


Note: All txt files excluding the README file must be deleted if the program runs the RunLocalTest


Description of classes


User class
Base class for a User.
Fields: String customerName (The name of the user). String username (The user's email/username). String password (the user's password)
The methods for this class are very simple: A constructor that takes input and sets all fields accordingly, and
just the basic get and set methods for each field, and User.toString() which formats the User object to a String.

File format:
name: user + "'s File.txt"
Name: name
User: userType
for sellers: list of markets
Market
Market

--------

Customer class extends User
Class for a Customer.
Fields: all fields from User class, and a ShoppingCart field for a customers ShoppingCart.
Methods: get ShoppingCart and setShoppingCart get and set the ShoppingCart field, respectively.
Customer.toString() overrides the User method and returns the Customer object formatted to a String.

--------

Seller class extends User
Class for a Seller.
Fields: All fields from User class, and an ArrayList<String> customerNames, which is an ArrayList of all Customer users in the MarketPlace.
Constructor: checks the Customers.txt file and sets the customerNames ArrayList. This allows some of the sellerOptions to work, also calls the User constructor.
Methods: setProducts() and getProducts(): While they were implemented in the past, we ended up not using these methods. toString() formats the Seller class to a String.

--------

Product class
Class for a Product
Fields:
String Name - the Product's name
String Store - the name of the Market the Product is stored in or purchased from
String Description - the Product's description
int Quantity - when Customers are buying it represents how much of the product they buy, for Sellers it is how much of the Product they have in stock.
double Price - the price of an item/the price it is bought at

Constructor: Takes the field values as parameters and sets them.

Methods:
Getter and Setter methods for each field
toString - separates each field of the Product with a comma. This is directly printed onto files.
format is: name,store,description,quantity,price

--------

ShoppingCart class
Class for a shoppingCart
Fields: ArrayList<Product> cartItems
Constructor:
Methods:
setter and getter for cartItems
toString - returns the shoppingCart in String form
totalCost - calculates the total cost of all items in the shoppingCart (price * quantity of each Product object in the cart)
addItem(Product product) - adds a Product to the shoppingCart
removeItem(Product product) - removes an item from the shoppingCart
removeItem(Product product, int amount) - removes amount of an item from the shoppingCart
These methods are not heavily used because we moved towards a more file-based system of data storage.

--------

Market class
Class for a store/Market
Fields: ArrayList<Product> products - ArrayList of products in the store. String name - name of the market.
Constructor: parameters String name, ArrayList<Product> products
    checks if market already exists in markets.txt. throws MarketExistsException if it does
    while markets.txt does not exist, creates new markets.txt file with itself as the first market name
    creates its own market file of the format "name" + " Market.txt"
    prints each product to the file, in the form name;;description;;store;;price;;quantity
    overloaded so that it can be called without an ArrayList of products.
Methods:
getters and setters for the fields, toString
This class is also not heavily used due to a file-heavy shift in methodology.

--------

CustomerOptions class
Houses most of the options for Customers.
viewMarket - allows a Customer to view the overall MarketPlace. this method is also used for Sellers to view the MarketPlace.
searchForProducts - allows a Customer to search for Products by name, quantity, or description.
sortByPrice - allows a Customer to sort all products by price ascending
sortByQuantity - allows a Customer to sort all products by quantity descending
addOrRemoveProductsShoppingCart(Scanner scanner, String customerName) - Prompts the customer to add or remove products from their shoppingCart. Edits Store quantities accordingly.
buyShoppingCart(Customer customer) - Buys all the products in the shoppingCart (removes them from the shoppingcart, then in each market's file lists the applicable products in the sales section. Adds customer to the customer section if not already in it)
viewShoppingCart(Customer user) - Allows a customer to view their shoppingCart

--------

SellerOptions class
Hold most options for Sellers
PurchaseInformation - used in ViewSales, has accompanying get and set methods
editProducts - allows sellers to create, edit or remove products from their markets
viewSales - allows sellers to view their sales.
viewCustomerShoppingCarts - allows sellers to view customers' shoppingCarts
deleteMarket - deletes a market. removes all products of that market from shoppingcarts
createMarket - creates a new market and its corresponding file
Market file format:
name is: name + " Market.txt"
contents:
//Products
Product.toString
Product.toString
--------
//Customers
Customer
Customer
--------
//Sales
Product.toString,Customer
Product.toString,Customer
Product.toString,Customer
Product.toString,Customer

--------

MarketPlace class
Houses the main method and some other useful methods
Main method: gives prompts and runs the program for the user.
verifyLogin: verifies a users login
getUser: gets user object for someone
getProduct: gets the product object from a Product.toString() line
checkAccount: checks if an account exists
createUser: creates a new User's file and calls the constructor

--------

Dashboard
viewSeller - allows a seller to view their dashboard
viewCustomer - allows a customer to view their dashboard
csvFile - allows a seller to import/export via csv file
exportPurchaseHistory - allows a customer to export their purchase history
