public class MarketExistsException extends Exception {
    public MarketExistsException(String marketName) {
        super("Market " + marketName + "already exists!");
    }
}
