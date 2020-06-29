package com.gildedrose.exception;

public class InvalidItemException extends Exception {
    public InvalidItemException() {
        super("Item is invalid");
    }
}
