package com.stock.exchange;

import com.stock.exchange.model.TransactionData;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class BuySell {

    private static List<TransactionData> transactionBuyList = new ArrayList<>();
    private static List<TransactionData> transactionSellList = new ArrayList<>();
    private static List<TransactionData> allTransactions = new ArrayList<>();

    public static void main(String[] args) throws Exception {

        try {
            String readPath = args[0];
//            String readPath = System.getProperty("user.dir") + "/src/main/resources/stock-exchange.txt";
            File file = new File(readPath);
            FileReader fr = new FileReader(file);
            BufferedReader br = new BufferedReader(fr);
            String line;
            while ((line = br.readLine()) != null) {
                allTransactions.add(new TransactionData(line));
            }
            br.close();
            fr.close();
        } catch (Exception e){
            System.out.println("Caused by: " + e.getMessage());
            e.printStackTrace();
            throw new Exception(e.getMessage());
        }
        func();
    }

    private static void func() {
        for (TransactionData currentTransaction : allTransactions) {
            switch (currentTransaction.getTransaction()) {
                case BUY:
                    for (TransactionData transactionSell : transactionSellList) {
                        if (currentTransaction.getStock().equals(transactionSell.getStock()) &&
                            currentTransaction.getPrice() >= transactionSell.getPrice()) {
                            if (currentTransaction.getQuantity() >= transactionSell.getQuantity()) {
                                System.out.println(
                                        "#" + currentTransaction.getId() + " " +
                                        transactionSell.getPrice() + " " +
                                        transactionSell.getQuantity() + " " +
                                        "#" + transactionSell.getId());
                                currentTransaction.setQuantity(currentTransaction.getQuantity() - transactionSell.getQuantity());
                                transactionSell.setQuantity(0);
                            } else {
                                System.out.println(
                                        "#" + currentTransaction.getId() + " " +
                                        transactionSell.getPrice() + " " +
                                        currentTransaction.getQuantity() + " " +
                                        "#" + transactionSell.getId());
                                transactionSell.setQuantity(transactionSell.getQuantity() - currentTransaction.getQuantity());
                            }
                        }
                    }
                    transactionSellList = transactionSellList.stream().filter(x -> x.getQuantity()>0).collect(
                            Collectors.toList());
                    if (currentTransaction.getQuantity() > 0) {
                        transactionBuyList.add(currentTransaction);
                    }
                    break;
                case SELL:
                    for (TransactionData transactionBuy : transactionBuyList) {
                        if (currentTransaction.getStock().equals(transactionBuy.getStock()) &&
                            currentTransaction.getPrice() <= transactionBuy.getPrice()) {
                            if (currentTransaction.getQuantity() >= transactionBuy.getQuantity()) {
                                System.out.println(
                                        "#" + transactionBuy.getId() + " " +
                                        currentTransaction.getPrice() + " " +
                                        transactionBuy.getQuantity() + " " +
                                        "#" + currentTransaction.getId());
                                currentTransaction.setQuantity(currentTransaction.getQuantity() - transactionBuy.getQuantity());
                                transactionBuy.setQuantity(0);
                            } else {
                                System.out.println(
                                        "#" + transactionBuy.getId() + " " +
                                        currentTransaction.getPrice() + " " +
                                        currentTransaction.getQuantity() + " " +
                                        "#" + currentTransaction.getId());
                                transactionBuy.setQuantity(transactionBuy.getQuantity() - currentTransaction.getQuantity());
                            }
                        }
                    }
                    transactionBuyList = transactionBuyList.stream().filter(x -> x.getQuantity()>0).collect(
                            Collectors.toList());
                    if (currentTransaction.getQuantity() > 0) {
                        transactionSellList.add(currentTransaction);
                    }
                    break;
            }
        }
    }
}
