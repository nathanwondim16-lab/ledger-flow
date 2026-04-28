package com.pluralsight;

import java.time.LocalDate;
import java.time.LocalTime;

public class Transaction {
    private final LocalDate transactionDate;
    private final LocalTime transactionTime;
    private final String transactionDescription;
    private final String vendor;
    private final double transactionAmount;

    public Transaction(LocalDate transactionDate, LocalTime transactionTime
                       ,String transactionDescription, String vendor, double transactionAmount) {

        this.transactionDate = transactionDate;
        this.transactionTime = transactionTime;
        this.transactionDescription = transactionDescription;
        this.vendor = vendor;
        this.transactionAmount = transactionAmount;
    }

    @Override
    public String toString() {
        return transactionDate + "|" + transactionTime + "|" + transactionDescription + "|"
                + vendor + "|" + transactionAmount;
    }
}
