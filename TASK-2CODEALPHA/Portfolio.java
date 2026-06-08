import java.util.ArrayList;

public class Portfolio {

    private double cashBalance = 100000;

    private ArrayList<Holding> holdings =
            new ArrayList<>();

    private ArrayList<Transaction> transactions =
            new ArrayList<>();

    public double getCashBalance() {
        return cashBalance;
    }

    public ArrayList<Holding> getHoldings() {
        return holdings;
    }

    public ArrayList<Transaction> getTransactions() {
        return transactions;
    }

    public void buyStock(String symbol,
                         int qty,
                         double price) {

        double cost = qty * price;

        if(cost > cashBalance)
            return;

        cashBalance -= cost;

        boolean found = false;

        for(Holding h : holdings) {

            if(h.getSymbol().equals(symbol)) {

                h.addQuantity(qty);
                found = true;
                break;
            }
        }

        if(!found) {

            holdings.add(
                    new Holding(
                            symbol,
                            qty,
                            price
                    )
            );
        }

        transactions.add(
                new Transaction(
                        "BUY",
                        symbol,
                        qty
                )
        );
    }

    public void sellStock(String symbol,
                          int qty,
                          double price) {

        for(Holding h : holdings) {

            if(h.getSymbol().equals(symbol)) {

                if(h.getQuantity() >= qty) {

                    h.removeQuantity(qty);

                    cashBalance += qty * price;

                    transactions.add(
                            new Transaction(
                                    "SELL",
                                    symbol,
                                    qty
                            )
                    );

                    break;
                }
            }
        }
    }
}