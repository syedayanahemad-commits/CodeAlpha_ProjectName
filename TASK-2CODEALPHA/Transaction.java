public class Transaction {

    private String type;
    private String stock;
    private int quantity;

    public Transaction(
            String type,
            String stock,
            int quantity) {

        this.type = type;
        this.stock = stock;
        this.quantity = quantity;
    }

    public String getType() {
        return type;
    }

    public String getStock() {
        return stock;
    }

    public int getQuantity() {
        return quantity;
    }
}