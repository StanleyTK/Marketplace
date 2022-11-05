import java.util.ArrayList;

public class ShoppingCart {
    ArrayList<Product> cartItems;

    public ShoppingCart(ArrayList<Product> cartItems) {
        this.cartItems = cartItems;
    }

    public ArrayList<Product> getCartItems() {
        return cartItems;
    }

    public void setCartItems(ArrayList<Product> cartItems) {
        this.cartItems = cartItems;
    }

    @Override
    public String toString() {
        return "ShoppingCart{" +
                "cartItems=" + cartItems +
                '}';
    }

    //Method to find the total cost of items in a shopping cart.
    public double totalCost() {
        double totalCost = 0;
        for (Product cartItem : cartItems) {
            double itemCost = cartItem.getQuantity() * cartItem.getPrice(); //Cost of each item.
            totalCost += itemCost;
        }
        return totalCost; //Cost of all the items in the cart
    }
}
