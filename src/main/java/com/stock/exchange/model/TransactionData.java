package com.stock.exchange.model;

import com.stock.exchange.exception.DataException;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class TransactionData {

    Integer id;
    String time;
    String stock;
    Transaction transaction;
    Double price;
    Integer quantity;


    public TransactionData(String line) throws Exception {
        try {
            String[] lineSplit = line.split("\\s+");
            id = Integer.valueOf(lineSplit[0]);
            time = lineSplit[1];
            stock = lineSplit[2];
            transaction = Transaction.valueOf(lineSplit[3].toUpperCase());
            price = Double.valueOf(lineSplit[4]);
            quantity = Integer.valueOf(lineSplit[5]);
        } catch (Exception e) {
            System.out.println("Data not in proper format. Caused by: " + e.getMessage());
            e.printStackTrace();
            throw new DataException("Data not in proper format");
        }
    }
}
