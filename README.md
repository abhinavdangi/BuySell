# BuySell
This is an order matching system for a stock exchange.

Traders place Buy and Sell orders for a stock indicating the price and quantity.

Each order gets entered into the exchange’s order-book and remains there until it is matched. Order matching is attempted whenever a new order is added.

The exchange follows a FirstInFirstOut Price-Time order-matching rule, which states that: “The first order in the order-book at a price level is the first order matched. All orders at the same price level are filled according to time priority”.

The exchange works like a market where lower selling prices and higher buying prices get priority.

A trade is executed when a buy price is greater than or equal to a sell price. The trade is recorded at the price of the sell order regardless of the price of the buy order.

Taking inputs as orders in a file, this code returns the exchange transactions.

## Deployment
Steps for running the project are:
1. mvn clean install
2. java -jar target/BuySell-1.0-SNAPSHOT.jar <location of input file>

Example of input file: BuySell/src/main/resources/stock-exchange.txt
