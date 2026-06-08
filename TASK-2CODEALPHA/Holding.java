public class Holding {

    private String symbol;
    private int quantity;
    private double averagePrice;

    public Holding(String symbol,
                   int quantity,
                   double averagePrice) {

        this.symbol = symbol;
        this.quantity = quantity;
        this.averagePrice = averagePrice;
    }

    public String getSymbol() {
        return symbol;
    }

    public int getQuantity() {
        return quantity;
    }

    public double getAveragePrice() {
        return averagePrice;
    }

    public void addQuantity(int qty) {
        quantity += qty;
    }

    public void removeQuantity(int qty) {
        quantity -= qty;
    }
}