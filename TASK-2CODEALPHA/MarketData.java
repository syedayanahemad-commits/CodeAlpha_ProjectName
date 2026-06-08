import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class MarketData {

    public static ObservableList<Stock> getStocks() {

        ObservableList<Stock> stocks =
                FXCollections.observableArrayList();

        stocks.add(new Stock("NVDA", "NVIDIA", 168.50, 4.2));
        stocks.add(new Stock("AAPL", "Apple", 212.30, 2.1));
        stocks.add(new Stock("TSLA", "Tesla", 315.80, -1.3));
        stocks.add(new Stock("META", "Meta", 550.10, 0.9));
        stocks.add(new Stock("AMZN", "Amazon", 185.40, 1.8));

        return stocks;
    }
}