package com.stock.exchange.exception;

public class DataException extends Exception {
    public DataException(String message) {
        super("Data Exception: " + message);
    }
}
