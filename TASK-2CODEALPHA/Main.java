import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.TextField;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage stage) {

        // Sidebar
        VBox sidebar = new VBox(15);

        Button dashboardBtn = new Button("Dashboard");
        Button marketBtn = new Button("Market Watch");
        Button portfolioBtn = new Button("Portfolio");
        Button transactionBtn = new Button("Transactions");

        dashboardBtn.getStyleClass().add("sidebar-button");
        marketBtn.getStyleClass().add("sidebar-button");
        portfolioBtn.getStyleClass().add("sidebar-button");
        transactionBtn.getStyleClass().add("sidebar-button");

        sidebar.getChildren().addAll(
                dashboardBtn,
                marketBtn,
                portfolioBtn,
                transactionBtn
        );

        sidebar.getStyleClass().add("sidebar");
        sidebar.setPadding(new Insets(20));

        // Title
        Label title = new Label("📈 TradeX Pro 2026");
        title.getStyleClass().add("title");

        // Dashboard Cards
        VBox card1 = createCard(
                "Portfolio Value",
                "₹100,000"
        );

        VBox card2 = createCard(
                "Cash Balance",
                "₹75,000"
        );

        VBox card3 = createCard(
                "Today's P/L",
                "+₹2,350"
        );

        HBox cards = new HBox(20);
cards.setSpacing(25);
        cards.getChildren().addAll(
                card1,
                card2,
                card3
        );

        // Market Table
       TableView<Stock> marketTable =
        new TableView<>();

TableColumn<Stock, String> symbolCol =
        new TableColumn<>("Symbol");

symbolCol.setCellValueFactory(
        new javafx.scene.control.cell.PropertyValueFactory<>("symbol"));

TableColumn<Stock, String> companyCol =
        new TableColumn<>("Company");

companyCol.setCellValueFactory(
        new javafx.scene.control.cell.PropertyValueFactory<>("company"));

TableColumn<Stock, Double> priceCol =
        new TableColumn<>("Price");

priceCol.setCellValueFactory(
        new javafx.scene.control.cell.PropertyValueFactory<>("price"));

TableColumn<Stock, Double> changeCol =
        new TableColumn<>("Change %");

changeCol.setCellValueFactory(
        new javafx.scene.control.cell.PropertyValueFactory<>("changePercent"));

        changeCol.setCellFactory(column ->
        new javafx.scene.control.TableCell<Stock, Double>() {

    @Override
    protected void updateItem(Double value, boolean empty) {

        super.updateItem(value, empty);

        if (empty || value == null) {

            setText(null);
            setStyle("");

        } else {

            setText(value + "%");

            if (value >= 0) {

                setStyle(
                        "-fx-text-fill: #22c55e;" +
                        "-fx-font-weight: bold;"
                );

            } else {

                setStyle(
                        "-fx-text-fill: #ef4444;" +
                        "-fx-font-weight: bold;"
                );
            }
        }
    }
});

marketTable.getColumns().addAll(
        symbolCol,
        companyCol,
        priceCol,
        changeCol
);

marketTable.setItems(
        MarketData.getStocks()
);
marketTable.setColumnResizePolicy(
        TableView.CONSTRAINED_RESIZE_POLICY);

marketTable.setFixedCellSize(35);
marketTable.setPrefHeight(500);
marketTable.setPrefWidth(800);
Timeline timer =
        new Timeline(

                new KeyFrame(

                        Duration.seconds(2),

                        e -> {

                            marketTable.refresh();

                        }
                )
        );

timer.setCycleCount(
        Timeline.INDEFINITE
);

timer.play();
Label portfolioTitle = new Label("Portfolio Holdings");
portfolioTitle.getStyleClass().add("section-title");

TableView<Holding> portfolioTable =
        new TableView<>();

TableColumn<Holding, String> stockCol =
        new TableColumn<>("Stock");

stockCol.setCellValueFactory(
        new javafx.scene.control.cell.PropertyValueFactory<>("symbol"));

TableColumn<Holding, Integer> qtyCol =
        new TableColumn<>("Quantity");

qtyCol.setCellValueFactory(
        new javafx.scene.control.cell.PropertyValueFactory<>("quantity"));

portfolioTable.getColumns().addAll(
        stockCol,
        qtyCol
);

portfolioTable.setPrefHeight(250);
portfolioTable.setColumnResizePolicy(
        TableView.CONSTRAINED_RESIZE_POLICY
);
Button buyButton = new Button("📈 Buy Stock");
Button sellButton = new Button("📉 Sell Stock");

sellButton.getStyleClass().add("sell-button");
buyButton.getStyleClass().add("buy-button");

Label transactionTitle =
        new Label("Transaction History");

transactionTitle.getStyleClass()
        .add("section-title");
        TableView<Transaction> transactionTable =
        new TableView<>();
        TableColumn<Transaction, String> typeCol =
        new TableColumn<>("Type");

typeCol.setCellValueFactory(
        new javafx.scene.control.cell.PropertyValueFactory<>("type"));

TableColumn<Transaction, String> stockTransCol =
        new TableColumn<>("Stock");

stockTransCol.setCellValueFactory(
        new javafx.scene.control.cell.PropertyValueFactory<>("stock"));

TableColumn<Transaction, Integer> qtyTransCol =
        new TableColumn<>("Quantity");

qtyTransCol.setCellValueFactory(
        new javafx.scene.control.cell.PropertyValueFactory<>("quantity"));

transactionTable.getColumns().addAll(
        typeCol,
        stockTransCol,
        qtyTransCol
);

transactionTable.setColumnResizePolicy(
        TableView.CONSTRAINED_RESIZE_POLICY
);

transactionTable.setPrefHeight(180);

NumberAxis xAxis =
        new NumberAxis();

NumberAxis yAxis =
        new NumberAxis();

LineChart<Number,Number> stockChart =
        new LineChart<>(xAxis,yAxis);

stockChart.setLegendVisible(false);
stockChart.setPrefHeight(220);

XYChart.Series<Number,Number> series =
        new XYChart.Series<>();

series.getData().add(
        new XYChart.Data<>(1,180));

series.getData().add(
        new XYChart.Data<>(2,185));

series.getData().add(
        new XYChart.Data<>(3,178));

series.getData().add(
        new XYChart.Data<>(4,190));

stockChart.getData().add(series);

VBox rightPanel = new VBox(20);

rightPanel.getChildren().addAll(
        portfolioTitle,
        portfolioTable,
        stockChart,
        transactionTitle,
        transactionTable
);

rightPanel.setPrefWidth(350);

Label tradeTitle =
        new Label("Trade Panel");

tradeTitle.getStyleClass()
        .add("section-title");

TextField symbolField =
        new TextField();

symbolField.setPromptText(
        "Stock Symbol"
);

TextField qtyField =
        new TextField();

qtyField.setPromptText(
        "Quantity"
);

buyButton.setOnAction(e -> {

   String symbol =
            symbolField.getText().trim();

    int qty =
            Integer.parseInt(
                    qtyField.getText()
            );

    portfolioTable.getItems().add(
            new Holding(
                    symbol.toUpperCase(),
                    qty,
                    100.0
            )
    );

    transactionTable.getItems().add(
            new Transaction(
                    "BUY",
                    symbol.toUpperCase(),
                    qty
            )
    );
javafx.scene.control.Alert alert =
        new javafx.scene.control.Alert(
                javafx.scene.control.Alert.AlertType.INFORMATION);

alert.setHeaderText(null);
alert.setContentText(
        qty +
        " shares of " +
        symbol.toUpperCase() +
        " purchased."
);

alert.showAndWait();
    symbolField.clear();
    qtyField.clear();
});


sellButton.setOnAction(e -> {

    String sellSymbol =
            symbolField.getText().trim().toUpperCase();

    if (sellSymbol.isEmpty()) {
        javafx.scene.control.Alert alert =
                new javafx.scene.control.Alert(
                        javafx.scene.control.Alert.AlertType.ERROR);

        alert.setHeaderText(null);
        alert.setContentText("Please enter Stock Symbol!");
        alert.showAndWait();
        return;
    }

    int qty;

    try {
        qty = Integer.parseInt(qtyField.getText());
    } catch (Exception ex) {

        javafx.scene.control.Alert alert =
                new javafx.scene.control.Alert(
                        javafx.scene.control.Alert.AlertType.ERROR);

        alert.setHeaderText(null);
        alert.setContentText("Enter valid quantity!");
        alert.showAndWait();
        return;
    }

    for (int i = 0; i < portfolioTable.getItems().size(); i++) {

        Holding h = portfolioTable.getItems().get(i);

        if (h.getSymbol().equals(sellSymbol)) {

            if (h.getQuantity() > qty) {
                h.removeQuantity(qty);
                portfolioTable.refresh();
            } else {
                portfolioTable.getItems().remove(i);
            }

            transactionTable.getItems().add(
                    new Transaction(
                            "SELL",
                            sellSymbol,
                            qty
                    )
            );

            symbolField.clear();
            qtyField.clear();
            return;
        }
    }

    
        javafx.scene.control.Alert alert =
            new javafx.scene.control.Alert(
                    javafx.scene.control.Alert.AlertType.ERROR);

    alert.setHeaderText(null);
    alert.setContentText(
            "Stock not found in portfolio!"
    );

    alert.showAndWait();

    symbolField.clear();
    qtyField.clear();

});   // <-- only ONE closing for sellButton.setOnAction

HBox tradeButtons =
        new HBox(15);

tradeButtons.getChildren().addAll(
        buyButton,
        sellButton
);

VBox leftPanel = new VBox(20);

leftPanel.getChildren().addAll(
        tradeTitle,
        symbolField,
        qtyField,
        tradeButtons,
        marketTable
);

HBox dashboardBody = new HBox(25);

dashboardBody.getChildren().addAll(
        leftPanel,
        rightPanel
);

VBox centerContent = new VBox(
        25,
        title,
        cards,
        dashboardBody
);

centerContent.setPadding(
        new Insets(25)
);


        BorderPane root =
                new BorderPane();

        root.setLeft(sidebar);
       javafx.scene.control.ScrollPane scrollPane =
        new javafx.scene.control.ScrollPane(centerContent);

scrollPane.setFitToWidth(true);
scrollPane.setPannable(true);
root.setCenter(scrollPane);

Scene scene = new Scene(
        root,
        1450,
        850
);

scene.getStylesheets().add(
        getClass()
                .getResource("/styles/dark-theme.css")
                .toExternalForm()
);

stage.setTitle("TradeX Pro");
stage.setScene(scene);
stage.show();
    }
    private VBox createCard(
            String title,
            String value) {

        Label titleLabel =
                new Label(title);

        titleLabel.getStyleClass()
                .add("card-title");

        Label valueLabel =
                new Label(value);

        valueLabel.getStyleClass()
                .add("card-value");

        VBox card =
                new VBox(
                        10,
                        titleLabel,
                        valueLabel
                );

        card.getStyleClass()
                .add("card");
                card.setPrefWidth(250);
card.setPrefHeight(120);

        return card;
    }

    public static void main(
            String[] args) {

        launch(args);
    }
}