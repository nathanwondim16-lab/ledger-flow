package com.pluralsight;

import java.time.LocalDate;
import java.time.LocalTime;


// Consider making this class a record
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

    public LocalDate getTransactionDate() {
        return transactionDate;
    }

    public LocalTime getTransactionTime() {
        return transactionTime;
    }

    public String getTransactionDescription() {
        return transactionDescription;
    }

    public String getVendor() {
        return vendor;
    }

    public double getTransactionAmount() {
        return transactionAmount;
    }

    @Override
    public String toString() {
        return transactionDate.format(DateTimeFormats.DATE) + "|" + transactionTime.format(DateTimeFormats.TIME) + "|" + transactionDescription + "|"
                + vendor + "|" + transactionAmount;
    }
}
