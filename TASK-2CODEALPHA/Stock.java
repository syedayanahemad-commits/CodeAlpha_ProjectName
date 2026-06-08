public class Stock {

    private String symbol;
    private String company;
    private double price;
    private double changePercent;

    public Stock(String symbol,
                 String company,
                 double price,
                 double changePercent) {

        this.symbol = symbol;
        this.company = company;
        this.price = price;
        this.changePercent = changePercent;
    }

    public String getSymbol() {
        return symbol;
    }

    public String getCompany() {
        return company;
    }

    public double getPrice() {
        return price;
    }

    public double getChangePercent() {
        return changePercent;
    }
}