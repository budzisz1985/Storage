package com.AutoOrders.Storage.storage;

public class PosNotFoundExeption extends Throwable {
    public PosNotFoundExeption(String message) {
        super(message);
    }
}
